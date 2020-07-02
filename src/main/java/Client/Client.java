package Client;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;

    private ArrayList<UserStatusListener> userStatusListeners = new ArrayList<>();
    private ArrayList<MessageListener> messageListeners = new ArrayList<>();

    public Client(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 7070);

        client.addUserStatusListener(new UserStatusListener() {
            @Override
            public void online(String login) {
                System.out.println("Online: " + login );
            }

            @Override
            public void offline(String login) {
                System.out.println("Offline: " + login);
            }
        });

        client.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(String fromLogin, String msgBody) {
                System.out.println("You got a message from " + fromLogin + " === > " + msgBody);
            }
        });

        if (!client.connect()){
            System.out.println("Connect failed");
        }
        else{
            System.out.println("Connect successful");
        }

        if (client.login("guest", "guest")){
            System.out.println("Login successful");

            client.msg("jim" , "hello");
        }
        else{
            System.out.println("Login failed");
        }

        //client.logoff();
    }

    private void msg(String sendTo , String msgBody) throws IOException {
        String cmd = "msg " + sendTo + " " + msgBody + "\n";
        serverOut.write(cmd.getBytes());
    }

    private void addUserStatusListener(UserStatusListener listener) {
        userStatusListeners.add(listener);
    }

    private void removeUserStatusListener(UserStatusListener listener){
        userStatusListeners.remove(listener);
    }

    private void addMessageListener(MessageListener listener){
        messageListeners.add(listener);
    }

    private void removeMessageListener(MessageListener listener){
        messageListeners.remove(listener);
    }

    public boolean connect() {
        try{
            this.socket = new Socket(serverName, serverPort);
            System.out.println("Client port is " + socket.getLocalPort());
            this.serverOut = socket.getOutputStream();
            this.serverIn = socket.getInputStream();
            this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
            return true;
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean login(String login, String password) throws IOException {
        String cmd = "login " + login + " " + password + "\n";
        serverOut.write(cmd.getBytes());

        String response = bufferedIn.readLine();
        System.out.println("Response line: " + response);

        if (response.equalsIgnoreCase("ok login")){
            startMessageReader();
            return true;
        }
        else{
            return false;
        }
    }

    private void logoff() throws IOException {
        String cmd = "logoff\n";
        serverOut.write(cmd.getBytes());
    }

    private void startMessageReader(){
        Thread t = new Thread(){
            @Override
            public void run() {
                readMessageLoop();
            }
        };
        t.start();
    }

    private void readMessageLoop(){
        try{
            String line;
            while ( (line = bufferedIn.readLine()) !=null){
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0){
                    String cmd = tokens[0];
                    if (cmd.equalsIgnoreCase("online")){
                        handleOnline(tokens);
                    }
                    else if (cmd.equalsIgnoreCase("offline")){
                        handleOffline(tokens);
                    }
                    else if (cmd.equalsIgnoreCase("msg")){
                        String[] tokensMsg = StringUtils.split(line,null,3);
                        handleMessage(tokensMsg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void handleOnline(String[] tokens){
        String login = tokens[1];
        for (UserStatusListener listener : userStatusListeners){
            listener.online(login);
        }
    }

    private void handleOffline(String[] tokens){
        String login = tokens[1];
        for (UserStatusListener listener : userStatusListeners){
            listener.offline(login);
        }
    }

    private void handleMessage(String[] tokensMsg){
        String login = tokensMsg[1];
        String msgBody = tokensMsg[2];

        for (MessageListener listener: messageListeners){
            listener.onMessage(login, msgBody);
        }
    }
}
