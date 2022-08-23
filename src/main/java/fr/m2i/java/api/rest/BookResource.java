
package fr.m2i.java.api.rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/books")
public class BookResource {
    
    
    // URI  : /books
    @GET
    public String getBooks(){
        System.out.println("Endpoint : getBooks");
        return  "Moby Dick / Don Quichotte / Le viel homme et la mer" ;
        
    }
   
     // URI  : /books/borrowed
    @GET
    @Path("/borrowed")
    public String getBorrowedBooks(){
        System.out.println("Endpoint : getBorrowedBooks");
        return "Moby Dick / Le viel homme et la mer";
        
    }
    
    
     // URI  : /books/1
    @GET
    @Path("/{id}")
    public String getBookById(@PathParam("id")  int id){
        System.out.println("Endpoint : getBookById");
        return " 1 -> Moby Dick ";
        
    }
    
     // URI  : /books/name-angular-editor-google
    @GET
    @Path("/name-{name}-editor-{editor}")
    public String getBookByNameAndEditor(@PathParam("name") String name , @PathParam("editor") String editor){
        System.out.println("Endpoint : getBookByNameAndEditor");
        return "Angular pour les nuls";
    }
    
    // URI : /books/queryparameters?name=harry&isbn=1-111111-11&isExtended=true
    @GET
    @Path("/query-parameters")
    public String getQueryParametersBook(@DefaultValue("default") @QueryParam("name") String name , 
                @DefaultValue("?-??????-??") @QueryParam("isbn") String isbn,
                @DefaultValue("false") @QueryParam("isExtended") boolean isExtended){
                System.out.println("Endpoint : query parameter");
        return "Livre recherché - nom :" + name + " ISBN : " + isbn + " Version étendue :" + isExtended ;
    }
    
    // URI : books/create-from-form
    @POST
    @Path("/create-from-form")
    @Consumes("application/x-www-form-urlencoded")
    public String createFromForm(@FormParam("name") String name){
        System.out.println("Endpoint : create from form");
        return "Le nom du livre crée est " + name ;
    }
    
    
    // URI : books/header-parameters
      @GET
    @Path("/header-parameters")
    public String getHeaderParametersBook(@DefaultValue("default") @HeaderParam("name") String name , 
                @DefaultValue("?-??????-??") @HeaderParam("isbn") String isbn,
                @DefaultValue("false") @HeaderParam("isExtended") boolean isExtended){
                System.out.println("Endpoint : header parameter");
        return "Livre recherché - nom :" + name + " ISBN : " + isbn + " Version étendue :" + isExtended ;
    }
    
     // URI : /context-uri-info
    @GET
    @Path("/context-uri-info/{name}")
    public String geContextUriInfo(@Context UriInfo uriInfo, @PathParam("name") String name,
            @QueryParam("queryParam") String queryParam) {
        System.out.println("Endpoint : geContextUriInfo");

        StringBuilder result = new StringBuilder();
        result.append("getPath(): ")
                .append(uriInfo.getPath())
                .append("\n")
                .append("getPathSegments(): ");

        for (PathSegment p : uriInfo.getPathSegments()) {
            result.append(p.getPath()).append(" ");
        }

        result.append("\n")
                .append("getPathParameters(): ");
        
        MultivaluedMap<String , String > pathParameters = uriInfo.getPathParameters();
        
        for (String key : pathParameters.keySet()) {
            result.append(key).append(" :").append(pathParameters.get(key));
        }

             

        result.append("\n")
                .append("getQueryParameters(): ");
        
        MultivaluedMap<String , String > queryParameters = uriInfo.getQueryParameters();
        
        for (String key : queryParameters.keySet()) {
            result.append(key).append(" :").append(queryParameters.get(key));
        }
           
        

        result.append("\n")
                .append("getAbsolutePath(): ").append(uriInfo.getAbsolutePath()).append("\n")
                .append("getBaseURI(): ").append(uriInfo.getBaseUri()).append("\n")
                .append("getRequestURI(): ").append(uriInfo.getRequestUri()).append("\n");

        return result.toString();
    }
    
    
     // URI : /context-headers
    @GET
    @Path("/context-headers")
    public String geContextHeaders(@Context HttpHeaders headers) {
        System.out.println("Endpoint : geContextHeaders");

        StringBuilder result = new StringBuilder();
        result.append("Cookies: ");

        for (String key : headers.getCookies().keySet()) {
            result.append(key).append("\n");
        }

        result.append("RequestHeaders: ");

        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();

        for (String key : requestHeaders.keySet()) {
            result.append(key).append(" : ").append(requestHeaders.get(key)).append("\n");
        }
        
        return result.toString();
    }

    
    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDetailsTextBookByID(@PathParam("id") int id){
        return "Le detail du livre numéro : " + id + "- il s'agit d'une autobiographie ";
    }
    
    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_XML)
    public String getDetailsXMLBookByID(@PathParam("id") int id){
        return "<?xml version=\"1.0\" ? > "
                + "<details>" 
                + "Le detail du livre numéro : " + id + "- il s'agit d'une autobiographie"
                + " </details>";
    }
    
    
    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String getDetailsHTMLBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsHTMLBookById");

        return "<html><head><title>Detail de mon livre</title></head>"
                + "<body><h1>Le détail du livre numéro : " + id + " - Il s'agit d'une autobiographie</h1></body></html>";
    }
}
