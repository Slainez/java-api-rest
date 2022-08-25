
package fr.m2i.java.api.rest.tpuser.dao;

import fr.m2i.java.api.rest.tpuser.model.User;
import fr.m2i.java.api.rest.tpuser.util.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class UserDAO {
    
    private final EntityManager entityManager;

    public UserDAO() {
        this.entityManager = SessionHelper.getEntityManager();
    }
    
    
    public List<User> findAll() {
        Query findAllQuery = entityManager.createQuery("select u from User u");
        return findAllQuery.getResultList();
    }
    
     public User findById(int id) throws Exception {
              if(id <= 0){
                throw new Exception("User can't be null ");
              }
              User founded = entityManager.find(User.class, id);
        
               if (founded == null) {
                  throw new Exception("User not Found");
                }
        
        return founded;
    }
     
      
       public List<User> findBySearch(String search, int count) {
       Query findByNameQuery = entityManager.createQuery("select u from User u where u.lastname = ?1 or u.email = ?1");
       findByNameQuery.setParameter(1, search);
       findByNameQuery.setMaxResults(count);
        return findByNameQuery.getResultList();
      }
          
    
     public void create(User user) {       

        EntityTransaction tx = null;
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            
            entityManager.persist(user);
            
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la création de l'utilisateur");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
     
      public void update(int id, User user) throws Exception {
        
        User userToUpdate = new User();
        try{
            userToUpdate = findById(id);
        }catch(Exception e){
           throw new Exception("User not Found");
        }                   
        // gerer le cas not found boolean puis exception 
        
        userToUpdate.copy(user);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.merge(userToUpdate);

            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la modification de l'utilisateur");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            throw new Exception("An error occured");
        }
    }

      public void delete(User user){
          
          EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(user);

            tx.commit();
        } catch (Exception e) {
            
            System.out.println("Une erreur est survenu lors de la suppression");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            throw e ;
        }
      }
    
}
