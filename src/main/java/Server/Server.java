package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private final int serverPort;

    private ArrayList<ServerWorker> workerList = new ArrayList<>();

    public Server(int serverPort){
        this.serverPort = serverPort;
    }

    public List<ServerWorker> getWorkerList(){
        return workerList;
    }

    public void removeWorker(ServerWorker serverWorker){
        workerList.remove(serverWorker);
    }

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while(true){
                System.out.println("About to accept client connection...");

                Socket clientSocket = serverSocket.accept();

                System.out.println("Accpected connection from " + clientSocket);

                ServerWorker serverWorker = new ServerWorker(this,clientSocket);
                workerList.add(serverWorker);
                serverWorker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
