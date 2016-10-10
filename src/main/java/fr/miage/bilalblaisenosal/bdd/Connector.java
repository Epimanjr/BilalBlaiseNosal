package fr.miage.bilalblaisenosal.bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Unique classe qui permet de gérer les connections à la base de données.
 *
 * @author Maxime BLAISE
 * @author Antoine NOSAL
 */
public class Connector {

    /**
     * Instance d'une connection. Initialement cette connection vaut "null",
     * C'est-à-dire qu'aucune connection n'a été initialisée.
     */
    private Connection connection;

    /**
     * Nom de la base.
     */
    private String baseName;

    /**
     * Pseudo utilisé pour se connecter.
     */
    private String login;

    /**
     * Mot de passe associé.
     */
    private String password;

    /**
     * Unique instance de connector.
     */
    private static final Connector CONNECTOR = new Connector();

    /**
     * Constructeur privé pour éviter la création de Connector en dehors de
     * cette classe.
     */
    private Connector() {
        super();
    }

    /**
     * Permet de récupérer l'unique instance de Connector en dehors de cette
     * classe.
     *
     * @return Connector
     */
    public static Connector getConnector() {
        return CONNECTOR;
    }

    /**
     * Permet de récupérer une connection à une base de données. Si la
     * connection a déjà été initialisée, alors on la récupère. Sinon, on créer
     * une nouvelle connection, et on récupère celle-ci.
     *
     * @return L'instance de la connection
     * @throws java.sql.SQLException If not connect.
     */
    public Connection getConnection() throws SQLException {
        if (this.connection == null) {
            // Initialisation d'une nouvelle connection
            initConnection();
        }
        return this.connection;
    }

    /**
     * Permet d'initialiser la connexion à la base de données.
     */
    private void initConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost/" + this.baseName + "?useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
            this.connection = DriverManager.getConnection(url, this.login, this.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'effectuer des requêtes de sélections à la base de données.
     *
     * @param sql Requête SQL
     * @return Résultats
     * @throws SQLException Exception si on arrive pas à effectuer la requête.
     */
    public static ResultSet select(String sql) throws SQLException {
        // Récupération de la connexion
        Connection connection = Connector.getConnector().getConnection();
        if (connection != null) {
            // Connexion OK
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            return results;
        }
        return null;
    }

    /**
     * Permet de renseigner les informations de connexion à la BDD.
     *
     * @param baseName Nom de la base.
     * @param login Pseudo utilisé pour se connecter.
     * @param password Mot de passe associé.
     */
    public void setConnectionInformation(String baseName, String login, String password) {
        this.baseName = baseName;
        this.login = login;
        this.password = password;
    }

    public void setConnectionInformation() {
        // TODO Recherche des informations dans le fichier .properties
        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("bdd/connexion.properties").getFile());

            System.out.println(file.getAbsolutePath());
            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

            setConnectionInformation(prop.getProperty("database"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Connector.getConnector().setConnectionInformation();
    }
}
