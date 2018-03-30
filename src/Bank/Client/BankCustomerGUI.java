package Bank.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankCustomerGUI {
    private ServicesInterface servicesInterface;
    private String loginUsername;
    public BankCustomerGUI(ServicesInterface servicesInterface) {
        this.servicesInterface = servicesInterface;
    }

    public void login() throws IOException {
        System.out.println("欢迎进入银行系统！请先登录！");
        boolean hasLogin = false;
        while (!hasLogin) {
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入银行账号：");
            String username = scanner.readLine();
            System.out.println("请输入银行密码：");
            String password = scanner.readLine();
            hasLogin = servicesInterface.Login(username, password);
            if (!hasLogin) {
                System.out.println("账号或密码错误！请重新登录！");
            } else {
                System.out.println("登录成功！");
                loginUsername = username;
            }
        }
    }

    public void withdraw() throws IOException {
        boolean withdrawSuccessfully = false;
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        while (!withdrawSuccessfully) {
            System.out.println("请输入您要取出的金额：(单位：元)");
            String input = scanner.readLine();

            if (!checkValid(input)) {
                System.out.println("取款失败！请确保输入合法金额！请重试！");
                return;
            }

            double money = Double.parseDouble(input);

            if (servicesInterface.withdraw(loginUsername, money)) {
                System.out.println("取款成功！成功取出："+money+"元。");
                withdrawSuccessfully = true;
                queryBalance();
            } else {
                System.out.println("取款失败！账户余额不足！");
                withdrawSuccessfully = false;
            }
        }
    }

    public void deposit() throws IOException {
        boolean depositSuccessfully = false;
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        while (!depositSuccessfully) {
            System.out.println("请输入您要存款的金额：(单位：元)");
            String input = scanner.readLine();
            if (!checkValid(input)) {
                System.out.println("取款失败！请确保输入合法金额！请重试！");
                return;
            }

            double money = Double.parseDouble(input);
            System.out.println("即将存入："+loginUsername);
            if (servicesInterface.deposit(loginUsername, money)) {
                System.out.println("存款成功！成功存入："+money+"元");
                depositSuccessfully = true;
                queryBalance();
            } else {
                System.out.println("存款失败！请确保输入合法金额！请重试！");
                depositSuccessfully = false;
            }
        }
    }


    public void queryBalance() {
        double balance = servicesInterface.queryBalance();
        System.out.println("您当前的账户余额为："+balance+"元");
    }

    public void transfer() throws IOException {
        String targetUsername;
        String money;
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        boolean targetUserExist;
        while (true) {
            System.out.println("请输入您要转入的账户：");
            targetUsername = scanner.readLine();
            targetUserExist = servicesInterface.checkAccountExist(targetUsername);
            if (!targetUserExist) {
                System.out.println("您输入的用户不存在，请重试！");
            } else {
                break;
            }
        }

        boolean moneyValid;
        while (true) {
            System.out.println("请输入您要转入的金额：（单位：元）");
            money = scanner.readLine();
            moneyValid =  checkValid(money);
            if (moneyValid) {
                break;
            } else {
                System.out.println("您输入的金额不合法，请重试！");
            }
        }

        if (servicesInterface.withdraw(loginUsername, Double.parseDouble(money))) {
            if (servicesInterface.deposit(targetUsername, Double.parseDouble(money))) {
                System.out.println("转账成功！");
                queryBalance();
            } else {
                System.out.println("转账错误！无法存入目标账户！请重试！");
                //回滚取款操作
                servicesInterface.deposit(loginUsername, Double.parseDouble(money));
            }
        } else {
            System.out.println("转账错误，无法从账户转出金额！请重试！");
        }

    }

    private boolean checkValid(String input) {
        return (input.matches("[0-9.]*"));
    }
}
