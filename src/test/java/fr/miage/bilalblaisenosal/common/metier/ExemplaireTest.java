/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.common.metier;

import fr.miage.bilalblaisenosal.common.metier.Etat;
import fr.miage.bilalblaisenosal.common.metier.Exemplaire;
import fr.miage.bilalblaisenosal.common.bdd.Connector;
import fr.miage.bilalblaisenosal.common.exception.ObjetMetierNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class ExemplaireTest {
    
    // Variables pour test
    private final String etat = Etat.BON.getValue();
    private final String oeuvre = "ISBN XB 22 12";
    
    public ExemplaireTest() {
    }
    
    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE exemplaire");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table exemplaire");
        }

        Exemplaire exemplaire = new Exemplaire(etat, oeuvre);
        try {
            exemplaire.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer 1 exemplaire de la base
        ArrayList<Exemplaire> listExemplaires = null;
        try {
            listExemplaires = Exemplaire.getAllExemplaires();

            assertFalse(listExemplaires == null);
            assertEquals(listExemplaires.size(), 1);

            // Les informations doivent être identiques à l'original
            Exemplaire copy = listExemplaires.get(0);
            assertTrue(exemplaire.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        try {
            Exemplaire exemplaire2 = Exemplaire.getExemplaireById(exemplaire.getIdentifiant());

            assertFalse(exemplaire2 == null);

            // Vérification des informations
            assertEquals(exemplaire2.getIdentifiant(), exemplaire.getIdentifiant());
            assertEquals(exemplaire2.getOeuvre(), oeuvre);
            assertEquals(exemplaire2.getEtat(), etat);
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        } catch (ObjetMetierNotFoundException ex) {
            fail("Erreur: exemplaire non trouvé.");
        }

        exemplaire.setEtat(Etat.ABIME.getValue());
        try {
            exemplaire.update();
        } catch (SQLException ex) {
            fail("Impossible de mettre à jour l'exemplaire");
        }

        try {
           Exemplaire exemplaire2 = Exemplaire.getExemplaireById(exemplaire.getIdentifiant());

            assertFalse(exemplaire2 == null);

            // Vérification des informations
            assertEquals(exemplaire2.getIdentifiant(), exemplaire.getIdentifiant());
            assertEquals(exemplaire2.getOeuvre(), oeuvre);
            assertEquals(exemplaire2.getEtat(), Etat.ABIME.getValue());
        } catch (SQLException | ObjetMetierNotFoundException  ex) {
            fail("Impossible de récupérer l'exemplaire mis à jour.");
        }

        try {
            exemplaire.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer l'exemplaire");
        }

        try {
            listExemplaires = Exemplaire.getAllExemplaires();

            // La liste doit être vide ! 
            assertTrue(listExemplaires.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des exemplaires (pour vérifier suppression)");
        }
    }
}
