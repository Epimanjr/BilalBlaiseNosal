/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.metier;

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
    
    public static ArrayList<Oeuvre> getAllOeuvre() {
        // TODO 
        
        return null;
    }
    
    public static Oeuvre getOeuvreByISBN() {
        // TODO 
        
        return null;
    }
    
    public void insert() {
        // TODO
    }
    
    public void update() {
        // TODO
    }
    
    public void delete() {
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
