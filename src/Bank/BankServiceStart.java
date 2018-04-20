package Bank;

import Bank.Server.CustomerAccount;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BankServiceStart {
    public static void main(String args[ ]) throws Exception {
        //RPCFramework.export(new CustomerAccount(), 8000);

        //RMI
        RemoteServicesInterface CustomerAccount = new CustomerAccount();
        RemoteServicesInterface stub = (RemoteServicesInterface) UnicastRemoteObject.exportObject(CustomerAccount, 9999);
        LocateRegistry.createRegistry(1099);
        Registry registry=LocateRegistry.getRegistry();
        registry.bind("BankServices", stub);
        System.out.println("绑定成功!");
    }
}
