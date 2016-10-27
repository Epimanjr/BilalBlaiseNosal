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
    private int idOeuvre;
    
    /**
     * Construit une réservation
     *
     * @param dateDemande
     * @param emailUsager
     */
    public Reservation(String dateDemande, String emailUsager, int idOeuvre) {
        this.dateDemande = dateDemande;
        this.emailUsager = emailUsager;
        this.idOeuvre = idOeuvre;
    }

    public Reservation(HashMap<String, String> askedFields) {
        this.emailUsager = askedFields.get("emailUsager");
        this.dateDemande = askedFields.get("dateDemande");
        this.idOeuvre = Integer.parseInt(askedFields.get("idOeuvre"));
    }

    public void insert() throws SQLException {
        String sql = "INSERT INTO reservation(emailUsager, dateDemande, idOeuvre) VALUES('" + this.emailUsager + "', '" + this.dateDemande + "', '" + this.idOeuvre + "');";
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
            int idOeuvre = results.getInt("idOeuvre");

            // Création d'une instance d'une reservation
            Reservation reservation = new Reservation(dateDemande, emailUsager, idOeuvre);
            reservation.setId(id);
            listreservations.add(reservation);
        }

        return listreservations;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        if (this.idOeuvre != other.idOeuvre) {
            return false;
        }
        if (!Objects.equals(this.dateDemande, other.dateDemande)) {
            return false;
        }
        if (!Objects.equals(this.emailUsager, other.emailUsager)) {
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

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

}
