package Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServicesInterface extends Remote {
    public boolean Login(String username, String password) throws RemoteException;
    public boolean deposit(String username, double money) throws RemoteException;
    public boolean withdraw(String username, double money) throws RemoteException;
    public boolean transfer(String username, double money) throws RemoteException;
    public double queryBalance() throws RemoteException;
    public boolean checkAccountExist(String username) throws RemoteException;
    String sayHello() throws RemoteException;
}
