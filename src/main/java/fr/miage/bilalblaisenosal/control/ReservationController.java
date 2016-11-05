package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import fr.miage.bilalblaisenosal.metier.Oeuvre;
import fr.miage.bilalblaisenosal.metier.Reservation;
import fr.miage.bilalblaisenosal.metier.Usager;
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
    private TextField tf_filteremail_toadd;

    @FXML
    private ComboBox<Usager> cb_usager_toadd;

    @FXML
    private TextField tf_filteroeuvre_toadd;

    @FXML
    private ComboBox<Oeuvre> cb_oeuvre_toadd;

    @FXML
    private TextField tf_filterresa_todelete;

    @FXML
    private ComboBox<Reservation> cb_resa_todelete;

    @FXML
    private Label lb_add_message;
    
    @FXML
    private Label lb_delete_message;

    private ArrayList<Oeuvre> listeOeuvres = new ArrayList<>();
    private ArrayList<Usager> listeUsagers = new ArrayList<>();
    private ArrayList<Reservation> listeReservation = new ArrayList<>();
    private FilterHelper<Oeuvre> filterHelperOeuvres;
    private FilterHelper<Usager> filterHelperUsagers;
    private FilterHelper<Reservation> filterHelperReservations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listeOeuvres = Oeuvre.getAllOeuvre();
            cb_oeuvre_toadd.setItems(FXCollections.observableArrayList(listeOeuvres));
            filterHelperOeuvres = new FilterHelper<>("", listeOeuvres);
            listeUsagers = Usager.getAllUsager();
            cb_usager_toadd.setItems(FXCollections.observableArrayList(listeUsagers));
            filterHelperUsagers = new FilterHelper<>("", listeUsagers);
            listeReservation = Reservation.getAllReservations();
            cb_resa_todelete.setItems(FXCollections.observableArrayList(listeReservation));
            filterHelperReservations = new FilterHelper<>("", listeReservation);
        } catch (SQLException ex) {
            lb_add_message.setText("Problème dans le chargement des oeuvres de la base.");
        }
    }

    @FXML
    private void update_cb_oeuvre(KeyEvent event) {
        String strFiltre = tf_filteroeuvre_toadd.getText();
        ArrayList<Oeuvre> newlist = filterHelperOeuvres.getWithFilter(strFiltre);
        cb_oeuvre_toadd.setItems(FXCollections.observableArrayList(newlist));
    }

    @FXML
    private void update_cb_usager(KeyEvent event) {
        String strFiltre = tf_filteremail_toadd.getText();
        ArrayList<Usager> newlist = filterHelperUsagers.getWithFilter(strFiltre);
        cb_usager_toadd.setItems(FXCollections.observableArrayList(newlist));
    }

    @FXML
    private void update_cb_resa(KeyEvent event) {
        this.update_cb_resa_core();
    }
    
    private void update_cb_resa_core() {
        String strFiltre = tf_filterresa_todelete.getText();
        ArrayList<Reservation> newlist = filterHelperReservations.getWithFilter(strFiltre);
        cb_resa_todelete.setItems(FXCollections.observableArrayList(newlist));
    }

    @FXML
    private void addReservation(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEmail = cb_usager_toadd.getSelectionModel().getSelectedItem().getEmail();
        String strIdoeuvre = cb_oeuvre_toadd.getSelectionModel().getSelectedItem().getISBN();

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
        this.update_cb_resa_core();
    }

    @FXML
    private void deleteReservation(ActionEvent event) {
        //Récupération de l'identifiant de la réservation
        int idResa = cb_resa_todelete.getSelectionModel().getSelectedItem().getId();

        //Suppression de la reservation
        Reservation resa_todelete = new Reservation();
        resa_todelete.setId(idResa);
        try {
            resa_todelete.delete();
            lb_delete_message.setText("La réservation a été supprimée de la base.");
        } catch (SQLException ex) {
            lb_delete_message.setText("Un problème est survenu durant la suppression dans la base.");
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
