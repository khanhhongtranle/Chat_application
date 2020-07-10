import Views.LoginView;

import java.awt.*;

public class App_2 {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }
}