package cyc.RPC.Framework;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServices {

    public static void main( String args[ ]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started...");
        //开启注册服务监听端口
        //ServicesRegister.run();


        while (true) {
            System.out.println(" 开启请求服务监听端口");
            Socket socket = serverSocket.accept();
            RemoteServicesHandler remoteServicesHandler = new RemoteServicesHandler(socket);
            remoteServicesHandler.register("RPCServiceImp", new RPCServiceImp());
            new Thread(remoteServicesHandler).start();
        }
    }
}


