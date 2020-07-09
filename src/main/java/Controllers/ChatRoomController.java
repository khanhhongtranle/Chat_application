package Controllers;

import Client.Client;
import Entities.Account;
import Models.AccountModel;
import Views.ChatBoxView;
import Views.ChatRoomView;
import Client.UserStatusListener;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatRoomController implements UserStatusListener {
    private ChatRoomView view;
    private Account currAcc;

    private final Client client;

    public ChatRoomController(Client client,Account loginedAcc){
        this.client = client;
        this.client.addUserStatusListener(this);
        this.currAcc = loginedAcc;

        this.view =  new ChatRoomView(currAcc);

        this.view.exitButtonListener(new ExitListener());
        this.view.addMouseListener(new ListUserListener());

        /*if (client.connect()){
            try{
                client.login(currAcc.getAccountName(), currAcc.getAccountPassword());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        client.connect();
    }

    public void setVisibleView(boolean b){
        view.setVisible(b);
    }

    @Override
    public void online(String login) {
        view.getListModel().addElement(currAcc.getAccountName());
    }

    @Override
    public void offline(String login) {
        view.getListModel().removeElement(currAcc.getAccountName());
    }


    private class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();

            LoginController loginController = new LoginController();
            loginController.setVisibleView(true);
        }
    }

    private class ListUserListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JList list = (JList)e.getSource();
            if (e.getClickCount() == 2) {
                // Double-click detected
                int index = list.locationToIndex(e.getPoint());
                System.out.println(list.getSelectedValue());
                Account hisAcc = AccountModel.select(list.getSelectedValue().toString());
                ChatBoxController chatBoxController = new ChatBoxController(currAcc, hisAcc);
                chatBoxController.setVisibleView(true);
            }
            else if (e.getClickCount() == 3) {
                // Triple-click detected
                int index = list.locationToIndex(e.getPoint());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
