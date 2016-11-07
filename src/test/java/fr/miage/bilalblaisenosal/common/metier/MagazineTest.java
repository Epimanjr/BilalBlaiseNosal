package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.metier.Magazine;
import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.OeuvreNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class MagazineTest {

    // Variables pour test
    private final String ISBN = "978-1-2345-6789-7";
    private final String titre = "Max le paresseux";
    private final String editeur = "Antoine Corp.";
    private final String dateParution = "2016-11-01";

    public MagazineTest() {
    }

    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE oeuvre");
            Connector.insert("TRUNCATE magazine");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table oeuvre et/ou magazine");
        }

        Magazine magazine = new Magazine(ISBN, titre, editeur, dateParution);
        try {
            magazine.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 oeuvre de la base
        ArrayList<Magazine> listMagazines = null;
        try {
            listMagazines = Magazine.getAllMagazines();

            assertFalse(listMagazines == null);
            assertEquals(listMagazines.size(), 1);

            // Les informations doivent être identiques à l'original
            Magazine copy = listMagazines.get(0);
            assertTrue(magazine.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        try {
            Magazine oeuvre2 = Magazine.getMagazineByISBN(ISBN);

            assertFalse(oeuvre2 == null);

            // Vérification des informations
            assertEquals(oeuvre2.getISBN(), ISBN);
            assertEquals(oeuvre2.getTitre(), titre);
            assertEquals(oeuvre2.getEditeur(), editeur);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        } catch (OeuvreNotFoundException ex) {
            Logger.getLogger(MagazineTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        magazine.setTitre("Nouveau titre");
        magazine.setEditeur("Nouvel editeur");
        try {
            magazine.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'oeuvre");
        }

        try {
            Magazine newMagazine = Magazine.getMagazineByISBN(ISBN);

            // Vérification des informations
            assertEquals(newMagazine.getISBN(), ISBN);
            assertEquals(newMagazine.getTitre(), "Nouveau titre");
            assertEquals(newMagazine.getEditeur(), "Nouvel editeur");
        } catch (SQLException ex) {
            fail("Impossible de récupérer l'oeuvre mis à jour.");
        } catch (OeuvreNotFoundException ex) {
            Logger.getLogger(MagazineTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            magazine.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer le magazine");
        }

        try {
            listMagazines = Magazine.getAllMagazines();

            // La liste doit être vide ! 
            assertTrue(listMagazines.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des oeuvres (pour vérifier suppression)");
        }
    }
}
