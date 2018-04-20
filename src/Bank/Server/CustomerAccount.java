package Bank.Server;

import Bank.RemoteServicesInterface;
import Bank.ServicesInterface;

import java.rmi.RemoteException;
import java.util.Map;

public class CustomerAccount implements RemoteServicesInterface {
    private static Account account;
    private static Map<String, Account> accountSet = AccountSet.getInstance();

    @Override
    public boolean Login(String usrname, String password) {
        boolean loginResult;
        Account loginingAccount = (Account) accountSet.get(usrname);
        String targetPassword = loginingAccount.getPassword();
        loginResult = targetPassword.equals(password);
        if (loginResult) {
            this.account = loginingAccount;
        } else {
            this.account = null;
        }
        System.out.println("login chekc....");
        checkAccountSet();
        return loginResult;
    }

    @Override
    public double queryBalance() {
        checkAccountSet();
        return account.getBalance();
    }

    @Override
    public boolean checkAccountExist(String username) {
        Map accountSet = AccountSet.getInstance();
        return !(accountSet.get(username) == null);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello Word!";
    }

    public CustomerAccount() {
        addTestAccount();
    }

    @Override
    public boolean deposit(String username, double money) {
        Account account = (Account) accountSet.get(username);
        if (account == null) {
            System.out.println("找不到账户："+username);
        } else {
            account.setBalance(account.getBalance() + money);
            System.out.println("存入： "+money);
            return true;
        }
        return  false;
    }

    @Override
    public boolean withdraw(String username, double money) {
        Account account = (Account) accountSet.get(username);
        if (money > account.getBalance()) return false;
        account.setBalance(account.getBalance() - money);
        updateAccountSet(account);
        System.out.println("取出: "+money);
        return true;
    }

    @Override
    public boolean transfer(String username, double money) {
        return false;
    }

    private void updateAccountSet(Account account) {
        accountSet.put(account.getName() ,account);
    }
    private void checkAccountSet() {
        System.out.println("此时AccountSet里有：");
        for (Account entry : accountSet.values()) {
            System.out.println(entry.getName() + "  " + entry.getBalance());
        }
        System.out.println();
    }

    private void addTestAccount(){
        Account testAccount = new Account("1","1");
        accountSet.put("1",testAccount);
        accountSet.put("2", new Account("2","2"));
        updateAccountSet(testAccount);
    }
}
