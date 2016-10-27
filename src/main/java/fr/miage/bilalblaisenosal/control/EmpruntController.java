
package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.metier.Auteur;
import fr.miage.bilalblaisenosal.metier.Emprunt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class EmpruntController implements Initializable{
    
    @FXML
    private TextField dateDebut;
    
    @FXML
    private ComboBox<String> etat;
    
    @FXML
    private TextField emailUsager;
    
    @FXML
    private TextField identifiantExemplaire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Mettre les informations dans le ComboBOX à partir de l'enum ETAT
    }
    
    @FXML
    private void ajouterEmprunt(ActionEvent event) {
        // Récupération des informations de l'interface
        String strDateDebut = dateDebut.getText();
        String strEtat = etat.getSelectionModel().getSelectedItem();
        String strEmailUsager = emailUsager.getText();
        String strIdentifiantExemplaire = identifiantExemplaire.getText();
        
        // Construction d'un emprunt
        Emprunt emprunt = new Emprunt(strDateDebut, strEtat, strEmailUsager, strIdentifiantExemplaire);
        
        try {
            // Insertion dans la base de données
            emprunt.insert();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void modifierEmprunt(ActionEvent event) {
        // Récupération des informations de l'interface
        String strDateDebut = dateDebut.getText();
        String strEtat = etat.getSelectionModel().getSelectedItem();
        String strEmailUsager = emailUsager.getText();
        String strIdentifiantExemplaire = identifiantExemplaire.getText();
        
        // Construction de l'emprunt concerné par la MAJ
        Emprunt emprunt = new Emprunt(strDateDebut, strEtat, strEmailUsager, strIdentifiantExemplaire);
        
        try {
            // Insertion dans la base de données
            emprunt.update();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerEmprunt(ActionEvent event) {
        // Récupération des informations de l'interface
        String strDateDebut = dateDebut.getText();
        String strEtat = etat.getSelectionModel().getSelectedItem();
        String strEmailUsager = emailUsager.getText();
        String strIdentifiantExemplaire = identifiantExemplaire.getText();
        
        // Construction de l'emprunt concerné par la MAJ
        Emprunt emprunt = new Emprunt(strDateDebut, strEtat, strEmailUsager, strIdentifiantExemplaire);
        
        try {
            // Insertion dans la base de données
            emprunt.delete();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
