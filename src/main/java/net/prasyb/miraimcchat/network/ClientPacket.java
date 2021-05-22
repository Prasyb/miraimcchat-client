package net.prasyb.miraimcchat.network;


public class ClientPacket {
    private String key;
    private ClientMessage message;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ClientMessage getMessage() {
        return message;
    }

    public void setMessage(ClientMessage message) {
        this.message = message;
    }
}
