package server;

import java.net.InetSocketAddress;

public class UDPMessage {
    public InetSocketAddress addr;
    public String body;
    
    public UDPMessage(InetSocketAddress addr, String body) {
        this.addr = addr;
        this.body = body;
    }
}