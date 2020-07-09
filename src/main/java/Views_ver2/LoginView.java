package Views_ver2;

import Client.Client;
import Controllers.LoginController;
import Entities.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame implements ActionListener {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton LOGINButton;

    private final Client client;

    public LoginView(){
        this.setTitle("Welcome to chatting world");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel1.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        panel1.setPreferredSize(new Dimension(500, 300));

        this.setContentPane(panel1);
        this.pack();

        LOGINButton.addActionListener(this);

        //
        client = new Client("localhost",7070);
        client.connect();
    }



    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getUsername().equals("") || this.getPassword().equals("")){
            JOptionPane.showMessageDialog(panel1,"Username or password must been fill");
            return;
        }
        this.doLogin();
    }

    public String getUsername(){
        return textField1.getText();
    }

    public String getPassword(){
        return new String (passwordField1.getPassword());
    }

    protected void doLogin(){
        String user = this.getUsername();
        String pass = this.getPassword();

        try{
            if (client.login(user,pass)){
                ChatRoomView chatRoomView = new ChatRoomView(client);
                chatRoomView.setVisible(true);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(panel1,"Username or password is incorrect");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
