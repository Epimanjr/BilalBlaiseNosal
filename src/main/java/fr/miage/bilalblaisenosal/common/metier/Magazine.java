package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.OeuvreNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Magazine extends Oeuvre {

    /**
     * Date de parution du magazine
     */
    private String dateParution;

    public Magazine(String ISBN, String titre, String editeur, String dateParution) {
        super(ISBN, titre, editeur);
        this.dateParution = dateParution;
    }

    public Magazine(HashMap<String, String> askedFields) {
        super(askedFields.get("ISBN"),askedFields.get("titre"),askedFields.get("editeur"));
        this.dateParution = askedFields.get("dateParution");
    }

    public void insert() throws SQLException {
        super.insert();
        String sql = "INSERT INTO magazine(ISBN, dateParution) VALUES('" + this.getISBN() + "', '" + this.dateParution + "');";
        Connector.insert(sql);

        /*String lastIdSql = "SELECT LAST_INSERT_ID() AS id FROM magazine";
        ResultSet results = Connector.select(lastIdSql);
        if (results.next()) {
            String isbn = results.getString("isbn");
            this.setISBN(isbn);
        }*/
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM magazine WHERE ISBN='" + this.getISBN() + "'";
        Connector.insert(sql);
        super.delete();
    }

    public static ArrayList<Magazine> getAllMagazines() throws SQLException {
        ArrayList<Magazine> listmagazines = new ArrayList<>();

        String sql = "SELECT * FROM magazine";
        ResultSet results = Connector.select(sql);
        while (results.next()) {
            try {
                // Récupération des informations
                String isbn = results.getString("ISBN");
                String dateParution = results.getString("dateParution");
                Oeuvre oeuvre = Oeuvre.getOeuvreByISBN(isbn);
                
                // Création d'une instance d'une magazine
                Magazine magazine = new Magazine(isbn, oeuvre.getTitre(), oeuvre.getEditeur(), dateParution);
                listmagazines.add(magazine);
            } catch (OeuvreNotFoundException ex) {
                Logger.getLogger(Magazine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listmagazines;
    }

    public static Magazine getMagazineByISBN(String ISBNParam) throws SQLException, OeuvreNotFoundException {
        Oeuvre oeuvre = Oeuvre.getOeuvreByISBN(ISBNParam);
        Magazine magazine = new Magazine(oeuvre.getISBN(), oeuvre.getTitre(), oeuvre.getEditeur(), "");
        String sql = "SELECT * FROM magazine WHERE ISBN='"+ISBNParam+"'";
        ResultSet results = Connector.select(sql);
        if(results.next()) {
            // Récupération des informations
            String dateParution = results.getString("dateParution");
            
            // Création d'une instance de Oeuvre
            magazine.setDateParution(dateParution);
            return magazine;
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.dateParution);
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
        final Magazine other = (Magazine) obj;
        if (!Objects.equals(this.dateParution, other.dateParution)) {
            return false;
        }
        return true;
    }

    public String getDateParution() {
        return dateParution;
    }

    public void setDateParution(String dateParution) {
        this.dateParution = dateParution;
    }

    @Override
    public String toString() {
        return "[" + this.getISBN() + "] " + dateParution + " (titre: " + this.getTitre() + " édité par " + this.getEditeur() + ")";
    }
    
}
