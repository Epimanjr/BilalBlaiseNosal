package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Reservation {

    /**
     * Identifiant de la réservation
     */
    private int id;

    /**
     * Date où l'usager a effectué la demande de réservation
     */
    private String dateDemande;

    /**
     * Email de l'usager concerné
     */
    private String emailUsager;

    /**
     * Identifiant de l'oeuvre réservée
     */
    private String ISBNOeuvre;

    public Reservation() {
    }
    
    /**
     * Construit une réservation
     *
     * @param dateDemande
     * @param emailUsager
     */
    public Reservation(String dateDemande, String emailUsager, String idOeuvre) {
        this.dateDemande = dateDemande;
        this.emailUsager = emailUsager;
        this.ISBNOeuvre = idOeuvre;
    }

    public Reservation(HashMap<String, String> askedFields) {
        this.emailUsager = askedFields.get("emailUsager");
        this.dateDemande = askedFields.get("dateDemande");
        this.ISBNOeuvre = askedFields.get("idOeuvre");
    }

    public void insert() throws SQLException {
        String sql = "INSERT INTO reservation(emailUsager, dateDemande, idOeuvre) VALUES('" + this.emailUsager + "', '" + this.dateDemande + "', '" + this.ISBNOeuvre + "');";
        Connector.insert(sql);

        String lastIdSql = "SELECT LAST_INSERT_ID() AS id FROM reservation";
        ResultSet results = Connector.select(lastIdSql);
        if (results.next()) {
            int id = results.getInt("id");
            this.setId(id);
        }
    }

    /*
    public void update() throws SQLException {
        String sql = "UPDATE reservation SET ";
        sql += "emailUsager='" + this.emailUsager + "', ";
        sql += "dateDemande='" + this.dateDemande + "', ";
        sql += "WHERE idReservation='" + this.id + "'";
        Connector.insert(sql);
    }
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM reservation WHERE idReservation='" + this.id + "'";
        Connector.insert(sql);

    }

    public static ArrayList<Reservation> getAllReservations() throws SQLException {
        ArrayList<Reservation> listreservations = new ArrayList<>();

        String sql = "SELECT * FROM reservation";
        ResultSet results = Connector.select(sql);
        while (results.next()) {
            // Récupération des informations
            int id = results.getInt("idReservation");
            String emailUsager = results.getString("emailUsager");
            String dateDemande = results.getString("dateDemande");
            String idOeuvre = results.getString("idOeuvre");

            // Création d'une instance d'une reservation
            Reservation reservation = new Reservation(dateDemande, emailUsager, idOeuvre);
            reservation.setId(id);
            listreservations.add(reservation);
        }

        return listreservations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.dateDemande);
        hash = 59 * hash + Objects.hashCode(this.emailUsager);
        hash = 59 * hash + Objects.hashCode(this.ISBNOeuvre);
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.dateDemande, other.dateDemande)) {
            return false;
        }
        if (!Objects.equals(this.emailUsager, other.emailUsager)) {
            return false;
        }
        if (!Objects.equals(this.ISBNOeuvre, other.ISBNOeuvre)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getEmailUsager() {
        return emailUsager;
    }

    public void setEmailUsager(String emailUsager) {
        this.emailUsager = emailUsager;
    }

    public String getISBNOeuvre() {
        return ISBNOeuvre;
    }

    public void setISBNOeuvre(String ISBNOeuvre) {
        this.ISBNOeuvre = ISBNOeuvre;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + dateDemande + "(pour l'ISBN " + ISBNOeuvre + "par " + emailUsager + ")";
    }
    
}
