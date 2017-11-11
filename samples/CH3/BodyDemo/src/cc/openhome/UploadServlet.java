package cc.openhome;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                       throws ServletException, IOException {
        // 讀取請求本體
        byte[] body = readBody(request);

        // 取得所有本體內容的字串表示
        String textBody = new String(body, "ISO-8859-1");
        
        // 取得上傳的檔案名稱
        String filename = getFilename(textBody);

        // 取得檔案啟始與結束位置
        Position p = getFilePosition(request, textBody);
        // 輸出至檔案
        writeTo(filename, body, p);
    }
    
    class Position {
        int begin;
        int end;
        Position(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }
    
    private byte[] readBody(HttpServletRequest request) throws IOException {
        int formDataLength = request.getContentLength();
        DataInputStream dataStream =
                new DataInputStream(request.getInputStream());
        byte body[] = new byte[formDataLength];
        int totalBytes = 0;
        while (totalBytes < formDataLength) {
            int bytes = dataStream.read(body, totalBytes, formDataLength);
            totalBytes += bytes;
        }
        return body;
    }
    
    private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {
        // 取得檔案區段邊界資訊
        String contentType = request.getContentType();
        String boundaryText = contentType.substring(
                contentType.lastIndexOf("=") + 1, contentType.length());
        // 取得實際上傳檔案的起始與結束位置        
        int pos = textBody.indexOf("filename=\"");
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;
        int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
        int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
        int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;
        return new Position(begin, end);
    }
    
    private String getFilename(String reqBody) {
        String filename = reqBody.substring(
                reqBody.indexOf("filename=\"") + 10);
        filename = filename.substring(0, filename.indexOf("\n"));
        filename = filename.substring(
                filename.lastIndexOf("\\") + 1, filename.indexOf("\""));
        return filename;
    }

    private void writeTo(String filename, byte[] body, Position p)
            throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream =
                new FileOutputStream("c:/workspace/" + filename);
        fileOutputStream.write(body, p.begin, (p.end - p.begin));
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
