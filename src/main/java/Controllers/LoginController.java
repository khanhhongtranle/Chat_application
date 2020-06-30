package Controllers;

import Models.AccountModel;
import Views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;

    public LoginController(){
        view = new LoginView();
        view.loginButtonListener(new LoginListener());
        view.signUpButtonListener(new SignUpListener());
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

            if (AccountModel.select(view.getUsernameField(), view.getPasswordField()) == null){
                JOptionPane.showMessageDialog(view, "The username or password is incorrect");
                return;
            }

            JOptionPane.showMessageDialog(view, "Logined");
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
