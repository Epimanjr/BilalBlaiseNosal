package fr.miage.bilalblaisenosal.sequences;

import fr.miage.bilalblaisenosal.common.metier.Usager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime BLAISE
 */
public class SeqUsager {
    
    private static final ArrayList<String> FIELDS = new ArrayList<>(Arrays.asList("nom", "prenom", "email", "telephone"));
    
    /**
     * Méthode pour ajouter un Usager de manière interactive. 
     */
    public static void ajouterUsager() {
        // Etape 1 : Demande des informations
        Usager usager = demanderInformation();
        try {
            // Etape 2 : Sauvegarde dans la base
            usager.insert();
        } catch (SQLException ex) {
            Logger.getLogger(SeqUsager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Méthode qui demande les informations concernant l'Usager à ajouter. 
     * @return instance d'Usager
     */
    public static Usager demanderInformation() {
        HashMap<String, String> askedFields = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        for(String currentField: FIELDS) {
            System.out.print("'"+currentField+"' : ");
            String current = sc.nextLine();
            askedFields.put(currentField, current);
        }
        
        return new Usager(askedFields);
    }
    
    public static void main(String[] args) {
        ajouterUsager();
    }
}
