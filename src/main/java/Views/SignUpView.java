package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpView extends JFrame {

    private JPanel panel;
    private JPanel subPanel;
    private JLabel label;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel re_enterPassLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField rePasswordField;
    private JButton submitButton;

    public SignUpView(){
        //set up frame
        this.setTitle("Sign up");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(10,10,10,10);

        //init components
        //--panel
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        //--subpanel
        subPanel  = new JPanel(new GridLayout(3, 2));
        //--labels
        label = new JLabel("Welcome to chatting world...");
        usernameLabel = new JLabel("Your username:");
        passwordLabel = new JLabel("Your password:");
        re_enterPassLabel = new JLabel("Your password again:");
        //--fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        rePasswordField = new JPasswordField();
        //--button
        submitButton = new JButton("Submit");

        //add to panel
        panel.add(label);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(new Insets(10,0,10,0)));

        subPanel.add(usernameLabel);
        subPanel.add(usernameField);
        subPanel.add(passwordLabel);
        subPanel.add(passwordField);
        subPanel.add(re_enterPassLabel);
        subPanel.add(rePasswordField);

        panel.add(subPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));

        panel.add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0,20)));

        //add to frame
        this.add(panel);
        this.pack();
        this.setSize(new Dimension(400,250));
    }

    public void submitButtonListener(ActionListener listener){
        submitButton.addActionListener(listener);
    }

    public String getUsernameField(){
        return usernameField.getText();
    }

    public String getPasswordField(){
        return new String(passwordField.getPassword());
    }

    public String getRePasswordField(){
        return new String(rePasswordField.getPassword());
    }
}
