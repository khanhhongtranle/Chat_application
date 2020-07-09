package Controllers;

import Client.Client;
import Entities.Account;
import Models.AccountModel;
import Views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginController {
    private LoginView view;

    private final Client client;

    public LoginController(){
        view = new LoginView();
        view.loginButtonListener(new LoginListener());
        view.signUpButtonListener(new SignUpListener());


        this.client = new Client("localhost", 7070);
        client.connect();
    }

    public void setVisibleView(boolean b){
        view.setVisible(b);
    }

    private class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getUsernameField().equals("") ||
                view.getPasswordField().equals("")){
                JOptionPane.showMessageDialog(view, "The username or password is incorrect");
                return;
            }

            /*if (AccountModel.select(view.getUsernameField(), view.getPasswordField()) == null){
                JOptionPane.showMessageDialog(view, "The username or password is incorrect");
                return;
            }*/

            try{
                if (client.login(view.getUsernameField(), view.getPasswordField())){
                    Account account = AccountModel.select(view.getUsernameField());

                    //JOptionPane.showMessageDialog(view, "Logined");
                    ChatRoomController chatRoomController = new ChatRoomController(client,account);
                    chatRoomController.setVisibleView(true);

                    view.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(view, "The username or password is incorrect");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return;
        }
    }

    private class SignUpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SignUpController signUpController = new SignUpController();
            signUpController.setVisibleView(true);
        }
    }
}
