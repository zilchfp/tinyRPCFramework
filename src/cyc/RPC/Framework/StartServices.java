package cyc.RPC.Framework;


import java.net.ServerSocket;
import java.net.Socket;

public class StartServices {

    public static void main( String args[ ]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started...");

        while (true) {
            Socket socket = serverSocket.accept();
            RemoteServicesHandler remoteServicesHandler = new RemoteServicesHandler(socket);
            remoteServicesHandler.register(" RPCServiceImp2", new RPCServiceImpl());
            new Thread(remoteServicesHandler).start();
        }
    }
}


