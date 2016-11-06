package fr.miage.bilalblaisenosal.front.control;

import fr.miage.bilalblaisenosal.back.control.OeuvreController;
import fr.miage.bilalblaisenosal.common.helper.FilterHelper;
import fr.miage.bilalblaisenosal.common.helper.ControlHelper;
import fr.miage.bilalblaisenosal.common.metier.Emprunt;
import fr.miage.bilalblaisenosal.common.metier.Etat;
import fr.miage.bilalblaisenosal.common.metier.EtatEmprunt;
import fr.miage.bilalblaisenosal.common.metier.Exemplaire;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class EmpruntController implements Initializable {

    @FXML
    private ComboBox<Exemplaire> cb_exemplaire_toadd;

    @FXML
    private ComboBox<String> cb_etat_exemplaire;

    @FXML
    private ComboBox<Emprunt> cb_emprunt_todelete;

    @FXML
    private TextField tf_email_toadd;

    @FXML
    private TextField tf_idexemplaire_toadd;

    @FXML
    private TextField tf_emprunt_todelete;

    @FXML
    private Label lb_add_message;
    
    @FXML
    private Label lb_delete_message;
    

    private ArrayList<Emprunt> emprunts = new ArrayList<>();
    private FilterHelper<Emprunt> filterEmprunt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Mettre les états 
        ArrayList<String> etats = new ArrayList<>();
        for (Etat etat : Etat.values()) {
            etats.add(etat.getValue());
        }
        cb_etat_exemplaire.setItems(FXCollections.observableArrayList(etats));

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
        
        this.listAllEmprunt();
    }

    @FXML
    private void supprimerEmprunt(ActionEvent event) {
        // TODO Modifier l'état de l'exemplaire si besoin

        // MAJ emprunt dans la base
        try {
            Emprunt emprunt = cb_emprunt_todelete.getSelectionModel().getSelectedItem();
            emprunt.setEtat(EtatEmprunt.FINI.getValue());
            emprunt.update();
            lb_delete_message.setText("L'emprunt a bien été supprimé.");
        } catch (SQLException ex) {
            Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void majEmprunt(KeyEvent event) {
        String strFilter = tf_emprunt_todelete.getText();

        ArrayList<Emprunt> newList = filterEmprunt.getWithFilter(strFilter);

        cb_emprunt_todelete.setItems(FXCollections.observableArrayList(newList));
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
            this.listAllEmprunt();
            lb_add_message.setText("L'emprunt a bien été enregistré dans la base.");
        } catch (SQLException ex) {
            // Affichage d'un message d'erreur dans l'interface ? 
            Logger.getLogger(OeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listAllEmprunt() {
        try {
            this.emprunts = Emprunt.getAllEmprunts();
            cb_emprunt_todelete.setItems(FXCollections.observableArrayList(emprunts));

        } catch (SQLException ex) {
            Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
