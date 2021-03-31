package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
    // This method is called if HTML and XML is not requested
    @GET
    //@Path("/getHello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "com.example.Hello Jersey Plain";
    }
    // This method is called if XML is requested
    @GET
    //@Path("/getHello")
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> com.example.Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is requested
    @GET
    //@Path("/getHello")
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "com.example.Hello Jersey" + "</title>"
                + "<body><h1>" + "com.example.Hello Jersey HTML" + "</h1></body>" + "</html> ";
    }
}
