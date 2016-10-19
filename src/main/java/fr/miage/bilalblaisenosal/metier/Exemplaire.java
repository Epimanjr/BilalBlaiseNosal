package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.exception.ObjetMetierNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private Etat etat;

    /**
     * ISBN de l'oeuvre associé.
     */
    private String oeuvre;

    public Exemplaire(Etat etat, String oeuvre) {
        this.etat = etat;
        this.oeuvre = oeuvre;
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

        return null;
    }

    /**
     * Permet de récupérer tous les exemplaires de la base de données.
     *
     * @return Liste d'exemplaires
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public static ArrayList<Exemplaire> getAllExemplaires() throws SQLException {

        return null;
    }

    /**
     * Insertion de l'exemplaire dans la base.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void insert() throws SQLException {

    }

    /**
     * Mise à jour de l'exemplaire.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void update() throws SQLException {

    }

    /**
     * Suppression de l'exemplaire.
     *
     * @throws SQLException Problème avec la requête ou la BDD
     */
    public void delete() throws SQLException {

    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(String oeuvre) {
        this.oeuvre = oeuvre;
    }

}
