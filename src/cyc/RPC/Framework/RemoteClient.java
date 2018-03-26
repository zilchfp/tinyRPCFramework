package cyc.RPC.Framework;

import java.io.*;
import java.net.Socket;


public class RemoteClient {
    public Boolean registerNewServices(Object newObject) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 9000);
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        InputStream in = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        oos.writeObject(newObject.getClass());
        //接收新服务注册结果
        Boolean registerResult = (Boolean) ois.readObject();
        if (registerResult) {
            System.out.println("新服务注册成功！");
        } else {
            System.out.println("新服务注册成功！");
        }
        ois.close();
        oos.close();
        socket.close();
        return  registerResult;
    }
    public void invoke(String className , String methodName, Class[]paramTypes, Object[] params) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        InputStream in = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        RemoteCall call = new RemoteCall(className ,methodName , paramTypes, params);// 向服务器发送Call 对象
        oos.writeObject(call);
        //接收包含了方法执行结果的Call对象
        call = (RemoteCall) ois.readObject();
        call.call();

        ois.close();
        oos.close();
        socket.close();
    }
    public void invoke() throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        InputStream in = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);

        RemoteCall call = new RemoteCall("RPCServiceImp", "getInformation", new Class[]{String.class}, new Object[]{"测试 RPC！"});// 向服务器发送Call 对象
        oos.writeObject(call);
        //接收包含了方法执行结果的Call对象
        call = (RemoteCall) ois.readObject();
        call.call();

        ois.close();
        oos.close();
        socket.close();
    }
    public static void main (String args[] )throws Exception {
        RemoteClient remoteClient = new RemoteClient();
        remoteClient.registerNewServices(new MyNewTestClass());
        remoteClient.invoke() ;
        //remoteClient.invoke("MyNewTestClass", "getInformation", new Class[]{String.class}, new Object[]{"new new new"});
    }
}
