package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    protected int id;
    protected String accountName;
    protected String accountPassword;

    public static  List<Account> list = new ArrayList<>();;

    static{
        list.add(new Account("khanhhong01", "123456"));
        list.add(new Account("khanhhong02", "123456"));
        list.add(new Account("khanhhong03", "123456"));
        list.add(new Account("khanhhong04", "123456"));
        list.add(new Account("khanhhong05", "123456"));
        list.add(new Account("khanhhong06", "123456"));
    }

    public Account(){

    }

    public Account(String name, String pass){
        this.accountName = name;
        this.accountPassword = pass;
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
