package fr.miage.bilalblaisenosal.metier;

import java.sql.SQLException;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Usager {

    /**
     * Nom de l'usager.
     */
    private String nom;

    /**
     * Prénom de l'usager.
     */
    private String prenom;

    /**
     * Adresse e-mail de l'usager.
     */
    private String email;

    /**
     * Numéro de téléphone de l'usager.
     */
    private String telephone;

    /**
     * Construit un Usager à partir des paramètres.
     *
     * @param nom Nom de l'usager
     * @param prenom Prénom de l'usager
     * @param email Adresse e-mail de l'usager
     * @param telephone Numéro de téléphone de l'usager
     */
    public Usager(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }
    
    public void insert() throws SQLException {
        // TODO Insertion d'un usager dans la base de données.
        
    }
    
    public void update() throws SQLException {
        // TODO Mise à jour d'un usager dans la base de données.
        
    }
    
    public void delete() throws SQLException {
        // TODO Suppression d'un usager dans la base de données.
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
