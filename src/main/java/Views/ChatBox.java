package Views;

public class ChatBox {
    protected String sender;
    protected String receiver;

    public ChatBox(String s, String r){
        this.sender = s;
        this.receiver = r;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
}
