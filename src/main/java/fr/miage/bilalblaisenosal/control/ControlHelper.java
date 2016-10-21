package fr.miage.bilalblaisenosal.control;

/**
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class ControlHelper {

    private static final int STRING_SIZE_LIMIT = 20;

    /**
     * Vérifie un attribut classique de type String (nom, prénom, etc.)
     *
     * @param value Chaîne de caractères à tester
     * @return Vrai si la chaîne est valide
     */
    public static boolean checkStringField(String value) {
        return checkStringSize(value)
                && checkStringSyntax(value);
    }

    /**
     * Vérifie si la chaîne de caractères ne dépasse pas la taille limite.
     *
     * @param value Chaîne de caractères à tester
     * @return Vrai si la chaîne est valide
     */
    public static boolean checkStringSize(String value) {
        return value.length() <= STRING_SIZE_LIMIT;
    }

    /**
     * Vérifie si la syntaxe de la chaîne de caractères.
     *
     * @param value Chaîne de caractères à tester.
     * @return Vrai si la chaîne est valide.
     */
    public static boolean checkStringSyntax(String value) {
        // TODO Vérifier la syntaxe de la chaîne

        return false;
    }
}
