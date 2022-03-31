package qq.com.zl.service;

import java.util.HashMap;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class MannerClientMessageThred {
    private static HashMap<String, ColientConectServerThred> sockets = new HashMap<>();

    public static void addThread(String userID, ColientConectServerThred colientConectServerThred) {
        sockets.put(userID, colientConectServerThred);
    }

    public static ColientConectServerThred getThread(String userID) {
        return sockets.get(userID);
    }

}
