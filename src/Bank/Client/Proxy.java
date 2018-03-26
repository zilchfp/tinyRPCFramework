package Bank.Client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {
/*
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();    //1.创建委托对象
        ProxyHandler handler = new ProxyHandler(realSubject);   //2.创建调用处理器对象
        Subject proxySubject = (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);    //3.动态生成代理对象
        proxySubject.request(); //4.通过代理对象调用方法
    }
*/

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object object = method.invoke(proxy, args);

        return null;
    }
}
