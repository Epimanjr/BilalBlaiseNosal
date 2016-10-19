package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import java.sql.SQLException;
import java.util.Date;
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
    private Date dateDemande;
    
    /**
     * Email de l'usager concerné
     */
    private String emailUsager;

    /**
     * Construit une réservation
     * @param id
     * @param dateDemande
     * @param emailUsager 
     */
    public Reservation(int id, Date dateDemande, String emailUsager) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.emailUsager = emailUsager;
    }
    
    //TODO : Constructeur depuis Hashmap de fields
    
    public void insert() throws SQLException {
        String sql = "INSERT INTO reservation(email, dateDemande) VALUES('"+ this.emailUsager +"', '" + this.dateDemande + "');";
        Connector.insert(sql);
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
        String sql = "DELETE FROM reservation WHERE idReservation='"+this.id+"'";
        Connector.insert(sql);
               
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.dateDemande);
        hash = 23 * hash + Objects.hashCode(this.emailUsager);
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
        if (!Objects.equals(this.emailUsager, other.emailUsager)) {
            return false;
        }
        if (!Objects.equals(this.dateDemande, other.dateDemande)) {
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

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getEmailUsager() {
        return emailUsager;
    }

    public void setEmailUsager(String emailUsager) {
        this.emailUsager = emailUsager;
    }

}
