package cyc.RPC.Framework;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RemoteServicesHandler implements Runnable {
    private Socket socket;
    private Map remoteObjects = new HashMap();

    RemoteServicesHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 创建基于流的Socket,并在8000 端口监听
        System.out.println(" 服务器启动......");
        InputStream in = null;
        try {
            in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);

            //接收客户发送的Call 对象
            System.out.println("接收客户发送的Call对象");
            RemoteCall remotecallobj = (RemoteCall) ois.readObject();
            System.out.println(remotecallobj);
            // 调用相关对象的方法
            System.out.println("调用相关对象的方法");
            remotecallobj = invoke(remotecallobj);
            // 向客户发送包含了执行结果的remotecallobj 对象
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
            Class classType = Class.forName(className);
            Class[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);
            // 从缓存中取出相关的远程对象Object
            Object remoteObject = remoteObjects.get(className);
            System.out.println("class name: "+className);
            if (remoteObject == null) {
                throw new Exception(className + " 的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
        }
        call.setResult(result);
        return call;
    }

}
