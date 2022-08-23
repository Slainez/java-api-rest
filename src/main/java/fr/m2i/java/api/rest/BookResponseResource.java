
package fr.m2i.java.api.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/response-books")
public class BookResponseResource {
    
    
    // URI : /ok/without-response
    @GET
    @Path("/ok/without-response")
    public String getBookWithoutResponse() {
        
        System.out.println("Endpoint : getBookWithoutResponse");
        
        return "Java 8 docs" ;
      }
    
     // URI : /ok
    @GET
    @Path("/ok")
    public Response getBook() {
        
        System.out.println("Endpoint : getBook");
        
        return Response.status(Response.Status.OK).entity("Java 8 doc").build() ;
     }
    
      // URI : /ok/headers
    @GET
    @Path("/ok/headers")
    public Response getBookWithHeaders() {
        
        System.out.println("Endpoint : getBookWithHeaderse");
        
        
        return Response.status(Response.Status.OK).header("param", "value").entity("Java 8 doc").build() ;
      }
    
     // URI : /ok/json-produces
    @GET
    @Path("/ok/json-produces")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookJSONProduces() {
        
        System.out.println("Endpoint : getBookJSON Produces");
        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("1-111111-11");
        return book ;
      }
    
    // URI : /ok/json
    @GET
    @Path("/ok/json")
    public Response getBookJSON() {
        
        System.out.println("Endpoint : getBookJSON");
        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("1-111111-11");
        
        return Response.status(Response.Status.OK).entity(book).type(MediaType.APPLICATION_JSON).build() ;
      }
    
      // URI : /error/webapp-exception
    @GET
    @Path("/error/webapp-exception")
    public String getBookByIdWithWebAppException(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithWebAppException");

        if (id == null) {
            throw new BadRequestException();
        }

        return "Livre avec id : " + id + " - Java 8 doc";
    }

    // URI : /error
    @GET
    @Path("/error")
    public Response getBookByIdWithError(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookByIdWithError");

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity("Livre avec id : " + id + " - Java 8 doc").build();
    }

    
}
