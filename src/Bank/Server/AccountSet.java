package Bank.Server;

import java.util.HashMap;
import java.util.Map;

public class AccountSet {
    private static Map<String, Account> AccountMap;
    private AccountSet(){}
    public static synchronized  Map getInstance() {
        if (AccountMap == null) {
            AccountMap = new HashMap();
        }
        return AccountMap;
    }

    public static Account getAccount(String username) {
        return (Account) getInstance().get(username);
    }
}
