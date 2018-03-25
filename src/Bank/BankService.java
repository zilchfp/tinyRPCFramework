package Bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankService {
    int Balance;
    ServerSocket serverSocket;
    BankService() {
        this.Balance = 0;
    }

    public void initialie() throws IOException {
        System.out.println("Server started...");
        this.serverSocket = new ServerSocket(8000);
    }

    public Socket getAcceptSocket() throws IOException {
        return this.serverSocket.accept();
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
