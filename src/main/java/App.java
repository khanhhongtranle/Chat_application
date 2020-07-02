import Controllers.ChatBoxController;
import Controllers.ChatRoomController;
import Controllers.LoginController;
import Entities.Account;
import Views.LoginView;

import java.awt.*;

public class App {
    public static void main(String[] agrs){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginController controller = new LoginController();
                controller.setVisibleView(true);
                //ChatRoomController chatRoomController = new ChatRoomController(Account.list.get(0));
                //chatRoomController.setVisibleView(true);

                //ChatBoxController chatBoxController = new ChatBoxController(Account.list.get(0), Account.list.get(2));
                //chatBoxController.setVisibleView(true);
            }
        });
    }
}
