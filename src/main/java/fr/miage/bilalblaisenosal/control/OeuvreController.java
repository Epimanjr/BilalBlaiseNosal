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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Maxime BLAISE
 */
public class OeuvreController implements Initializable {

    @FXML
    private TextField isbn;
    
    @FXML
    private TextField titre;
    
    @FXML
    private TextField editeur;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterOeuvre(ActionEvent event) {
        // Récupération des informations de l'interface
        String strIsbn = isbn.getText();
        String strTitre = titre.getText();
        String strEditeur = editeur.getText();
        
        // Construction d'une oeuvre
        Oeuvre oeuvre = new Oeuvre(strIsbn, strTitre, strEditeur);
        
        try {
            // Insertion dans la base de données
            oeuvre.insert();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerOeuvre(ActionEvent event) {
        // Récupération des informations de l'interface
        String strIsbn = ""; // TODO Récupérer l'ISBN
        
        try {
            // Recherche de l'oeuvre concerné
            Oeuvre oeuvre = Oeuvre.getOeuvreByISBN(strIsbn);
            oeuvre.delete();
        } catch (SQLException | OeuvreNotFoundException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
