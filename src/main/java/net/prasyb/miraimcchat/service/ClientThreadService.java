package net.prasyb.miraimcchat.service;

import net.prasyb.miraimcchat.ModConfig;
import net.prasyb.miraimcchat.network.WebSocketClient;

public class ClientThreadService {
    public static WebSocketClient client;
    public static void runWebSocketClient() {
        if (client != null) {
            client.interrupt();
        }
        client = new WebSocketClient(
                ModConfig.HOST.get(),
                ModConfig.PORT.get(),
                ModConfig.KEY.get());
        client.start();
    }
    /**
     * @return {@code true}: 已存在客户端; {@code false}: 不存在客户端
     * */
    public static boolean stopWebSocketClient() {
        boolean isStopSuccessfully = false;
        if (client != null) {
            client.interrupt();
            isStopSuccessfully = true;
        }
        client = null;
        return isStopSuccessfully;
    }
}
