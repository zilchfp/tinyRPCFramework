package Bank;

import Bank.Client.CustomerClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CustomerClientStart {
    public static void main (String args[] ) throws Exception {

        CustomerClient customerClient = new CustomerClient();
        customerClient.run();

    }
}
