package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import fr.miage.bilalblaisenosal.exception.UsagerNotFoundException;
import fr.miage.bilalblaisenosal.metier.Usager;
import fr.miage.bilalblaisenosal.metier.Oeuvre;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class UsagerController implements Initializable {

    @FXML
    private TextField tf_email_toadd;

    @FXML
    private TextField tf_nom_toadd;

    @FXML
    private TextField tf_prenom_toadd;

    @FXML
    private TextField tf_telephone_toadd;
    
    @FXML
    private Label lb_add_message;
    
    @FXML
    private TextField tf_email_todelete;
    
    @FXML
    private Label lb_delete_message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void addUsager(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEmail = tf_email_toadd.getText();
        String strNom = tf_nom_toadd.getText();
        String strPrenom = tf_prenom_toadd.getText();
        String strTel = tf_telephone_toadd.getText();

        // Construction d'un usager
        Usager usager = new Usager(strNom, strPrenom, strEmail, strTel);

        try {
            // Insertion dans la base de données
            usager.insert();
            lb_add_message.setText("L'usager a été ajoutée dans la base.");
        } catch (SQLException ex) {
            lb_add_message.setText("Un problème est survenu durant l'ajout dans la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteUsager(ActionEvent event) {
        // Récupération des informations de l'interface
        String email = tf_email_todelete.getText(); // TODO Récupérer l'id usager

        try {
            // Recherche de l'oeuvre concerné
            Usager usager = Usager.getUsagerByEmail(email);
            usager.delete();
            lb_delete_message.setText("L'usager a été retirée de la base.");
        } catch (SQLException | UsagerNotFoundException ex) {
            lb_delete_message.setText("Un problème est survenu durant la suppression de cet usager de la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
