package fr.miage.bilalblaisenosal.back.control;

import fr.miage.bilalblaisenosal.back.control.OeuvreController;
import fr.miage.bilalblaisenosal.common.exception.OeuvreNotFoundException;
import fr.miage.bilalblaisenosal.common.exception.UsagerNotFoundException;
import fr.miage.bilalblaisenosal.common.metier.Usager;
import fr.miage.bilalblaisenosal.common.metier.Oeuvre;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    private TextField tf_filterusager_todelete;
    
    @FXML
    private ComboBox<Usager> cb_usager_todelete;
    
    @FXML
    private Label lb_delete_message;

    @FXML
    private TextField tf_email_toupdate;

    @FXML
    private TextField tf_nom_toupdate;

    @FXML
    private TextField tf_prenom_toupdate;

    @FXML
    private TextField tf_telephone_toupdate;
    
    @FXML
    private Label lb_update_message;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void update_cb_usager(KeyEvent event) {
        //TODO
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
    private void searchUsager(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEmail = tf_email_toupdate.getText();

        try {
            // Recherche de l'oeuvre concerné
            Usager usager = Usager.getUsagerByEmail(strEmail);
            tf_nom_toupdate.setText(usager.getNom());
            tf_prenom_toupdate.setText(usager.getPrenom());
            tf_telephone_toupdate.setText(usager.getTelephone());
            lb_update_message.setText("L'usager a été chargé. Vous pouvez modifier les champs et cliquer sur \"Modifier\".");
        } catch (SQLException | UsagerNotFoundException ex) {
            lb_update_message.setText("Aucun usager n'a été trouvé dans la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void updateUsager(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEmail = tf_email_toupdate.getText();
        String strNom = tf_nom_toupdate.getText();
        String strPrenom = tf_prenom_toupdate.getText();
        String strTel = tf_telephone_toupdate.getText();

        try {
            // Recherche de l'oeuvre concerné
            Usager usager = Usager.getUsagerByEmail(strEmail);
            usager.setEmail(strEmail);
            usager.setNom(strNom);
            usager.setPrenom(strPrenom);
            usager.setTelephone(strTel);
            usager.update();
            lb_update_message.setText("L'usager a été modifié dans la base.");
        } catch (SQLException | UsagerNotFoundException ex) {
            lb_update_message.setText("Un problème est survenu durant la modification dans la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void deleteUsager(ActionEvent event) {
        // Récupération des informations de l'interface
        String email = tf_filterusager_todelete.getText(); // TODO Récupérer l'id usager

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
