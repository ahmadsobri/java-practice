package docstyle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8282/ws/hello?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://localhost:8282/wsdl", "MyHelloService");
        Service service = Service.create(url, qname);
        HelloInterface hello = service.getPort(HelloInterface.class);
        System.out.println(hello.getHelloWorldAsString("tutorial doc style"));
    }
}
