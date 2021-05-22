package net.prasyb.miraimcchat.network;

public class ServerPacket {
    private ServerMessage message;

    public ServerMessage getMessage() {
        return message;
    }

    public void setMessage(ServerMessage message) {
        this.message = message;
    }
}
