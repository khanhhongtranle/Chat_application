package Views;

import Entities.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatBoxView extends JFrame {

    private JPanel panel;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    private JList listChat;
    private JPanel subPanel;
    private JLabel label;
    private JTextArea textArea;
    private JButton sendButton;

    public ChatBoxView(Account yourAcc, Account hisAcc){
        //set up frame
        this.setTitle(hisAcc.getAccountName());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        //init components
        //--panel
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        //
        subPanel = new JPanel(new FlowLayout());
        //--chat box
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listChat = new JList(listModel);
        scrollPane = new JScrollPane(listChat);
        //--label
        label = new JLabel("Press your message...");
        //--text area
        textArea = new JTextArea();
        textArea.setBorder(new EmptyBorder(new Insets(20,180,20,180)));
        scrollPane1 = new JScrollPane(textArea);
        //--button
        sendButton = new JButton("SEND");

        //add to panel
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0,10)));

        subPanel.add(scrollPane1);
        subPanel.add(sendButton);

        panel.add(label);
        label.setBounds(5,5,10,10);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(subPanel);
        panel.add(Box.createRigidArea(new Dimension(0,10)));

        //add to frame
        this.add(panel);
        this.pack();
        this.setSize(500,300);
    }
}
