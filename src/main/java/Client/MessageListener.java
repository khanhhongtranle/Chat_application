package Client;

public interface MessageListener {
    void onMessage(String fromLogin, String msgBody);
    void openChatBox(String sender, String receive);
}
