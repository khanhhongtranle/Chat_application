package Views_ver2;

import Client.Client;
import Client.UserStatusListener;
import Entities.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatRoomView extends JFrame implements UserStatusListener  {
    private JPanel panel1;
    private JList<String> list1;
    private JButton LOGOUTButton;
    private JScrollPane scrollPane;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel hiLabel;

    private final Client client;
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ChatRoomView(Client client1){
        this.client = client1;
        client.addUserStatusListener(this);

        this.setTitle("Chat room");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel1.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        panel1.setPreferredSize(new Dimension(500, 500));

        list1 = new JList<>();
        list1.setModel(listModel);
        scrollPane.setViewportView(list1);

        hiLabel.setText(hiLabel.getText() + client.getUsername());

        this.setContentPane(panel1);
        this.pack();

        list1.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1){
                    String sendTo = list1.getSelectedValue();
                    ChatBoxView chatBoxView = new ChatBoxView(client, sendTo);
                    chatBoxView.setVisible(true);
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
        });

        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    client.logoff();
                    dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    @Override
    public void online(String login) {
        listModel.addElement(login);
    }

    @Override
    public void offline(String login) {
        listModel.removeElement(login);
    }
}
