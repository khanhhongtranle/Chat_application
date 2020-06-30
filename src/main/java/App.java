import Controllers.LoginController;
import Views.LoginView;

import java.awt.*;

public class App {
    public static void main(String[] agrs){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginController controller = new LoginController();
                controller.setVisibleView(true);
            }
        });
    }
}
