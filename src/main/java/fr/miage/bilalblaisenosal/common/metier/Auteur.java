package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Auteur {

    /**
     * Identifiant de l'auteur dans la base de données.
     */
    private int idAuteur;

    /**
     * Nom de l'auteur.
     */
    private String nomAuteur;

    /**
     * Prénom de l'auteur.
     */
    private String prenomAuteur;

    public Auteur(String nomAuteur, String prenomAuteur) {
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
    }

    public Auteur(int idAuteur, String nomAuteur, String prenomAuteur) {
        this.idAuteur = idAuteur;
        this.nomAuteur = nomAuteur;
        this.prenomAuteur = prenomAuteur;
    }

    /**
     * Permet de récupérer un Auteur à partir de son identifiant.
     *
     * @param id Identifiant de l'auteur
     * @return Instance de l'objet Auteur
     */
    public static Auteur getAuteurById(int id) throws SQLException {
        String sql = "SELECT * FROM auteur WHERE idAuteur='" + id + "'";
        ResultSet results = Connector.select(sql);
        if (results.next()) {
            // Récupération des informations
            int identifiant = results.getInt("idAuteur");
            String nom = results.getString("nomAuteur");
            String prenom = results.getString("prenomAuteur");

            // Création d'une instance de Exemplaire
            Auteur auteur = new Auteur(identifiant, nom, prenom);
            return auteur;
        }
        return null;
    }

    /**
     * Récupère tous les auteurs de la base de données.
     *
     * @return Liste d'auteurs
     */
    public static ArrayList<Auteur> getAllAuteurs() throws SQLException {
        ArrayList<Auteur> auteurs = new ArrayList<>();

        String sql = "SELECT * FROM auteur";
        ResultSet results = Connector.select(sql);
        if (results.next()) {
            // Récupération des informations
            int identifiant = results.getInt("idAuteur");
            String nom = results.getString("nomAuteur");
            String prenom = results.getString("prenomAuteur");

            // Création d'une instance de Exemplaire
            Auteur auteur = new Auteur(identifiant, nom, prenom);
            auteurs.add(auteur);
        }
        return auteurs;
    }

    /**
     * Insertion de l'auteur dans la base de données.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO auteur(nomAuteur, prenomAuteur) VALUES('" + this.nomAuteur + "', '" + this.prenomAuteur + "');";
        Connector.insert(sql);

        String lastIdSql = "SELECT LAST_INSERT_ID() AS id FROM auteur";
        ResultSet results = Connector.select(lastIdSql);
        if (results.next()) {
            int id = results.getInt("id");
            System.out.println("Dernier identifiant insésé : " + id);
            this.setIdAuteur(id);
        }
    }

    /**
     * Mise à jour de l'auteur dans la base de données.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE auteur SET ";
        sql += "nomAuteur='" + this.nomAuteur + "', ";
        sql += "prenomAuteur='" + this.prenomAuteur + "' ";
        sql += "WHERE idAuteur='" + this.idAuteur + "';";
        Connector.insert(sql);
    }

    /**
     * Suppression de l'auteur dans la base de données.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM auteur WHERE idAuteur='" + this.idAuteur + "'";
        Connector.insert(sql);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.idAuteur;
        hash = 47 * hash + Objects.hashCode(this.nomAuteur);
        hash = 47 * hash + Objects.hashCode(this.prenomAuteur);
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
        final Auteur other = (Auteur) obj;
        if (this.idAuteur != other.idAuteur) {
            return false;
        }
        if (!Objects.equals(this.nomAuteur, other.nomAuteur)) {
            return false;
        }
        if (!Objects.equals(this.prenomAuteur, other.prenomAuteur)) {
            return false;
        }
        return true;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur = prenomAuteur;
    }

}
