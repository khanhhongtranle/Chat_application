package Views_ver2;

import Client.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatBoxView extends JFrame implements MessageListener {

    public static List<ChatBox> listChatBox = new ArrayList<>();

    private JPanel panel1;
    private JScrollPane scrollPane;
    private JList<String> list1;
    private JTextPane textPane1;
    private JButton sendButton;
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    private final Client client;
    private final String login;

    public ChatBoxView(Client client, String login){

        listChatBox.add(new ChatBox(client.getUsername(), login));

        this.client = client;
        this.login = login;
        this.client.addMessageListener(this);

        this.setTitle(login);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        panel1.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        panel1.setPreferredSize(new Dimension(600, 400));

        list1.setModel(listModel);
        scrollPane.setViewportView(list1);

        this.setContentPane(panel1);
        this.pack();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getInputText().equals("")){
                    return;
                }

                /*for (ChatBox cb : listChatBox){
                    if (!cb.getReceiver().equalsIgnoreCase(client.getUsername())
                        && !cb.getSender().equalsIgnoreCase(login)){
                        //open new chat box window

                    }
                }*/

                try{
                    client.msg(login,getInputText());
                    listModel.addElement("You: " + getInputText());
                    textPane1.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public String getInputText(){
        return textPane1.getText();
    }

    @Override
    public void onMessage(String fromLogin, String msgBody) {
        if (login.equalsIgnoreCase(fromLogin)){
            String line = fromLogin + ": " + msgBody;
            listModel.addElement(line);
        }
    }

    @Override
    public void openChatBox(String sender, String receive) {

    }
}