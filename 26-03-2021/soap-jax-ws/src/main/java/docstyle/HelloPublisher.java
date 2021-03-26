package rpcstyle;

import javax.xml.ws.Endpoint;

public class HelloPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8181/ws/hello", new HelloService());
    }
}
