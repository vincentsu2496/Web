package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlahDAOFileImpl implements BlahDAO {
    private String USERS;
    
    public BlahDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    private class TxtFilenameFilter implements FilenameFilter {
        @Override 
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }
    
    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();
    
    @Override
    public List<Blah> getBlahs(Blah blah) {
        List<Blah> blahs = new ArrayList<Blah>();
        
        File border = new File(USERS + "/" + blah.getUsername());
        String[] txts = border.list(filenameFilter);
        for(String txt : txts) { 
            BufferedReader reader = null;
            IOException ex = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(USERS + "/" + blah.getUsername() + "/" +  txt), "UTF-8"));
                String text = null;
                StringBuilder builder = new StringBuilder();
                while((text = reader.readLine()) != null) {
                    builder.append(text);
                }
                Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
                String content = builder.toString();
                blahs.add(new Blah(blah.getUsername(), date, content));
            } catch (IOException e) {
                ex = e;
            }
            finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        if(ex == null) {
                            ex = e;
                        }
                    }
                }
            }
            if(ex != null) {
                throw new RuntimeException(ex);
            }
        }
        
        return blahs;
    }

    @Override
    public void addBlah(Blah blah) {
        String file = USERS + "/" + blah.getUsername() + "/" + blah.getDate().getTime() + ".txt";
        BufferedWriter writer = null;
        IOException ex = null;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(blah.getTxt());
        } catch (IOException e) {
            ex = e;
        }
        finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if(ex != null) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteBlah(Blah blah) {
        File file = new File(USERS + "/" + blah.getUsername() + "/" + blah.getDate().getTime() + ".txt");
        if(file.exists()) {
            file.delete();
        }
    }

}
