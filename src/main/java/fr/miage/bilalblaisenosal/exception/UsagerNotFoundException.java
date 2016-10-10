
package fr.miage.bilalblaisenosal.exception;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class UsagerNotFoundException extends Exception {

    public UsagerNotFoundException() {
        super();
    }

    public UsagerNotFoundException(String message) {
        System.out.println(message);
    }
    
}
