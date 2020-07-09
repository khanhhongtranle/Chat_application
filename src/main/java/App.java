import Controllers.ChatBoxController;
import Controllers.ChatRoomController;
import Controllers.LoginController;
import Entities.Account;
import Views_ver2.LoginView;

import java.awt.*;

public class App {
    public static void main(String[] agrs){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }
}
