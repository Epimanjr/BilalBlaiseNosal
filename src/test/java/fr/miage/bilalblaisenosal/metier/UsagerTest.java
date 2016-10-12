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
    public void testPersistence() {
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

        try {
            Usager usager2 = Usager.getUsagerByEmail(email);

            assertFalse(usager2 == null);

            // Vérification des informations
            assertEquals(usager2.getNom(), nom);
            assertEquals(usager2.getPrenom(), prenom);
            assertEquals(usager2.getEmail(), email);
            assertEquals(usager2.getTelephone(), telephone);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        } catch (UsagerNotFoundException ex) {
            fail("Erreur: usager non trouvé.");
        }

        usager.setNom("NOSAL");
        usager.setPrenom("Antoine");
        try {
            usager.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'usager");
        }

        try {
            Usager newUsager = Usager.getUsagerByEmail(email);

            assertEquals(newUsager.getNom(), "NOSAL");
            assertEquals(newUsager.getPrenom(), "Antoine");
            assertEquals(newUsager.getEmail(), email);
            assertEquals(newUsager.getTelephone(), telephone);
        } catch (SQLException | UsagerNotFoundException ex) {
            fail("Impossible de récupérer l'usager mis à jour.");
        }

        try {
            usager.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer l'usager");
        }

        try {
            listUsagers = Usager.getAllUsager();

            // La liste doit être vide ! 
            assertTrue(listUsagers.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des usagers (pour vérifier suppression)");
        }
    }

}
