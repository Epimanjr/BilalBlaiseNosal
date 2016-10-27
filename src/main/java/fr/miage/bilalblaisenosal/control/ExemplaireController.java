/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.ObjetMetierNotFoundException;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
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
public class ExemplaireController implements Initializable {

    @FXML
    private TextField etat;
    
    @FXML
    private TextField isbn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void ajouterExemplaire(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEtat = etat.getText();
        String strIsbn = isbn.getText();
        
        // Construction d'un exemplaire
        Exemplaire exemplaire = new Exemplaire(strEtat, strIsbn);
        
        try {
            // Insertion dans la base de données
            exemplaire.insert();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void modifierExemplaire(ActionEvent event) {
        // Récupération des informations de l'interface
        String idExemplaire = "";
        String strEtat = etat.getText();
        String strIsbn = isbn.getText();
        
        // Construction d'un exemplaire
        Exemplaire exemplaire = new Exemplaire(idExemplaire, strEtat, strIsbn);
        
        try {
            // Mise à jour dans la base de données
            exemplaire.update();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerExemplaire(ActionEvent event) {
        // Récupération des informations de l'interface
        String idExemplaire = ""; // TODO Récupérer l'id exemplaire
        
        try {
            // Recherche de l'exemmlaire concerné
            Exemplaire exemplaire = Exemplaire.getExemplaireById(idExemplaire);
            exemplaire.delete();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ObjetMetierNotFoundException ex) {
            Logger.getLogger(ExemplaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
