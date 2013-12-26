package ch.tuason.djbattlescore;

import ch.tuason.djbattlescore.lib.MainController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    private final String cTITLE = "...::: DJ Battle Scoreboard - Kurhaus Lenzerheide :::...";
    
    //private final String cCSS_FILE_RESOURCE = "/styles/Styles.css";
    //private final String cFXML_FILE_RESOURCE = "/fxml/Scene.fxml";
  
    private MainController mMainController;
    

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource(cFXML_FILE_RESOURCE));
        //Scene scene = new Scene(root);
        //scene.getStylesheets().add(cCSS_FILE_RESOURCE);
        
        StackPane root = new StackPane();
        //root.getChildren().add(getController().getComponentHandler().getScoreBoardChart());
        // root.getChildren().add(getController().getComponentHandler().getFooter());
        // root.getChildren().add(btn);
        root.getChildren().add(getController().getComponentHandler().getMainLayout());
        
        Scene scene = new Scene(root, 1024, 800);
        
        stage.setTitle(cTITLE);
        stage.setScene(scene);
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

    
    /**
     * this method returns the main application controller...
     * 
     * @return an instance of MainController
     */
    public MainController getController() {
        if (mMainController == null) {
            mMainController = new MainController(this);
        }
        return mMainController;
    }
}
