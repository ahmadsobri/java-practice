package rpcstyle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://localhost:8181/wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC) // just change style
public interface HelloInterface {
    @WebMethod
    String getHelloWorldAsString(String name);
}
