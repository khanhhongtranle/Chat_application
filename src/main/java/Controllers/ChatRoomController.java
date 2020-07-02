package Controllers;

import Entities.Account;
import Models.AccountModel;
import Views.ChatBoxView;
import Views.ChatRoomView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class ChatRoomController {
    private ChatRoomView view;
    private Account currAcc;

    public ChatRoomController(Account loginedAcc){
        this.currAcc = loginedAcc;

        this.view =  new ChatRoomView(currAcc);

        setVisibleView(true);

        this.view.exitButtonListener(new ExitListener());
        this.view.addMouseListener(new ListUserListener());
    }

    public void setVisibleView(boolean b){
        view.setVisible(b);
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
