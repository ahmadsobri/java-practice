package docstyle;

import javax.jws.WebService;

@WebService(
        portName = "HelloInterface",
        serviceName = "MyHelloService",
        targetNamespace = "http://localhost:8282/wsdl",
        endpointInterface = "docstyle.HelloInterface"
)
public class HelloService implements HelloInterface {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}
