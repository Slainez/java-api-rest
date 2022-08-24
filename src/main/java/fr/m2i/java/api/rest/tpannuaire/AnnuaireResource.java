
package fr.m2i.java.api.rest.tpannuaire;


import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/annuaire")
public class AnnuaireResource {
    
    
    // URI  : /annuaire
    @GET
    public Response  getPersons(@Context HttpServletRequest request){
        System.out.println("Endpoint : getPersons");
        if(request.getSession().getAttribute("annuaire") != null){
            
             Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        
              annuaire.getAllPersons();
              
           return  Response.status(Response.Status.OK).entity(annuaire).build();
        }
       
       
        return Response.status(Response.Status.NO_CONTENT).build() ;
        
    }
   
     // URI  : /annuaire/1
    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id")  int id , @Context HttpServletRequest request ){
        System.out.println("Endpoint : getPersonById");
        
         if(request.getSession().getAttribute("annuaire") != null){        
             Annuaire annuaire =(Annuaire) request.getSession().getAttribute("annuaire");        
             annuaire.getPersonById(id, annuaire.getAllPersons());
             return  Response.status(Response.Status.OK).entity(annuaire).build();
         }
         
         return Response.status(Response.Status.NO_CONTENT).build() ;
       
    }
   
      // URI : /annuaire
      @POST
      @Consumes(MediaType.APPLICATION_JSON)
      @Produces(MediaType.APPLICATION_JSON)
      public Response addPerson(Personne person , @Context HttpServletRequest request){
          
          if(request.getSession().getAttribute("annuaire") != null){
              
              Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
              
              annuaire.addPerson(person);
              request.getSession().setAttribute("annuaire",annuaire);
              
             return Response.status(Response.Status.NO_CONTENT).build();
               
          }else{
              Annuaire annuaire = new Annuaire() ;
              annuaire.addPerson(person);
              request.getSession().setAttribute("annuaire",annuaire);
               return Response.status(Response.Status.CREATED).build();
          }
        
       }
       
      
      
      
      
      
      
      
      
       // URI  : /annuaire/1
       @PUT
       @Path("/{id}")
       public Response updatePerson(Personne person , @Context HttpServletRequest request){
           
            if(request.getSession().getAttribute("annuaire") != null){
              Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
              annuaire.updatePerson(person);
              return Response.status(Response.Status.NO_CONTENT).build();
              
          }else{
             return Response.status(Response.Status.BAD_REQUEST).build(); 
          }
           
       }
       
       // URI  : /annuaire/1
       @DELETE
       @Path("/{id}")
       public Response deletePerson(@PathParam("id") int id , @Context HttpServletRequest request){
            if(request.getSession().getAttribute("annuaire") != null){
              Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
              annuaire.deletePerson(id);
              return Response.status(Response.Status.NO_CONTENT).build();
          }else{
             return Response.status(Response.Status.BAD_REQUEST).build();
          }
           
       }
   
    
}
