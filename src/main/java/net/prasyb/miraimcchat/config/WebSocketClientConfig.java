package net.prasyb.miraimcchat.config;

import net.minecraftforge.common.config.Configuration;

public class WebSocketClientConfig implements IHasConfig{
    private String host;
    private int port;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String ip) {
        this.host = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    @Override
    public void syncConfig(Configuration cfg) {
        host = cfg.getString("host",
                "connection",
                "127.0.0.1",
                "Server Host");
        port = cfg.getInt("port",
                "connection",
                0,
                0,
                65536,
                "Server Port");
        key = cfg.getString("key","connection","","Client Key");
    }
}
