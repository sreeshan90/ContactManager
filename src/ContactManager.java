/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sreesha.n
 */
public class ContactManager extends Application {

    /*The main entry point of the JavaFX application. It is called after the init method has returned,
     and after the system is ready for the application to begin running.*/
    @Override
    public void start(Stage stage) throws Exception {
        // load the resource from the XML file and assign to the root whihc is the parent
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //create a new scene with the root 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
