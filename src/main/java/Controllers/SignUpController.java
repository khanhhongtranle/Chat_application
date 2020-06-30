package Controllers;

import Entities.Account;
import Models.AccountModel;
import Views.SignUpView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    private SignUpView view;

    public SignUpController(){
        view = new SignUpView();
        view.submitButtonListener(new SubmitListener());
    }

    public void setVisibleView(boolean b){
        view.setVisible(b);
    }

    private class SubmitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getUsernameField().equals("") ||
                view.getPasswordField().equals("") ||
                view.getRePasswordField().equals("")){
                JOptionPane.showMessageDialog(view,"Please fill up all of fields");
                return;
            }

            if (AccountModel.select(view.getUsernameField()) != null){
                JOptionPane.showMessageDialog(view, "This username is exists");
                return;
            }

            if (!view.getPasswordField().equals(view.getRePasswordField())){
                JOptionPane.showMessageDialog(view, "The passwords do not match");
                return;
            }

            Account account = new Account();
            account.setAccountName(view.getUsernameField());
            account.setAccountPassword(view.getPasswordField());
            try{
                AccountModel.insert(account);

                JOptionPane.showMessageDialog(view,"Sign up successfully!");

                view.dispose();

            }catch(Exception exception){
                throw  exception;
            }
        }
    }
}
