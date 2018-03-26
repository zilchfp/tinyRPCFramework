package Bank.Server;

import java.util.HashMap;
import java.util.Map;

public class RemoteObjectsSingleton {
    private static Map remoteObjects;
    private RemoteObjectsSingleton(){}
    public static synchronized  Map getInstance() {
        if (remoteObjects == null) {
            remoteObjects = new HashMap();
        }
        return remoteObjects;
    }
}
