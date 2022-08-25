
package fr.m2i.java.api.rest.tpuser.filter;

import fr.m2i.java.api.rest.tpuser.model.User;
import fr.m2i.java.api.rest.tpuser.util.BasicAuth;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    @Context
    public HttpServletRequest request ;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        // On recupere les identififants et mot de passe dans le header
        String auth = requestContext.getHeaderString("Authorization");        
       checkNotNull(auth);
        
        String[] lap = BasicAuth.decode(auth);        
       checkNotNull(lap);
        if(lap.length != 2){
         throw new WebApplicationException(
                        Response.status(Response.Status.UNAUTHORIZED)
                                .entity("You must be connected !")
                                .build());
       
      }
     
     
        User authentified = checkUser(lap[0] , lap[1]);        
        checkNotNull(authentified);

    }
     public User checkUser(String email, String password) {
        User admin = new User("Super", "admin", "SUPER_ADMIN", "super@admin.com", "admin");

        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return admin;
        }

        return null;
    }
     
     
     public boolean checkNotNull (Object object){
         
         if(object == null){
              throw new WebApplicationException(
                        Response.status(Response.Status.UNAUTHORIZED)
                                .entity("You must be connected !")
                                .build());              
         }
         
         return true ;
         
     }
    
    
}
