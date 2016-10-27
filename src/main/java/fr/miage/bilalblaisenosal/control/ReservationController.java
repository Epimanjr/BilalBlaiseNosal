package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import fr.miage.bilalblaisenosal.metier.Oeuvre;
import fr.miage.bilalblaisenosal.metier.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    private ComboBox<String> cb_oeuvre_toadd;
    
    @FXML
    private Label lb_add_message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Oeuvre> listOeuvres = Oeuvre.getAllOeuvre();
            ArrayList<String> listOeuvresAsString = new ArrayList<>();
            for(Oeuvre oeuvre : listOeuvres) {
                listOeuvresAsString.add(oeuvre.getISBN() + " - " + oeuvre.getTitre() + " - " + oeuvre.getEditeur());
            }
            cb_oeuvre_toadd.setItems(FXCollections.observableArrayList(listOeuvresAsString));
        } catch (SQLException ex) {
            lb_add_message.setText("Problème dans le chargement des oeuvres de la base.");
        }
    }
    
    @FXML
    private void update_cb_oeuvre(KeyEvent event) {
        String strTitreRecherche = tf_idoeuvre_toadd.getText();
        try {
            ArrayList<Oeuvre> listOeuvres = Oeuvre.getAllOeuvreByTitre(strTitreRecherche);
            ArrayList<String> listOeuvresAsString = new ArrayList<>();
            for(Oeuvre oeuvre : listOeuvres) {
                listOeuvresAsString.add(oeuvre.getISBN() + " - " + oeuvre.getTitre() + " - " + oeuvre.getEditeur());
            }
            cb_oeuvre_toadd.setItems(FXCollections.observableArrayList(listOeuvresAsString));
        } catch (SQLException ex) {
            lb_add_message.setText("Problème dans le chargement des oeuvres de la base.");
        }
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
