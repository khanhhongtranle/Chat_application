package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JPanel panel;
    private JPanel subPanel;
    private JPanel subPanel2;
    private JLabel label;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton signUpBtn;

    public LoginView(){
        //set up frame
        this.setTitle("Chatting");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        //init components
        //---panel
        panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        //--subpanel
        subPanel = new JPanel(new FlowLayout());
        subPanel2 = new JPanel(new GridLayout(2, 2));
        //--labels
        label = new JLabel("LOGIN");
        labelUsername = new JLabel("Username:");
        labelPassword = new JLabel("Password:");
        //--textfields
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        //--buttons
        loginBtn = new JButton("Login");
        signUpBtn = new JButton("Sign Up");

        //add to panel
        panel.add(label);
        label.setBorder(new EmptyBorder(new Insets(10,0,10,0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        subPanel2.add(labelUsername);
        subPanel2.add(usernameField);
        subPanel2.add(labelPassword);
        subPanel2.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(10,20)));
        panel.add(subPanel2);
        subPanel.add(loginBtn);
        subPanel.add(signUpBtn);
        panel.add(Box.createRigidArea(new Dimension(10,20)));
        panel.add(subPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add to frame
        this.add(panel);
        this.pack();
        this.setSize(300,200);
    }

    public void loginButtonListener(ActionListener listener){
        loginBtn.addActionListener(listener);
    }

    public void signUpButtonListener(ActionListener listener){
        signUpBtn.addActionListener(listener);
    }

    public String getUsernameField(){
        return usernameField.getText();
    }

    public String getPasswordField(){
        return new String(passwordField.getPassword());
    }
}
