package cyc.RPC.Framework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServicesRegister {
    public static void run() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket registerSocket = null;
                try {
                    System.out.println(" 开启注册新服务监听端口:9000");
                    registerSocket = new ServerSocket(9000);
                    while (true) {
                        Socket socket = registerSocket.accept();
                        RegisterHandler registerHandler = new RegisterHandler(socket);
                        new Thread(registerHandler).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
