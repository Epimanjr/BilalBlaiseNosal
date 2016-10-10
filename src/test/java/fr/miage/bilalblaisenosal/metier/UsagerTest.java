package fr.miage.bilalblaisenosal.metier;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class UsagerTest {

    public UsagerTest() {
    }

    /**
     * Test de l'insertion d'un usager dans la base de données.
     *
     */
    @Test
    public void testInsert() {
        Usager usager = new Usager("BLAISE", "Maxime", "maxime.blaise1@etu.univ-lorraine.fr", "06 06 06 06 06");
        try {
            usager.insert();
        } catch (SQLException e) {
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 usager de la base
        ArrayList<Usager> listUsagers = null;
        try {
            listUsagers = Usager.getAllUsager();

            assertFalse(listUsagers == null);
            assertEquals(listUsagers.size(), 1);

            // Les informations doivent être identiques à l'original
            Usager copy = listUsagers.get(0);
            assertTrue(usager.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

    }
}
