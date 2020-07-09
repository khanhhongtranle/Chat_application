import Controllers.LoginController;
import Views_ver2.LoginView;

import java.awt.*;

public class App_2 {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //LoginController controller = new LoginController();
                //controller.setVisibleView(true);
                //ChatRoomController chatRoomController = new ChatRoomController(Account.list.get(0));
                //chatRoomController.setVisibleView(true);

                //ChatBoxController chatBoxController = new ChatBoxController(Account.list.get(0), Account.list.get(2));
                //chatBoxController.setVisibleView(true);

                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }
}