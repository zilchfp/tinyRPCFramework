package Bank;

import Bank.Server.CustomerAccount;

public class BankServiceStart {
    public static void main(String args[ ]) throws Exception {
        RPCFramework.export(new CustomerAccount(), 8000);
    }
}
