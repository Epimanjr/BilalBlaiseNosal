/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.back.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class MenuBackEndController implements Initializable {

    private static String FXML_FOLDER = "/fxml/";
    private static String WINDOWNAME_PREFIX = "BibalBlaiseNosal - ";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Permet de lancer une fenêtre spécifique
     * @param fxmlName Nom du fichier FXML associé
     * @param windowName Nom de la fenêtre qui sera ouverte
     * @throws Exception 
     */
    private void launch(String fxmlName, String windowName) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FOLDER + fxmlName + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(WINDOWNAME_PREFIX + windowName);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void launch_oeuvre(ActionEvent event) throws Exception {
        this.launch("Oeuvre", "Gestion des oeuvres");
    }
    
    @FXML
    private void launch_magazine(ActionEvent event) throws Exception {
        this.launch("Oeuvre", "Gestion des oeuvres");
    }
    
    @FXML
    private void launch_livre(ActionEvent event) throws Exception {
        this.launch("Oeuvre_suite", "Gestion des oeuvres");
    }
    
    @FXML
    private void launch_exemplaire(ActionEvent event) throws Exception {
        this.launch("Exemplaire", "Gestion des exemplaires");
    }
    
    @FXML
    private void launch_usager(ActionEvent event) throws Exception {
        this.launch("Usager", "Gestion des usagers");
    }
    
    @FXML
    private void launch_usager_suite(ActionEvent event) throws Exception {
        this.launch("Usager_suite", "Gestion des usagers");
    }
    
}
