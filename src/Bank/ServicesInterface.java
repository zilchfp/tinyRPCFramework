package Bank;

public interface ServicesInterface {
    public boolean Login(String username, String password);
    public boolean deposit(String username, double money);
    public boolean withdraw(String username, double money);
    public boolean transfer(String username, double money);
    public double queryBalance();
    public boolean checkAccountExist(String username);

}
