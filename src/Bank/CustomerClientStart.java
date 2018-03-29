package Bank;

import Bank.Client.CustomerClient;
public class CustomerClientStart {
    public static void main (String args[] ) throws Exception {
        CustomerClient customerClient = new CustomerClient();
        customerClient.run();
    }
}
