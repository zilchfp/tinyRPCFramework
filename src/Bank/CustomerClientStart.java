package Bank;

import Bank.Client.RemoteObjectsCall;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CustomerClientStart {
    public static void main (String args[] )throws Exception {
        System.out.println("欢迎进入银行系统！请先登录！");

        boolean hasLogin = false;
        do {
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入银行账号：");
            String username = scanner.readLine();
            System.out.println("请输入银行密码：");
            String password = scanner.readLine();
            hasLogin = (boolean) new RemoteObjectsCall().invoke("CustomerAccount", "Login",
                    new Class[]{String.class, String.class}, new Object[]{username, password});
            if (hasLogin == false) System.out.println("密码错误！请重新登录！");
        } while (!hasLogin);
        if (hasLogin) System.out.println("登录成功！");

        int operation;
        do {
            System.out.println("请选择相应操作的序号：");
            System.out.println("请选择需要进行的操作：");
            System.out.println("1.存款");
            System.out.println("2.取款");
            System.out.println("3.转账");
            System.out.println("4.查询余额");
            System.out.println("5.退出");

            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            operation = Integer.parseInt(scanner.readLine());
            if (operation == 1) {


            } else if (operation == 2) {

            } else if (operation == 3) {

            } else if (operation == 4) {

            }
        } while (operation != 5);



        new RemoteObjectsCall().invoke("CustomerAccount", "deposit",
                new Class[]{int.class}, new Object[]{100});
    }
}
