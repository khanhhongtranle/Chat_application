package Entities;

import java.io.Serializable;

public class Account implements Serializable {
    protected int id;
    protected String accountName;
    protected String accountPassword;

    public Account(){

    }

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
