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
        GameField game = new GameField(false, false); 
        MainMenu menu = new MainMenu();
        
        // 480, 360 -> 470, 350
        // 500, 380 -> 490, 370
        //Scene scene = new Scene(game, 490, 370);
        //scene.setOnKeyPressed(ke->game.handleKey(ke));
        Scene scene = new Scene(menu, 490, 370);
        
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
