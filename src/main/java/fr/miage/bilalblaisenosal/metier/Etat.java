
package fr.miage.bilalblaisenosal.metier;

/**
 * Représente les différents états possibles pour un exemplaire.
 * 
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public enum Etat {
    BON("Bon état"),
    ABIME("Abimé");
    
    private final String etat;
    
    Etat(String etat) {
        this.etat = etat;
    }
    
    public String getValue() {
        return etat;
    }
}
