/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maxime BLAISE
 */
public class Oeuvre {
    
    private final String ISBN;
    private String titre;
    private String editeur;

    public Oeuvre(String ISBN, String titre, String editeur) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.editeur = editeur;
    }
    
    public static ArrayList<Oeuvre> getAllOeuvre() throws SQLException {
        // TODO 
        
        return null;
    }
    
    public static Oeuvre getOeuvreByISBN(String ISBNParam) throws SQLException, OeuvreNotFoundException {
        // TODO 
        
        return null;
    }
    
    public void insert() throws SQLException {
        // TODO
    }
    
    public void update() throws SQLException {
        // TODO
    }
    
    public void delete() throws SQLException {
        // TODO
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
    
    
}
