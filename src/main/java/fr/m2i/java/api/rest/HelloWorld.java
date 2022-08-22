
package fr.m2i.java.api.rest;

import javax.ws.rs.*;

@Path("/hello")
public class HelloWorld {
    
    @GET
    @Produces("text/plain")
    public String getHelloWorld(){
        return "Hello World";
    }
}
