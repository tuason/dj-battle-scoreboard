package ch.tuason.djbattlescore;

import ch.tuason.djbattlescore.lib.DjBattleConstants;
import ch.tuason.djbattlescore.lib.MainController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {
  
    private MainController mMainController;
    

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.getChildren().add(getController().getComponentHandler().getMainLayout());
        
        Scene scene = new Scene(root, DjBattleConstants.APP_MIN_SCENE_WIDTH, 
                DjBattleConstants.APP_MIN_SCENE_HEIGHT);
        
        stage.setMinWidth(DjBattleConstants.APP_MIN_SCENE_WIDTH);
        stage.setMinHeight(DjBattleConstants.APP_MIN_SCENE_HEIGHT);
        
        // make it possible to impoart data with "shift control enter" keys pressed...
        stage.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER && event.isShiftDown() && event.isControlDown()) {
                    getController().getComponentHandler().showCSVImportDialog();
                }
            }
        });
        
        stage.setTitle(DjBattleConstants.APPLICATION_TITLE);
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
