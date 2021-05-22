package net.prasyb.miraimcchat.service;

import net.prasyb.miraimcchat.MiraiMcChat;
import net.prasyb.miraimcchat.network.WebSocketClient;

public class ClientThreadService {
    public static void runWebSocketClient() {
        if (MiraiMcChat.INSTANCE.getClientThread() != null) {
            MiraiMcChat.INSTANCE.getClientThread().interrupt();
        }
        MiraiMcChat.INSTANCE.setClientThread(new WebSocketClient(
                MiraiMcChat.INSTANCE.getClientConfig().getHost(),
                MiraiMcChat.INSTANCE.getClientConfig().getPort(),
                MiraiMcChat.INSTANCE.getClientConfig().getKey()));
        MiraiMcChat.INSTANCE.getClientThread().start();
    }
    /**
     * @return {@code true}: 已存在客户端; {@code false}: 不存在客户端
     * */
    public static boolean stopWebSocketClient() {
        boolean isStopSuccessfully = false;
        if (MiraiMcChat.INSTANCE.getClientThread() != null) {
            MiraiMcChat.INSTANCE.getClientThread().interrupt();
            isStopSuccessfully = true;
        }
        MiraiMcChat.INSTANCE.setClientThread(null);
        return isStopSuccessfully;
    }
}
