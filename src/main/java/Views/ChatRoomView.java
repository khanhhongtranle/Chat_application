package Views;

import Entities.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ChatRoomView extends JFrame {

    private JPanel panel;
    private JPanel subPanel;
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    private JList listUsers;
    private JScrollPane scrollPane;
    private JButton exitButton;

    DefaultListModel<String> listModel = new DefaultListModel<>();

    public ChatRoomView(Account account){
        // set up frame
        this.setTitle("Chat room");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        //init components
        //--panel
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        //
        subPanel = new JPanel(new FlowLayout());
        //--labels
        label = new JLabel("Chat room");
        label1 = new JLabel("Hello, " + account.getAccountName());
        label2 = new JLabel("Online users:");
        //--button
        exitButton = new JButton("Exit");
        //--list
        /*for(Account acc : Account.list){
            listModel.addElement(acc.getAccountName());
        }*/
        listUsers = new JList(listModel);
        listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listUsers.setLayoutOrientation(JList.VERTICAL_WRAP);
        listUsers.setVisibleRowCount(-1);
        scrollPane = new JScrollPane(listUsers);
        scrollPane.setBounds(100,100, 75,75);

        //add to panels
        panel.add(label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        subPanel.add(label1);
        subPanel.add(exitButton);

        panel.add(subPanel);
        panel.add(label2);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0,30)));

        //add to frame
        this.add(panel);
        this.pack();
        this.setSize(new Dimension(300,400));
    }

    public void exitButtonListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }

    public void addMouseListener(MouseListener listener){
        listUsers.addMouseListener(listener);
    }

    public JList getListUser(){
        return listUsers;
    }

    public DefaultListModel<String> getListModel(){
        return listModel;
    }
}
