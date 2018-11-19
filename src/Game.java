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
    @Override
    public void start(Stage stage) throws Exception
    {
        GameField game = new GameField();
        
        Scene scene = new Scene(game, 470, 350);
        scene.setOnKeyPressed(ke->game.handleKey(ke));
        
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
        
        game.play();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
