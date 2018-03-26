package cyc.RPC.Framework;

import java.io.Serializable;

public class MyNewTestClass implements RPCService ,Serializable{
    @Override
    public String getInformation(String s) {
        String test = "MyNewTestClass  +  "+s;
        System.out.println(test);
        return test;
    }
}
