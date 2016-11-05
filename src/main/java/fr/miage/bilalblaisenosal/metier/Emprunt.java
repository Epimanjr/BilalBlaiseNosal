package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Emprunt {

    /**
     * Date de début de l'emprunt.
     */
    private String dateDebut;

    /**
     * Etat de l'emprunt.
     */
    private String etat;

    /**
     * Email de l'usager ayant effectué l'emprunt.
     */
    private String emailUsager;

    /**
     * Identifiant de l'exemplaire emprunté.
     */
    private String identifiantExemplaire;

    /**
     * Construit un Emprunt à partir de toutes les informations caractérisant un
     * emprunt.
     *
     * @param dateDebut
     * @param etat
     * @param emailUsager
     * @param identifiantExemplaire
     */
    public Emprunt(String dateDebut, String etat, String emailUsager, String identifiantExemplaire) {
        this.dateDebut = dateDebut;
        this.etat = etat;
        this.emailUsager = emailUsager;
        this.identifiantExemplaire = identifiantExemplaire;
    }

    /**
     * Récupère tous les emprunts de la base de données.
     *
     * @return Liste d'emprunts
     */
    public static ArrayList<Emprunt> getAllEmprunts() throws SQLException {
        ArrayList<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM emprunt";
        ResultSet results = Connector.select(sql);
        if (results.next()) {
            // Récupération des informations
            String dateDebut = results.getString("dateDebut");
            String etat = results.getString("etat");
            String emailUsager = results.getString("emailUsager");
            String identifiantExemplaire = results.getString("identifiantExemplaire");

            // Création d'une instance de Exemplaire
            Emprunt emprunt = new Emprunt(dateDebut, etat, emailUsager, identifiantExemplaire);
            emprunts.add(emprunt);
        }
        return emprunts;
    }

    /**
     * Insertion de l'emprunt dans la base de données.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO emprunt(dateDebut, etat, emailUsager, identifiantExemplaire) VALUES('" + this.dateDebut + "', '" + this.etat + "', '" + this.emailUsager + "', '" + this.identifiantExemplaire + "');";
        Connector.insert(sql);
    }

    /**
     * Mise à jour de l'emprunt dans la base de données.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE emprunt SET ";
        sql += "etat='" + this.etat + "' ";
        sql += "WHERE dateDebut='" + this.dateDebut + "' ";
        sql += "AND emailUsager='" + this.emailUsager + "' ";
        sql += "AND identifiantExemplaire='" + this.identifiantExemplaire + "';";
        Connector.insert(sql);
    }

    /**
     * Suppression de l'emprunt de la base de données.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM emprunt ";
        sql += "WHERE dateDebut='" + this.dateDebut + "' ";
        sql += "AND emailUsager='" + this.emailUsager + "' ";
        sql += "AND identifiantExemplaire='" + this.identifiantExemplaire + "';";
        Connector.insert(sql);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.dateDebut);
        hash = 17 * hash + Objects.hashCode(this.etat);
        hash = 17 * hash + Objects.hashCode(this.emailUsager);
        hash = 17 * hash + Objects.hashCode(this.identifiantExemplaire);
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
        final Emprunt other = (Emprunt) obj;
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.emailUsager, other.emailUsager)) {
            return false;
        }
        if (!Objects.equals(this.identifiantExemplaire, other.identifiantExemplaire)) {
            return false;
        }
        return true;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEmailUsager() {
        return emailUsager;
    }

    public void setEmailUsager(String emailUsager) {
        this.emailUsager = emailUsager;
    }

    public String getIdentifiantExemplaire() {
        return identifiantExemplaire;
    }

    public void setIdentifiantExemplaire(String identifiantExemplaire) {
        this.identifiantExemplaire = identifiantExemplaire;
    }

    @Override
    public String toString() {
        return "Emprunt{" + "dateDebut=" + dateDebut + ", etat=" + etat + ", emailUsager=" + emailUsager + ", identifiantExemplaire=" + identifiantExemplaire + '}';
    }

    
}
