package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import fr.miage.bilalblaisenosal.exception.ObjetMetierNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class EmpruntTest {

    // Variables pour test
    private final String datePattern = "dd-MM-YYYY";
    private final String emailUsager = "maxime.blaise@outlook.fr";
    private final String identifiantEmprunt = "XB2212";

    public EmpruntTest() {
    }

    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE emprunt");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table emprunt");
        }

        Date currentDate = new GregorianCalendar().getTime();
        Emprunt emprunt = new Emprunt(new SimpleDateFormat(datePattern).format(currentDate), EtatEmprunt.ENCOURS.getValue(), emailUsager, identifiantEmprunt);
        try {
            emprunt.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 emprunt de la base
        ArrayList<Emprunt> listEmprunts = null;
        try {
            listEmprunts = Emprunt.getAllEmprunts();

            assertFalse(listEmprunts == null);
            assertEquals(listEmprunts.size(), 1);

            // Les informations doivent être identiques à l'original
            Emprunt copy = listEmprunts.get(0);
            assertTrue(emprunt.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        emprunt.setEtat(EtatEmprunt.FINI.getValue());
        try {
            emprunt.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'emprunt");
        }

        try {
            listEmprunts = Emprunt.getAllEmprunts();

            assertEquals(listEmprunts.size(), 1);
            Emprunt emprunt2 = listEmprunts.get(0);

            // Vérification des informations
            assertEquals(emprunt, emprunt2);
        } catch (SQLException ex) {
            fail("Impossible de récupérer l'emprunt mis à jour.");
        }

        try {
            emprunt.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer l'emprunt");
        }

        try {
            listEmprunts = Emprunt.getAllEmprunts();

            // La liste doit être vide ! 
            assertTrue(listEmprunts.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des emprunts (pour vérifier suppression)");
        }
    }
}
