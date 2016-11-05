package fr.miage.bilalblaisenosal.ui;

import fr.miage.bilalblaisenosal.bdd.DBHelper;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DBHelper.fillWithExamples();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("BibalBlaiseNosal");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
