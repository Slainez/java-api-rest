
package fr.m2i.java.api.rest.tpuser.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SessionHelper {
   private static EntityManager entityManager = null;
    
    public static EntityManager getEntityManager(){
        if(entityManager == null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp_rest_pu");
            entityManager = emf.createEntityManager();           
        }
        return entityManager ;
    }
    
}
