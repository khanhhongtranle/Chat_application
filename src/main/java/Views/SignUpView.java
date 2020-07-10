package Views;

import Client.Client;
import Entities.Account;
import Models.AccountModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpView extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton submitButton;

    //private final Client client;

    public SignUpView(){
        //this.client = client1;
        //client.connect();

        this.setTitle("Sign up");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        panel1.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        panel1.setPreferredSize(new Dimension(500, 500));

        this.setContentPane(panel1);
        this.pack();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getUsername().equals("") ||
                        getPassword().equals("") ||
                        getPasswordAgain().equals("")){
                    JOptionPane.showMessageDialog(panel1,"Please fill up all of fields");
                    return;
                }

                if (AccountModel.select(getUsername()) != null){
                    JOptionPane.showMessageDialog(panel1, "This username is exists");
                    return;
                }

                if (!getPasswordAgain().equals(getPasswordAgain())){
                    JOptionPane.showMessageDialog(panel1, "The passwords do not match");
                    return;
                }

                Account account = new Account();
                account.setAccountName(getUsername());
                account.setAccountPassword(getPassword());
                try{
                    AccountModel.insert(account);

                    JOptionPane.showMessageDialog(panel1,"Sign up successfully!");

                    dispose();

                }catch(Exception exception){
                    throw  exception;
                }
            }
        });
    }

    public String getUsername(){
        return textField1.getText();
    }

    public String getPassword(){
        return new String(passwordField1.getPassword());
    }

    public String getPasswordAgain(){
        return new String(passwordField2.getPassword());
    }
}
