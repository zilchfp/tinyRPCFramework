package cyc.RPC.Framework;



public class StartServices {

    public static void main( String args[ ]) throws Exception {
        Server server = new Server() ;
        //注册服务：把事先创建的RemoteServceImpl 对象加入到服务器的缓存中
        server.register(" RPCService",new RPCServiceImpl( ));
        server.service( );

    }

}


