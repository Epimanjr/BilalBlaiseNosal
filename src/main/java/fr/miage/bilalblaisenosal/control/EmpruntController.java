
package fr.miage.bilalblaisenosal.control;

import fr.miage.bilalblaisenosal.metier.Emprunt;
import fr.miage.bilalblaisenosal.metier.EtatEmprunt;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
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
import javafx.scene.control.TextField;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class EmpruntController implements Initializable{
    
    @FXML
    private ComboBox<Exemplaire> cb_exemplaire_toadd;
    
    @FXML
    private TextField tf_email_toadd;
    
    @FXML
    private TextField tf_idexemplaire_toadd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Mettre les informations dans le ComboBOX à partir de l'enum ETAT
            /*ArrayList<String> listEtatEmprunt = new ArrayList<>();
            for(EtatEmprunt etatTmp: EtatEmprunt.values()) {
            listEtatEmprunt.add(etatTmp.getValue());
            }
            etat.setItems(FXCollections.observableArrayList(listEtatEmprunt));*/
            
            // Mettre la liste des exemplaires dans le ComboBox<Exemplaire>
            ArrayList<Exemplaire> listExemplaires = Exemplaire.getAllExemplaires();
            cb_exemplaire_toadd.setItems(FXCollections.observableArrayList(listExemplaires));
        } catch (SQLException ex) {
            // TODO Afficher message dans interface
            Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addEmprunt(ActionEvent event) {
        // Récupération des informations de l'interface
        String strEtat = EtatEmprunt.ENCOURS.getValue();
        String strEmailUsager = tf_email_toadd.getText();
        String strIdentifiantExemplaire = cb_exemplaire_toadd.getSelectionModel().getSelectedItem().getIdentifiant();
        
        // Construction d'un emprunt
        Emprunt emprunt = new Emprunt(ControlHelper.getCurrentDate(), strEtat, strEmailUsager, strIdentifiantExemplaire);
        
        try {
            // Insertion dans la base de données
            emprunt.insert();
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
