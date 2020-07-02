package Server;

public class ServerMain {

    public static void main (String[] args){
        int port = 7070;
        Server server = new Server(port);
        server.start();
    }
}
