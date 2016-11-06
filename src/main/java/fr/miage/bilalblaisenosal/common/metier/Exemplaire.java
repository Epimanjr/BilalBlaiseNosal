package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.ObjetMetierNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Exemplaire {

    /**
     * Identifiant de l'exemplaire.
     */
    private String identifiant;

    /**
     * Etat de l'exemplaire.
     */
    private String etat;

    /**
     * ISBN de l'oeuvre associé.
     */
    private String ISBNOeuvre;

    public Exemplaire(String identifiant, String etat, String oeuvre) {
        this.identifiant = identifiant;
        this.etat = etat;
        this.ISBNOeuvre = oeuvre;
    }

    public Exemplaire(String etat, String oeuvre) {
        this.etat = etat;
        this.ISBNOeuvre = oeuvre;
    }

    /**
     * Permet de récupérer un exemplaire à partir de son identifiant.
     *
     * @param id Identifiant recherché
     * @return Instance d'exemplaire
     * @throws SQLException Problème avec la requête ou la BDD
     * @throws ObjetMetierNotFoundException Si objet non existant dans la base
     */
    public static Exemplaire getExemplaireById(String id) throws SQLException, ObjetMetierNotFoundException {
        String sql = "SELECT * FROM exemplaire WHERE identifiant='" + id + "'";
        ResultSet results = Connector.select(sql);
        if (results.next()) {
            // Récupération des informations
            String identifiant = results.getString("identifiant");
            String etat = results.getString("etat");
            String oeuvre = results.getString("oeuvre");

            // Création d'une instance de Exemplaire
            Exemplaire exemplaire = new Exemplaire(identifiant, etat, oeuvre);
            return exemplaire;
        }
        return null;
    }

    /**
     * Permet de récupérer tous les exemplaires de la base de données.
     *
     * @return Liste d'exemplaires
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public static ArrayList<Exemplaire> getAllExemplaires() throws SQLException {
        ArrayList<Exemplaire> exemplaires = new ArrayList<>();

        String sql = "SELECT * FROM exemplaire";
        ResultSet results = Connector.select(sql);
        while (results.next()) {
            // Récupération des informations
            String identifiant = results.getString("identifiant");
            String etat = results.getString("etat");
            String oeuvre = results.getString("oeuvre");

            // Création d'une instance de Exemplaire
            Exemplaire exemplaire = new Exemplaire(identifiant, etat, oeuvre);

            // Ajout à la liste
            exemplaires.add(exemplaire);
        }
        return exemplaires;
    }

    /**
     * Insertion de l'exemplaire dans la base.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO exemplaire(etat, oeuvre) VALUES('" + this.etat + "', '" + this.ISBNOeuvre + "');";
        Connector.insert(sql);
        
        String lastIdSql = "SELECT LAST_INSERT_ID() AS id FROM exemplaire";
        ResultSet results = Connector.select(lastIdSql);
        if(results.next()) {
            int id = results.getInt("id");
            System.out.println("Dernier identifiant insésé : " + id);
            this.setIdentifiant(""+id);
        }
    }

    /**
     * Mise à jour de l'exemplaire.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void update() throws SQLException {
        String sql = "UPDATE exemplaire SET ";
        sql += "etat='" + this.etat + "', ";
        sql += "oeuvre='" + this.ISBNOeuvre + "' ";
        sql += "WHERE identifiant='" + this.identifiant + "';";
        Connector.insert(sql);
    }

    /**
     * Suppression de l'exemplaire.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM exemplaire WHERE identifiant='" + this.identifiant + "'";
        Connector.insert(sql);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.identifiant);
        hash = 23 * hash + Objects.hashCode(this.etat);
        hash = 23 * hash + Objects.hashCode(this.ISBNOeuvre);
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
        final Exemplaire other = (Exemplaire) obj;
        if (!Objects.equals(this.identifiant, other.identifiant)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.ISBNOeuvre, other.ISBNOeuvre)) {
            return false;
        }
        return true;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getISBNOeuvre() {
        return ISBNOeuvre;
    }

    public void setISBNOeuvre(String ISBNOeuvre) {
        this.ISBNOeuvre = ISBNOeuvre;
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "identifiant=" + identifiant + ", etat=" + etat + ", oeuvre=" + ISBNOeuvre + '}';
    }
}
