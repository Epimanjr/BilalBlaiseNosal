/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.exception.ObjetMetierNotFoundException;
import fr.miage.bilalblaisenosal.metier.Etat;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
import fr.miage.bilalblaisenosal.metier.Oeuvre;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class ExemplaireController implements Initializable {

    @FXML
    private ComboBox<String> cb_etat_toadd;

    @FXML
    private ComboBox<Oeuvre> cb_isbn_toadd;

    @FXML
    private ComboBox<Exemplaire> cb_exemplaire_todelete;

    @FXML
    private TextField tf_isbn_toadd;

    @FXML
    private TextField tf_exemplaire_todelete;
    
    @FXML
    private Label lb_add_message;
    
    @FXML
    private Label lb_delete_message;

    private ArrayList<Oeuvre> oeuvres = new ArrayList<>();
    private ArrayList<Exemplaire> exemplaires = new ArrayList<>();
    private FilterHelper<Oeuvre> filterOeuvre;
    private FilterHelper<Exemplaire> filterExemplaire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Mettre les états 
        ArrayList<String> etats = new ArrayList<>();
        for (Etat etat : Etat.values()) {
            etats.add(etat.getValue());
        }
        cb_etat_toadd.setItems(FXCollections.observableArrayList(etats));

        try {
            // Mettre les oeuvres disponibles dans le ComboBox<Oeuvre>
            oeuvres = Oeuvre.getAllOeuvre();
            cb_isbn_toadd.setItems(FXCollections.observableArrayList(oeuvres));
            filterOeuvre = new FilterHelper<>("", oeuvres);
        } catch (SQLException ex) {
            // TODO Affichage d'un message d'erreur dans l'interface
            Logger.getLogger(ExemplaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.updateListExemplaires();
    }

    @FXML
    private void update_cb_oeuvre(KeyEvent event) {
        String strFilter = tf_isbn_toadd.getText();

        ArrayList<Oeuvre> newList = filterOeuvre.getWithFilter(strFilter);

        cb_isbn_toadd.setItems(FXCollections.observableArrayList(newList));
    }

    @FXML
    private void update_cb_exemplaire(KeyEvent event) {
        String strFilter = tf_exemplaire_todelete.getText();

        ArrayList<Exemplaire> newList = filterExemplaire.getWithFilter(strFilter);

        cb_exemplaire_todelete.setItems(FXCollections.observableArrayList(newList));
    }

    @FXML
    private void addExemplaire(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEtat = cb_etat_toadd.getSelectionModel().getSelectedItem().toString();
        String strIsbn = cb_isbn_toadd.getSelectionModel().getSelectedItem().getISBN();

        // Construction d'un exemplaire
        Exemplaire exemplaire = new Exemplaire(strEtat, strIsbn);

        try {
            // Insertion dans la base de données
            exemplaire.insert();
            this.updateListExemplaires();
            lb_add_message.setText("L'exemplaire a bien été enregistré.");
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateListExemplaires() {
        try {
            // Mettre les exemplaires disponibles dans le ComboBox<Exemplaire>
            exemplaires = Exemplaire.getAllExemplaires();
            cb_exemplaire_todelete.setItems(FXCollections.observableArrayList(exemplaires));
            filterExemplaire = new FilterHelper<>("", exemplaires);
        } catch (SQLException ex) {
            // TODO Affichage d'un message d'erreur dans l'interface
            Logger.getLogger(ExemplaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* @FXML
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
    */
    @FXML
    private void supprimerExemplaire(ActionEvent event) {     
        try {
            // Recherche de l'exemmlaire concerné
            Exemplaire exemplaire = cb_exemplaire_todelete.getSelectionModel().getSelectedItem();
            exemplaire.delete();
            lb_delete_message.setText("L'exemplaire a bien été supprimé.");
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
}
