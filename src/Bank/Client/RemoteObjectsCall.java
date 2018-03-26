package Bank.Client;

import cyc.RPC.Framework.RemoteCall;

import java.io.*;
import java.net.Socket;

public class RemoteObjectsCall {
    String host;
    int portNumber;
    OutputStream out;
    ObjectOutputStream oos;
    InputStream in;
    ObjectInputStream ois;
    Socket socket;

    public RemoteObjectsCall() throws IOException {
        this.host = "127.0.0.1";
        this.portNumber = 8000;
        this.socket= new Socket(host, portNumber);
        this.out = socket.getOutputStream();
        this.oos = new ObjectOutputStream(out);
        this.in = socket.getInputStream();
        this.ois = new ObjectInputStream(in);
    }

    public void close() throws IOException {
        this.ois.close();
        this.oos.close();
        this.socket.close();
    }
    public Object invoke(String className , String methodName, Class[]paramTypes, Object[] params) throws Exception {
        RemoteCall call = new RemoteCall(className, methodName, paramTypes, params);
        oos.writeObject(call);
        //接收包含了方法执行结果的Call对象
        call = (RemoteCall) ois.readObject();
        Object object = (Object)call.call();
        this.close();
        return object;
    }
}
