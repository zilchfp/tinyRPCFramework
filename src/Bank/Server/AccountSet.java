package Bank.Server;

import Bank.Server.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountSet {
    private static Map AccountMap;
    private AccountSet(){}
    public static synchronized  Map getInstance() {
        if (AccountMap == null) {
            AccountMap = new HashMap();
        }
        return AccountMap;
    }
    public static boolean addAccount(Account account) {
        AccountMap.put(account.getName(), account.getPassword());
        boolean addResult;
        if (AccountMap.get(account.getName()) == null) {
            addResult = false;
        } else {
            addResult = true;
        }
        return addResult;
    }
}
