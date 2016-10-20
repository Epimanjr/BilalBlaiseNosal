/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.metier;

import fr.miage.bilalblaisenosal.bdd.Connector;
import fr.miage.bilalblaisenosal.exception.ObjetMetierNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class AuteurTest {
    
    // Variables pour test
    private final String nom = "BLAISE";
    private final String prenom = "Maxime";
    
    public AuteurTest() {
    }
    
    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE auteur");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table auteur");
        }

        Auteur auteur = new Auteur(nom, prenom);
        try {
            auteur.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 auteur de la base
        ArrayList<Auteur> listAuteurs = null;
        try {
            listAuteurs = Auteur.getAllAuteurs();

            assertFalse(listAuteurs == null);
            assertEquals(listAuteurs.size(), 1);

            // Les informations doivent être identiques à l'original
            Auteur copy = listAuteurs.get(0);
            assertTrue(auteur.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        try {
            Auteur auteur2 = Auteur.getAuteurById(auteur.getIdAuteur());

            assertFalse(auteur2 == null);

            // Vérification des informations
            assertEquals(auteur2, auteur);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        auteur.setPrenomAuteur("Antoine");
        try {
            auteur.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'auteur");
        }

        try {
           Auteur auteur2 = Auteur.getAuteurById(auteur.getIdAuteur());

            assertFalse(auteur2 == null);

            // Vérification des informations
            assertEquals(auteur2, auteur);
        } catch (SQLException  ex) {
            fail("Impossible de récupérer l'auteur mis à jour.");
        }

        try {
            auteur.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer l'auteur");
        }

        try {
            listAuteurs = Auteur.getAllAuteurs();

            // La liste doit être vide ! 
            assertTrue(listAuteurs.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des auteurs (pour vérifier suppression)");
        }
    }
}
