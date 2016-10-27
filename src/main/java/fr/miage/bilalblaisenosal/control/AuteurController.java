
package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.OeuvreNotFoundException;
import fr.miage.bilalblaisenosal.metier.Auteur;
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
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class AuteurController implements Initializable{
    
    @FXML
    private TextField nomAuteur;
    
    @FXML
    private TextField prenomAuteur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void ajouterAuteur(ActionEvent event) {
        // Récupération des informations de l'interface
        String strNomAuteur = nomAuteur.getText();
        String strPrenomAuteur = prenomAuteur.getText();
        
        // Construction d'un auteur
        Auteur auteur = new Auteur(strNomAuteur, strPrenomAuteur);
        
        try {
            // Insertion dans la base de données
            auteur.insert();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void modifierAuteur(ActionEvent event) {
        // Récupération des informations de l'interface
        int idAuteur = 0;
        String strNomAuteur = nomAuteur.getText();
        String strPrenomAuteur = prenomAuteur.getText();
        
        // Construction d'un auteur
        Auteur auteur = new Auteur(idAuteur, strNomAuteur, strPrenomAuteur);
        
        try {
            // Mise à jour dans la base de données
            auteur.update();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerAuteur(ActionEvent event) {
        // Récupération des informations de l'interface
        int idAuteur = 0; // TODO Récupérer l'id auteur
        
        try {
            // Recherche de l'oeuvre concerné
            Auteur auteur = Auteur.getAuteurById(idAuteur);
            auteur.delete();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
