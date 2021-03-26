package rpcstyle;

import javax.jws.WebService;

@WebService(
        portName = "HelloInterface",
        serviceName = "MyHelloService",
        targetNamespace = "http://localhost:8181/wsdl",
        endpointInterface = "rpcstyle.HelloInterface"
)
public class HelloService implements HelloInterface {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
