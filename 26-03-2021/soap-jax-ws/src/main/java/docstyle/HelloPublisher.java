package docstyle;

import javax.xml.ws.Endpoint;

public class HelloPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8282/ws/hello", new HelloService());
    }
}
