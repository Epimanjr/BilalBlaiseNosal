package fr.miage.bibalblaisenosal.helper;

import fr.miage.bilalblaisenosal.metier.Etat;
import fr.miage.bilalblaisenosal.metier.Exemplaire;
import fr.miage.bilalblaisenosal.metier.Oeuvre;
import fr.miage.bilalblaisenosal.metier.Usager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class DBHelper {
    
    public static void fillWithExamples() {
        try {
            // Ajout d'oeuvres
            Oeuvre o1 = new Oeuvre("XB2212", "Toto et ses potes", "Maxime");
            o1.insert();
            Oeuvre o2 = new Oeuvre("XT5262", "La mer est belle", "Antoine");
            o2.insert();
            Oeuvre o3 = new Oeuvre("PO6922", "Programmer en Java", "Romain");
            o3.insert();
            
            // Ajout d'usagers
            Usager u1 = new Usager("BLAISE", "Maxime", "maxime@mail.com", "0639526145");
            u1.insert();
            Usager u2 = new Usager("NOSAL", "Antoine", "antoine@mail.com", "0765325941");
            u2.insert();
            Usager u3 = new Usager("DENIS", "Guillaume", "denis@mail.com", "0614639852");
            u3.insert();
            
            // Ajout d'exemplaires
            Exemplaire e1 = new Exemplaire(Etat.ABIME.getValue(), "XB2212");
            e1.insert();
            Exemplaire e2 = new Exemplaire(Etat.ABIME.getValue(), "XB2212");
            e2.insert();
            Exemplaire e3 = new Exemplaire(Etat.BON.getValue(), "PO6922");
            e3.insert();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
