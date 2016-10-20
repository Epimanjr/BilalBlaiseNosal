
package fr.miage.bilalblaisenosal.metier;

/**
 * Représente les différents états possibles pour un exemplaire.
 * 
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public enum EtatEmprunt {
    ENCOURS("en cours"),
    FINI("fini");
    
    private final String etat;
    
    EtatEmprunt(String etat) {
        this.etat = etat;
    }
    
    public String getValue() {
        return etat;
    }
}
