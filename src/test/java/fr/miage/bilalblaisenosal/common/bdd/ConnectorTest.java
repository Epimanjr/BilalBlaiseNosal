/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.common.bdd;

import fr.miage.bilalblaisenosal.common.bdd.Connector;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxim
 */
public class ConnectorTest {

    private String baseName = "bilal";
    private String login = "root";
    private String pass = "";

    public ConnectorTest() {
    }

    /**
     * Permet de tester la connexion à la base de données.
     */
    @Test
    public void testConnexion() {
        Connector.getConnector().setConnectionInformation();
        try {
            Connector.getConnector().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }
    }
}
