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
public class ReservationTest {

    // Variables pour test
    private final String datePattern = "yyyy-MM-dd";
    private final String emailUsager = "antoine.nosal@outlook.fr";
    private final String idOeuvre = "XB2212";

    public ReservationTest() {
    }

    @Test
    public void testPersistence() {
        try {
            Connector.insert("TRUNCATE reservation");
            System.out.println("Truncate OK");
        } catch (SQLException ex) {
            fail("Erreur: impossible de vider la table reservation");
        }

        Date currentDate = new GregorianCalendar().getTime();
        String date = new SimpleDateFormat(datePattern).format(currentDate);
        Reservation reservation = new Reservation(date, emailUsager, idOeuvre);
        try {
            reservation.insert();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erreur de connexion à la base de données.");
        }

        // On doit récupérer une reservation de la base
        ArrayList<Reservation> listReservations = null;
        try {
            listReservations = Reservation.getAllReservations();

            assertFalse(listReservations == null);
            assertEquals(listReservations.size(), 1);

            // Les informations doivent être identiques à l'original
            Reservation copy = listReservations.get(0);
            assertTrue(reservation.equals(copy));
        } catch (SQLException ex) {
            fail("Erreur de connexion à la base de données.");
        }

        try {
            reservation.delete();
        } catch (SQLException ex) {
            fail("Impossible de supprimer la reservation");
        }

        try {
            listReservations = Reservation.getAllReservations();

            // La liste doit être vide ! 
            assertTrue(listReservations.isEmpty());
        } catch (SQLException e) {
            fail("Impossible de récupérer la liste des reservations (pour vérifier suppression)");
        }
    }
}
