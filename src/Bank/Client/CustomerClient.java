package Bank.Client;

import Bank.RPCFramework;
import cyc.RPC.Framework.RemoteCall;

import java.io.*;
import java.net.Socket;

public class CustomerClient {
    private ServicesInterface servicesInterface;

    public void run() throws Exception {
        servicesInterface = RPCFramework.refer(ServicesInterface.class, "127.0.0.1",8000);
        BankCustomerGUI GUI = new BankCustomerGUI(servicesInterface);
        GUI.login();
        int operation;
        do {

            System.out.println("*******************************************************");
            System.out.println("请选择需要进行的操作序号：");
            System.out.println("1.存款");
            System.out.println("2.取款");
            System.out.println("3.转账");
            System.out.println("4.查询余额");
            System.out.println("5.退出");
            System.out.println("*******************************************************");

            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            operation = Integer.parseInt(scanner.readLine());
            if (operation == 1) {
                GUI.deposit();
            } else if (operation == 2) {
                GUI.withdraw();
            } else if (operation == 3) {
                GUI.transfer();
            } else if (operation == 4) {
                GUI.queryBalance();
            }
        } while (operation != 5);
    }
}
