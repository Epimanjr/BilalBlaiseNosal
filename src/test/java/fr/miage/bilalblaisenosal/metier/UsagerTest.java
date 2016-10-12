package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import fr.miage.bilalblaisenosal.exception.UsagerNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class UsagerTest {

    private final String nom = "BLAISE";
    private final String prenom = "Maxime";
    private final String email = "maxime.blaise1@etu.univ-lorraine.fr";
    private final String telephone = "06 06 06 06 06";

    public UsagerTest() {

    }

    /**
     * Test de l'insertion d'un usager dans la base de données.
     *
     */
    @Test
    public void testInsert() {
        try {
            Connector.insert("TRUNCATE usager");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table usager");
        }

        Usager usager = new Usager(nom, prenom, email, telephone);
        try {
            usager.insert();
        } catch (SQLException e) {
            e.printStackTrace();
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

    @Test
    public void testGetUsagerByEmail() {
        try {
            Usager usager = Usager.getUsagerByEmail(email);

            assertFalse(usager == null);

            // Vérification des informations
            assertEquals(usager.getNom(), nom);
            assertEquals(usager.getPrenom(), prenom);
            assertEquals(usager.getEmail(), email);
            assertEquals(usager.getTelephone(), telephone);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        } catch (UsagerNotFoundException ex) {
            fail("Erreur: usager non trouvé.");
        }
    }
}
