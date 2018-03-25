package Bank;

import cyc.RPC.Framework.RPCServiceImp;
import cyc.RPC.Framework.RemoteServicesHandler;

import java.net.Socket;

public class BankServiceStart {
    public static void main(String args[ ]) throws Exception {
        BankService bankService = new BankService();
        bankService.initialie();

        while (true) {
            Socket socket = bankService.getAcceptSocket();
            RemoteServicesHandler remoteServicesHandler = new RemoteServicesHandler(socket);
            remoteServicesHandler.register("RPCServiceImp", new RPCServiceImp());
            new Thread(remoteServicesHandler).start();
        }
    }
}
