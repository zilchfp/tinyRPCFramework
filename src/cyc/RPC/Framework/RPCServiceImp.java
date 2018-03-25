package cyc.RPC.Framework;

interface RPCService {
    public String getInformation(String s);

}

public class RPCServiceImp implements  RPCService {
    @Override
    public String getInformation(String s) {
        System.out.println("[Message from getInfo]收到发来的信息了！："+s);
        return ("收到发来的信息："+s);
    }
}

