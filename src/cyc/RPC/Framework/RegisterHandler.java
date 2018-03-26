package cyc.RPC.Framework;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RegisterHandler implements Runnable {
    private Socket socket;
    private Map remoteObjects = new HashMap();
    private ObjectInputStream ois ;
    private OutputStream out ;
    private ObjectOutputStream oos ;
    private InputStream in ;

    public RegisterHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
        this.ois = new ObjectInputStream(in);
        this.out = socket.getOutputStream();
        this.oos = new ObjectOutputStream(out);
    }

    public void register( String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
        System.out.println("className: "+ className + " regested");
    }

    @Override
    public void run() {
        try {
            //接收客户发送的注册对象
            Class newObjectClass = (Class) ois.readObject();
            // 注册新的对象
            String className = newObjectClass.getName();
            Object newInstance = newObjectClass.getInterfaces();

            this.register(className, newInstance);

        //返回注册结果
        Boolean registResult;
        if (remoteObjects.get(className) == null) {
            registResult = false;
        } else {
            registResult = true;
        }
        oos.writeObject(registResult);
        ois.close();
        oos.close();
        socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
