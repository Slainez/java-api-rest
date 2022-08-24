package fr.m2i.java.api.rest.tpannuaire;


import java.util.ArrayList;
import java.util.List;


public class Annuaire {
    
    private List<Personne> personList = new ArrayList() ;

    public Annuaire() {
        
    }
    
    public Personne addPerson(Personne person) {
        
       personList.add(person);
       
       return person ;
    }
    
    public List<Personne> getAllPersons(){
        
        return personList ;
    }
    
    public Personne getPersonById(int id , List<Personne> listPerson){
         
        for(Personne p : listPerson){
            
             if(p.getId() == id){
                 return p ;
            }            
        }
       return  null ;
       
    }
    
    public void updatePerson(Personne personData){
        
         Personne personToUpdate = personList.get(personData.getId());
         personToUpdate.setNom(personData.getNom());
         personToUpdate.setPrenom(personData.getPrenom());
        
    }
    public void deletePerson(int id){
        
        personList.remove(id);
        
    }
    
}
