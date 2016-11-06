package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.UsagerNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    public static ArrayList<Usager> getAllUsager() throws SQLException {
        // TODO Sélection de tous les usagers de la base
        ArrayList<Usager> listUsagers = new ArrayList<>();

        String sql = "SELECT * FROM usager";
        ResultSet results = Connector.select(sql);
        while (results.next()) {
            // Récupération des informations
            String nom = results.getString("nom");
            String prenom = results.getString("prenom");
            String email = results.getString("email");
            String telephone = results.getString("telephone");

            // Création d'une instance d'Usager
            Usager usager = new Usager(nom, prenom, email, telephone);
            listUsagers.add(usager);
        }

        return listUsagers;
    }

    public static Usager getUsagerByEmail(String emailParam) throws SQLException, UsagerNotFoundException {
        // TODO Récupération d'un usager de la base
        String sql = "SELECT * FROM usager WHERE email='" + emailParam + "'";
        System.out.println(sql);
        ResultSet results = Connector.select(sql);
        if (results.next()) {
            // Récupération des informations
            String nom = results.getString("nom");
            String prenom = results.getString("prenom");
            String email = results.getString("email");
            String telephone = results.getString("telephone");

            // Création d'une instance d'Usager
            Usager usager = new Usager(nom, prenom, email, telephone);
            return usager;
        } else {
            throw new UsagerNotFoundException();
        }
    }

    public Usager(HashMap<String, String> askedFields) {
        this.nom = askedFields.get("nom");
        this.prenom = askedFields.get("prenom");
        this.email = askedFields.get("email");
        this.telephone = askedFields.get("telephone");
    }

    public void insert() throws SQLException {
        // TODO Insertion d'un usager dans la base de données.
        String sql = "INSERT INTO usager(nom, prenom, email, telephone) VALUES('" + this.nom + "', '" + this.prenom + "', '" + this.email + "', '" + this.telephone + "');";
        Connector.insert(sql);
    }

    public void update() throws SQLException {
        // TODO Mise à jour d'un usager dans la base de données.
        String sql = "UPDATE usager SET ";
        sql += "nom='" + this.nom + "', ";
        sql += "prenom='" + this.prenom + "', ";
        sql += "telephone='" + this.telephone + "' ";
        sql += "WHERE email='" + this.email + "'";
        Connector.insert(sql);
    }

    public void delete() throws SQLException {
        // TODO Suppression d'un usager dans la base de données.
        String sql = "DELETE FROM usager WHERE email='"+this.email+"'";
        Connector.insert(sql);
               
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nom);
        hash = 41 * hash + Objects.hashCode(this.prenom);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.telephone);
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
        final Usager other = (Usager) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        return true;
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

    @Override
    public String toString() {
        return "" + nom + " " + prenom + " (" + email + " - " + telephone + ")";
    }

    
    
}
