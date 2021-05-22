package net.prasyb.miraimcchat.network;

public class ServerMessage {
    private String senderQQName = "";
    private String senderMcName = "";
    private String source = "";
    private String text;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSenderQQName() {
        return senderQQName;
    }

    public void setSenderQQName(String senderQQName) {
        this.senderQQName = senderQQName;
    }

    public String getSenderMcName() {
        return senderMcName;
    }

    public void setSenderMcName(String senderMcName) {
        this.senderMcName = senderMcName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}