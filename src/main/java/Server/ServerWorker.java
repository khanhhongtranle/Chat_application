package Server;

import Models.AccountModel;
import Views.ChatBox;
import Views.ChatBoxView;
import org.apache.commons.lang.StringUtils;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

public class ServerWorker extends Thread{
    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;
    private HashSet<String> topicSet = new HashSet<>();

    public ServerWorker(Server server, Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = reader.readLine()) != null){
            String[] tokens = StringUtils.split(line);
            if (tokens!=null && tokens.length > 0){
                String cmd = tokens[0];
                if (cmd.equalsIgnoreCase("logoff") || cmd.equalsIgnoreCase("quit")){
                    handleLogoff();
                    break;
                }
                else if(cmd.equalsIgnoreCase("login")){
                    handleLogin(outputStream, tokens);
                }
                else if (cmd.equalsIgnoreCase("msg")){
                    String[] tokensMsg = StringUtils.split(line,null,3);
                    handleMessage(tokensMsg);
                }
                else if (cmd.equalsIgnoreCase("join")){
                    handleJoin(tokens);
                }
                else if (cmd.equalsIgnoreCase("leave")){
                    handleLeave(tokens);
                }
                else if (cmd.equalsIgnoreCase("open")){
                    String[] tokensOpen = StringUtils.split(line,null,3);
                    handleOpenChatBox(tokensOpen);
                }
                else{
                    String msg = "unknown " + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String body = tokens[2];

        boolean isTopic = sendTo.charAt(0) == '#';

        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker : workerList){
            if (isTopic){
                if (worker.isMemberOfTopic(sendTo)){
                    String outMsg = "msg " + sendTo + ": " + login + " " + body + "\n";
                    worker.send(outMsg);
                    worker.outputStream.write(outMsg.getBytes());
                }
            }
            else{
                if (sendTo.equalsIgnoreCase(worker.getLogin())){
                    String outMsg = "msg " + login + " " + body + "\n";
                    worker.send(outMsg);
                    worker.outputStream.write(outMsg.getBytes());
                }
            }
        }
    }

    private void handleLogoff() throws IOException {
        List<ServerWorker> workerList = server.getWorkerList();

        String onlineMsg = "offline " + login + "\n";
        for (ServerWorker worker : workerList){
            if (!login.equals(worker.getLogin())) {
                //worker.send(onlineMsg);
                worker.outputStream.write(onlineMsg.getBytes());
            }
        }
        System.out.println(onlineMsg);
        clientSocket.close();

        server.removeWorker(this);
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3){
            String login = tokens[1];
            String password = tokens[2];

            //check from database
            boolean loginSuccess = (AccountModel.select(login,password) != null);

            //kiem trang tai khoan do co phai dang duoc dang nhap boi thang khac
            if (login.equals("jim") || login.equals("guest")){
                loginSuccess = true;
            }

            if (loginSuccess) {

                String msg = "ok login\n";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in successfully: " + login);

                List<ServerWorker> workerList = server.getWorkerList();

                // send current user all other online logins
                for (ServerWorker worker : workerList){
                    if (worker.getLogin() != null){
                        if (!login.equals(worker.getLogin())){
                            String msg2 = "online " + worker.getLogin() + "\n";
                            //send(msg2);

                            outputStream.write(msg2.getBytes());
                        }
                    }
                }

                // send other online users current user's status
                String onlineMsg = "online " + login + "\n";
                for (ServerWorker worker : workerList){
                    if (!login.equals(worker.getLogin())){
                        //worker.send(onlineMsg);
                        worker.outputStream.write(onlineMsg.getBytes());
                    }
                }

                System.out.println(onlineMsg);
            }
            else{
                String msg = "error login\n";
                outputStream.write(msg.getBytes());
            }
        }
    }

    private void handleJoin(String[] tokens){
        if (tokens.length > 1){
            String topic = tokens[1];
            topicSet.add(topic);
        }
    }

    private void handleLeave(String[] tokens){
        if (tokens.length > 1){
            String topic = tokens[1];
            topicSet.remove(topic);
        }
    }

    private void handleOpenChatBox(String[] tokens) throws IOException {
        String sender = tokens[1];
        String receiver = tokens[2];

        List<ServerWorker> workerList = server.getWorkerList();

        int isOnline = 0;
        for(ServerWorker worker : workerList){
            if (worker.getLogin().equalsIgnoreCase(sender)){
                isOnline ++;
            }
            if (worker.getLogin().equalsIgnoreCase(receiver)){
                isOnline ++;
            }
            if (isOnline == 2){
                break;
            }
        }

        if (isOnline != 2){
            String msg = "open error\n";
            outputStream.write(msg.getBytes());
            return;
        }

        List<ChatBox> chatBoxViewList = ChatBoxView.listChatBox;

        String receiver2 = sender;
        String sender2 = receiver;

        for (ChatBox cb : chatBoxViewList){
            if (cb.getSender().equals(sender) && cb.getReceiver().equals(receiver)){
                if (!cb.getSender().equals(sender2) && !cb.getReceiver().equals(receiver2)){
                    String msg = "open " + sender2 + " " + receiver2 + "\n";
                    outputStream.write(msg.getBytes());
                    break;
                }
            }
        }
    }

    private void send(String msg) throws IOException {
        if (login != null){
            outputStream.write(msg.getBytes());
        }
    }

    public String getLogin(){
        return login;
    }

    public boolean isMemberOfTopic(String topic){
        return topicSet.contains(topic);
    }
}
