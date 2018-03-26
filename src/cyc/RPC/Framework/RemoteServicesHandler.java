package cyc.RPC.Framework;

import Bank.Server.RemoteObjectsSingleton;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class RemoteServicesHandler implements Runnable {
    private Socket socket;
    private Map remoteObjects;
    private ObjectInputStream ois ;
    private OutputStream out ;
    private ObjectOutputStream oos ;
    private InputStream in ;

    public RemoteServicesHandler(Socket socket) throws IOException {
        this.remoteObjects = RemoteObjectsSingleton.getInstance();
        this.socket = socket;
        this.in = socket.getInputStream();
        this.ois = new ObjectInputStream(in);
        this.out = socket.getOutputStream();
        this.oos = new ObjectOutputStream(out);
    }
    public void register( String className,Object remoteObject) {
        remoteObjects.put(className, remoteObject);
        System.out.println("className: "+ className + " regested");
    }
    @Override
    public void run() {
        System.out.println(" 服务启动......");
        try {
            //接收客户发送的Call 对象
            RemoteCall remotecallobj = (RemoteCall) ois.readObject();
            // 调用相关对象的方法
            remotecallobj = invoke(remotecallobj);
            // 向客户发送包含了执行结果的remotecallobj 对象
            System.out.println("向客户端发送在服务器查询的结果");
            oos.writeObject(remotecallobj);

            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public RemoteCall invoke(RemoteCall call) {
        Object result = null;
        try {
            String className = call.getClassName();
            String methodName = call.getMethodName();
            Object[] params = call.getParams();
            Class classType = Class.forName("cyc.RPC.Framework."+className);
            Class[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);
            // 从缓存中取出相关的远程对象Object
            Object remoteObject = remoteObjects.get(className);
            if (remoteObject == null) {
                throw new Exception(className + " 的远程对象不存在");
            } else {
                System.out.println("remoteObject:"+ remoteObject);
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
            e.printStackTrace();
        }
        call.setResult(result);
        return call;
    }
}
