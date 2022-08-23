
package fr.m2i.java.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.xml.bind.annotation.XmlRootElement;

@JsonPropertyOrder({"isbn","name"})
@XmlRootElement(name = "book") // L'annotation XML est obligatoire pour serialiser en XML
public class Book {
    
    @JsonProperty( "book_name") // Les annotations JSON sont facultatives 
    private String name ;
    @JsonProperty( "book_isbn")
    private String isbn ;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
    
}
