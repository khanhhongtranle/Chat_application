package Controllers;

import Entities.Account;
import Views.ChatBoxView;

public class ChatBoxController {
    private ChatBoxView view;
    private Account yourAcc;
    private Account hisAcc;

    public ChatBoxController(Account yourAcc, Account hisAcc){
        this.yourAcc = yourAcc;
        this.hisAcc = hisAcc;

        view = new ChatBoxView(yourAcc,hisAcc);


    }

    public void setVisibleView(boolean b){
        this.view.setVisible(b);
    }
}
