package Bank;

import Bank.Server.Account;
import Bank.Server.BankService;
import Bank.Server.CustomerAccount;
import cyc.RPC.Framework.RPCServiceImp;
import cyc.RPC.Framework.RemoteServicesHandler;

import java.net.Socket;

public class BankServiceStart {
    public static void main(String args[ ]) throws Exception {
        /*
        BankService bankService = new BankService();
        bankService.initialie();
        bankService.registerAllServices("RPCServiceImp", new RPCServiceImp());
        bankService.registerAllServices("CustomerAccount", new CustomerAccount());

        while (true) {
            Socket socket = bankService.getAcceptSocket();
            RemoteServicesHandler remoteServicesHandler = new RemoteServicesHandler(socket);
            new Thread(remoteServicesHandler).start();
        }
*/

        RPCFramework.export(new CustomerAccount(), 8000);

    }
}
