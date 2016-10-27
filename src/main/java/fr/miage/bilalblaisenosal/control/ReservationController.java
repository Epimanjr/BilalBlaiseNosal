package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.metier.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tf_email_toadd;
    
    @FXML
    private TextField tf_idoeuvre_toadd;
    
    @FXML
    private Label lb_add_message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addReservation(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEmail = tf_email_toadd.getText();
        int strIdoeuvre = Integer.parseInt(tf_idoeuvre_toadd.getText());
        
        // Construction d'une reservation
        Date currentDate = new GregorianCalendar().getTime();
        String dateCourante = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(currentDate);
        Reservation reservation = new Reservation(dateCourante, strEmail, strIdoeuvre);
        
        try {
            // Insertion dans la base de données
            reservation.insert();
            lb_add_message.setText("La reservation a été ajoutée dans la base.");
        } catch (SQLException ex) {
            lb_add_message.setText("Un problème est survenu durant l'ajout dans la base.");
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
