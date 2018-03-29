package Bank.Server;

public class Account {
    String name;
    String password;
    double balance;
    public Account(){}
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        this.balance = 0;
    }

    public boolean createAccount(String name, String password) {
        Account newAccount = new Account(name,password);
        AccountSet.getInstance().put(name,newAccount);
        return true;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
