package cyc.RPC.Framework;


public class RPCServiceImpl implements  RPCService {
    @Override
    public String getInformation(String s) {
        System.out.println("收到发来的信息："+s);
        return ("收到发来的信息："+s);
    }
}
