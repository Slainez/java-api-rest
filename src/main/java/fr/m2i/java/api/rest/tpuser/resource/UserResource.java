
package fr.m2i.java.api.rest.tpuser.resource;

import fr.m2i.java.api.rest.tpuser.dao.UserDAO;
import fr.m2i.java.api.rest.tpuser.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserResource {
    
    // URI : /
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getUsers(){
        
        System.out.println("Endpoint : GET ALL USERS");
        UserDAO userDao = new UserDAO();    
        List<User> listUsers = userDao.findAll() ;
        if(listUsers.isEmpty()){
            return  Response.status(Response.Status.NOT_FOUND).entity("Pas d'utilisateurs dans la base de données actuellement").build();
        }   
        
        return  Response.status(Response.Status.OK).entity(listUsers).build();
    
  }
      // URI : users/1
    @GET  
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getUsersById( @PathParam("id") int id){
        
        System.out.println("Endpoint : GET  USER BY ID");
        UserDAO userDao = new UserDAO();    
               
        if(id < 1){
            return  Response.status(Response.Status.BAD_REQUEST).entity("An error has occured").build();
        }   
               
       User found = userDao.findById(id);
       if(found != null ) {
           return  Response.status(Response.Status.OK).entity(found).build();
       }    
        
        return  Response.status(Response.Status.NOT_FOUND).entity("User was not found").build();
    
  }
    
    //URI /
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser( User user){
        System.out.println("Endpoint : POST USER");
        UserDAO userDao = new UserDAO(); 
        // Verifications  
       
        if (!user.isNotNull(user)){
             return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build(); 
        }
         
        // Création 
        try{
             userDao.create(user) ;
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();
        }
        
         return Response.status(Response.Status.CREATED).entity("User succesfully created !").build();
    }
    
    
    
     // URI  : /users/1
       @PUT
       @Path("/{id}")
       @Consumes(MediaType.APPLICATION_JSON)
       @Produces(MediaType.APPLICATION_JSON)
       public Response updatePerson(User user ,  @PathParam("id") int id){
           System.out.println("Endpoint : PUT USER");
           UserDAO userDao = new UserDAO(); 
           // Verifications  
           
           if(user == null ){            
             return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();          
           }
          
           // Tentative d'update
           try{               
             userDao.update(id, user);
             
            }catch (Exception e){
                return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();
            }
           
           return Response.status(Response.Status.OK).entity("User succesfully modified !").build();

           
       }
    
     // URI : /users/1
     @DELETE
     @Path("/{id}")
     public Response deleteUser(@PathParam("id") int id ){
              System.out.println("Endpoint : DELETE USER");
              UserDAO userDao = new UserDAO(); 
              User userToDelete =   new User()  ;           
              // Verifications  
               if (id < 1){
                 return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build(); 
               }
              // On tente de trouver l'utilisateur par son id 
               try{
               
                userToDelete = userDao.findById(id);
               
                 }catch(Exception e){
               
                return Response.status(Response.Status.NOT_FOUND).entity("User not found !").build();
              
                }    
               
               if(userToDelete == null ){            
                     return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();          
                }
               // On tente de supprimer l'utilisateur             
              try{
               
                userDao.delete(userToDelete);
             
                }catch (Exception e){
                    return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();
                }
              
              return Response.status(Response.Status.OK).entity("User succesfully deleted").build();          
          
          }
           
       // URI /users/search
     @GET
     @Path("/search")
     @Produces(MediaType.APPLICATION_JSON)
     public Response getUsersByQuery( @Context HttpServletRequest request , 
             @QueryParam("q") String searched ,
             @DefaultValue("1") @QueryParam("count") int count){
         
        System.out.println("Endpoint : Search USER by name or email");
        UserDAO userDao = new UserDAO();
        List<User> listUsers = new ArrayList() ;
          
        // Verifications 
        if(searched == null || count <= 0){
              return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured !").build();
        }
               
        // On execute la recherche    
       listUsers = userDao.findBySearch(searched , count);
         
       if(listUsers.isEmpty()){
               return Response.status(Response.Status.NOT_FOUND).entity("No user found").build(); 
        }
        
         return Response.status(Response.Status.OK).entity(listUsers).build(); 
     }
    
  
}
