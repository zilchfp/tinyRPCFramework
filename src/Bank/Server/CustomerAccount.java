package Bank.Server;

import java.util.Map;

public class CustomerAccount {
    private Account account;
    private Integer balance;

    public static boolean Login(String usrname, String password) {
        Map accountSet = AccountSet.getInstance();
        accountSet.put("1","1");
        String targetPassword = (String)accountSet.get(usrname);
        if (targetPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public CustomerAccount() {

        this.balance = 0;

    }

    public boolean deposit (int credit) {
        this.balance += credit;
        System.out.println("balance: "+balance);
        return true;
    }

}
