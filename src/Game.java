import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Application class for the Snake game
 * @author Matthew Below
 */
public class Game extends Application
{    
    
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        // main menu of game
        MainMenu menu = new MainMenu(this, stage, false, false);
        
        scene = new Scene(menu, 490, 370);
        
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public Scene getScene()
    {
        return scene;
    }
    
}
