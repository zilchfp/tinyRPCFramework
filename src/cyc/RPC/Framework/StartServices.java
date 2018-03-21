package cyc.RPC.Framework;


import java.net.ServerSocket;
import java.net.Socket;

public class StartServices {

    public static void main( String args[ ]) throws Exception {
        Server server = new Server() ;
        //注册服务：把事先创建的RemoteServceImpl 对象加入到服务器的缓存中
        //server.register(" RPCServiceImpl", new RPCServiceImpl( ));
        //server.service( );
        //尝试多线程
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket socket = serverSocket.accept();
            RemoteServicesHandler remoteServicesHandler = new RemoteServicesHandler(socket);
            new Thread(remoteServicesHandler).start();
            System.out.println("remoteServicesHandler started");
        }


    }
}


