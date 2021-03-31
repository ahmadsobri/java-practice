package docstyle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://localhost:8282/wsdl")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT) // just change style
public interface HelloInterface {
    @WebMethod
    String getHelloWorldAsString(String name);
}
