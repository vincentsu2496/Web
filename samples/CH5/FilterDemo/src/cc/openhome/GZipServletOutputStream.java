package cc.openhome;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {
    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(
            ServletOutputStream servletOutputStream) throws IOException {
        this.gzipOutputStream =
              new GZIPOutputStream(servletOutputStream);
    }

    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }

    public GZIPOutputStream getGzipOutputStream() {
        return gzipOutputStream;
    }
}