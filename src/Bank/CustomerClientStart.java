package Bank;

public class CustomerClientStart {
    public static void main (String args[] )throws Exception {
        new CustomerClient().invoke("RPCServiceImp", "getInformation",
                new Class[]{String.class}, new Object[]{"---测试 RPC！"});
    }
}
