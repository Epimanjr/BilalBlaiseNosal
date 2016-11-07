/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.OeuvreNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 */
public class Oeuvre {
    
    private String ISBN;
    private String titre;
    private String editeur;

    public Oeuvre(String ISBN, String titre, String editeur) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.editeur = editeur;
    }
    
    public static ArrayList<Oeuvre> getAllOeuvre() throws SQLException {
        // TODO 
        ArrayList<Oeuvre> oeuvres = new ArrayList<>();
        
        String sql = "SELECT * FROM oeuvre";
        ResultSet results = Connector.select(sql);
        while(results.next()) {
            // Récupération des informations
            String titre = results.getString("titre");
            String editeur = results.getString("editeur");
            String ISBN = results.getString("ISBN");
            
            // Création d'une instance de Oeuvre
            Oeuvre oeuvre = new Oeuvre(ISBN, titre, editeur);
            
            // Ajout à la liste
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }
    
    public static ArrayList<Oeuvre> getAllOeuvreByTitre(String titreParam) throws SQLException {
        // TODO 
        ArrayList<Oeuvre> oeuvres = new ArrayList<>();
        
        String sql = "SELECT * FROM oeuvre WHERE titre LIKE '%" + titreParam + "%'";
        ResultSet results = Connector.select(sql);
        while(results.next()) {
            // Récupération des informations
            String titre = results.getString("titre");
            String editeur = results.getString("editeur");
            String ISBN = results.getString("ISBN");
            
            // Création d'une instance de Oeuvre
            Oeuvre oeuvre = new Oeuvre(ISBN, titre, editeur);
            
            // Ajout à la liste
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }
    
    public static Oeuvre getOeuvreByISBN(String ISBNParam) throws SQLException, OeuvreNotFoundException {
        // TODO 
        String sql = "SELECT * FROM oeuvre WHERE ISBN='"+ISBNParam+"'";
        ResultSet results = Connector.select(sql);
        if(results.next()) {
            // Récupération des informations
            String titre = results.getString("titre");
            String editeur = results.getString("editeur");
            String ISBN = results.getString("ISBN");
            
            // Création d'une instance de Oeuvre
            Oeuvre oeuvre = new Oeuvre(ISBN, titre, editeur);
            return oeuvre;
        }
        return null;
    }
    
    public void insert() throws SQLException {
        // TODO
        String sql = "INSERT INTO oeuvre(ISBN, titre, editeur) VALUES('"+this.ISBN+"', '"+this.titre+"', '"+this.editeur+"');";
        Connector.insert(sql);
    }
    
    public void update() throws SQLException {
        // TODO
        String sql = "UPDATE oeuvre SET ";
        sql += "titre='"+this.titre+"', ";
        sql += "editeur='"+this.editeur+"' ";
        sql += "WHERE ISBN='"+this.ISBN+"';";
        Connector.insert(sql);
    }
    
    public void delete() throws SQLException {
        // TODO
        String sql = "DELETE FROM oeuvre WHERE ISBN='"+this.ISBN+"'";
        Connector.insert(sql);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.ISBN);
        hash = 59 * hash + Objects.hashCode(this.titre);
        hash = 59 * hash + Objects.hashCode(this.editeur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oeuvre other = (Oeuvre) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.editeur, other.editeur)) {
            return false;
        }
        return true;
    }
    
    public void setISBN(String isbn) {
        this.ISBN = isbn;
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

    @Override
    public String toString() {
        return "" + ISBN + " - " + this.titre + " (par " + this.editeur + ")";
    }
    
    
}
