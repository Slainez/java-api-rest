
package fr.m2i.java.api.rest;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/content-books")
public class BookContentResource {
    
    // GET et PUT pour XML
    //*********************
    // URI : content-book/xml    
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Book getContentBookXML(){
        
        System.out.println("Endpoint : getContentBookXML");
        
        
        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("1-111111-11");
        
        return book  ;
    }
    // URI : content-book/xml   
     @PUT
    @Path("/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateContentBookXML(Book book){
        
        System.out.println("Endpoint : updateContentBookXML");
        System.out.println( " Les modification apportées sont " + book.getName());
         System.out.println( " isbn " + book.getIsbn());    
   
    }
       // GET et PUT pour JSON
    //*********************
    // URI : content-book/json   
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getContentBookJSON(){
        
        System.out.println("Endpoint : getContentBookJSON");
        
        
        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("1-111111-11");
        
        return book  ;
    }
    // URI : content-book/json  
    @PUT
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContentBookJSON(Book book){
        
        System.out.println("Endpoint : updateContentBookJSON");
        System.out.println( "Les modification apportées sont : ");
        System.out.println("name :" + book.getName());
        System.out.println( "isbn : " + book.getIsbn());    
   
    }
    
     // URI : content-books/jsonxml   
    @GET
    @Path("/jsonxml")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getContentBookJSONAndXML(){
        
        System.out.println("Endpoint : getContentBookJSONAndXML");
        
        
        Book book = new Book();
        book.setName("Harry Potter JSON XML");
        book.setIsbn("1-111111-11");
        
        return book  ;
    }
    
    // URI : content-books/   
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public List< Book> getAllContentBookJSON(){
        
        System.out.println("Endpoint : getAllContentBookJSON");
        
        
        Book book1 = new Book();
        book1.setName("Eragon");
        book1.setIsbn("1-111111-11");
        Book book2 = new Book();
        book2.setName("L'aine");
        book2.setIsbn("2-222222-22");
        Book book3 = new Book();
        book3.setName("Brisingr");
        book3.setIsbn("3-333333-33");
        
        
        
        return Arrays.asList(book1,book2,book3)  ;
    }
    
    
}
