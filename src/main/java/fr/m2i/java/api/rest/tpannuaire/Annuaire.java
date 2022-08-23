package fr.m2i.java.api.rest.tpannuaire;


import java.util.List;


public class Annuaire {
    
    private List<Personne> personList ;

    public Annuaire() {
        
    }
    
    public void addPerson(Personne person) {
        
       personList.add(person);
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
        
         Personne personToUpdate = personList.get(0);
         personToUpdate.setNom(personData.getNom());
         personToUpdate.setPrenom(personData.getPrenom());
        
    }
    public void deletePerson(int id){
        
        personList.remove(id);
        
    }
    
}
