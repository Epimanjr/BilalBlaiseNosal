/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Maxime BLAISE
 */
public class OeuvreTest {

    // Variables pour test
    private final String ISBN = "978-1-2345-6789-7";
    private final String titre = "Max le paresseux";
    private final String editeur = "Antoine Corp.";

    public OeuvreTest() {
    }

    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE oeuvre");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table oeuvre");
        }

        Oeuvre oeuvre = new Oeuvre(ISBN, titre, editeur);
        try {
            oeuvre.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 oeuvre de la base
        ArrayList<Oeuvre> listOeuvres = null;
        try {
            listOeuvres = Oeuvre.getAllOeuvre();

            assertFalse(listOeuvres == null);
            assertEquals(listOeuvres.size(), 1);

            // Les informations doivent être identiques à l'original
            Oeuvre copy = listOeuvres.get(0);
            assertTrue(oeuvre.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        try {
            Oeuvre oeuvre2 = Oeuvre.getOeuvreByISBN(ISBN);

            assertFalse(oeuvre2 == null);

            // Vérification des informations
            assertEquals(oeuvre2.getISBN(), ISBN);
            assertEquals(oeuvre2.getTitre(), titre);
            assertEquals(oeuvre2.getEditeur(), editeur);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        } catch (OeuvreNotFoundException ex) {
            fail("Erreur: oeuvre non trouvé.");
        }

        oeuvre.setTitre("Nouveau titre");
        oeuvre.setEditeur("Nouvel editeur");
        try {
            oeuvre.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'oeuvre");
        }

        try {
            Oeuvre newOeuvre = Oeuvre.getOeuvreByISBN(ISBN);

            // Vérification des informations
            assertEquals(newOeuvre.getISBN(), ISBN);
            assertEquals(newOeuvre.getTitre(), titre);
            assertEquals(newOeuvre.getEditeur(), editeur);
        } catch (SQLException | OeuvreNotFoundException ex) {
            fail("Impossible de récupérer l'oeuvre mis à jour.");
        }

        try {
            oeuvre.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer l'oeuvre");
        }

        try {
            listOeuvres = Oeuvre.getAllOeuvre();

            // La liste doit être vide ! 
            assertTrue(listOeuvres.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des oeuvres (pour vérifier suppression)");
        }
    }
}
