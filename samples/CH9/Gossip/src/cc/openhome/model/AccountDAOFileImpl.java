package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountDAOFileImpl implements AccountDAO {
    private String USERS;
    
    public AccountDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    @Override
    public boolean isUserExisted(Account account) {
        for (String file : new File(USERS).list()) {
            if (file.equals(account.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addAccount(Account account) {
        File userhome = new File(USERS + "/" + account.getName());
        userhome.mkdir();
        IOException ex = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
            writer.write(account.getEmail() + "\t" + account.getPassword());
        } catch (IOException e) {
            ex = e;
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                if(ex == null) {
                    ex = e;
                }
            }
        }
        if(ex != null) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Account getAccount(Account account) {
        for (String file : new File(USERS).list()) {
            if (file.equals(account.getName())) {
                IOException ex = null;
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(
                            USERS + "/" + file + "/profile"));
                    String passwd = reader.readLine().split("\t")[1];
                    Account storedAcct = new Account();
                    storedAcct.setName(account.getName());
                    storedAcct.setPassword(passwd);
                    return storedAcct;
                } catch (IOException e) {
                    ex = e;
                }
                finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        if(ex == null) {
                            ex = e;
                        }
                    }
                }
                if(ex != null) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }

}
