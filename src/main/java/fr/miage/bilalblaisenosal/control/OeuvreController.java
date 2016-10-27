package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
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
 * FXML Controller class
 *
 * @author Maxime BLAISE
 */
public class OeuvreController implements Initializable {

    @FXML
    private TextField tf_isbn_toadd;
    
    @FXML
    private TextField tf_titre;
    
    @FXML
    private TextField tf_editeur;
    
    @FXML
    private Label lb_add_message;
    
    @FXML
    private TextField tf_isbn_todelete;
    
    @FXML
    private Label lb_delete_message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addOeuvre(ActionEvent event) {
        // Récupération des informations de l'interface
        String strIsbn = tf_isbn_toadd.getText();
        String strTitre = tf_titre.getText();
        String strEditeur = tf_editeur.getText();
        
        // Construction d'une oeuvre
        Oeuvre oeuvre = new Oeuvre(strIsbn, strTitre, strEditeur);
        
        try {
            // Insertion dans la base de données
            oeuvre.insert();
            lb_add_message.setText("L'oeuvre a été ajoutée dans la base.");
        } catch (SQLException ex) {
            lb_add_message.setText("Un problème est survenu durant l'ajout dans la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void deleteOeuvre(ActionEvent event) {
        // Récupération des informations de l'interface
        String strIsbn = tf_isbn_todelete.getText();
        
        try {
            // Recherche de l'oeuvre concerné
            Oeuvre oeuvre = Oeuvre.getOeuvreByISBN(strIsbn);
            oeuvre.delete();
            lb_delete_message.setText("L'oeuvre a été retirée de la base.");
        } catch (SQLException | OeuvreNotFoundException ex) {
            lb_delete_message.setText("Un problème est survenu durant la suppression de cette oeuvre de la base.");
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
