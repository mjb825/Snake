    
    
    import javafx.application.Application;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.geometry.Insets;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.GridPane;
    import javafx.stage.Stage;
    
    import javafx.scene.layout.Pane;
    
    import javafx.animation.Timeline;
    import javafx.animation.KeyFrame;
    import javafx.util.Duration;
    import javafx.animation.Animation;
    import javafx.scene.input.KeyCode;
    import javafx.scene.input.KeyEvent;
    
    public class GameField extends Pane
    {    
    private Snake player; // will be class Snake in future
    private Timeline frameTimer;
    
    public GameField()
    {
        player = new Snake(20, 15);
        player.add(20, 16);
        player.add(20, 17);
        player.add(20, 18);
        
        
        for(int i = 0; i < player.getTail().size(); i++)
            getChildren().add(player.getTail().get(i));
        
        frameTimer = new Timeline(new KeyFrame(Duration.seconds(1.0/8.0),
            e->updateFrame()));
            
        frameTimer.setCycleCount(Animation.INDEFINITE);
    }
    
    public void updateFrame()
    {
        player.updateFrame(40, 30);
        //System.out.println("x: " + player.getX() + " y: " + player.getY());
    }
    
    public void handleKey(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.UP)
            player.changeDirection(Direction.UP);
        else if(ke.getCode() == KeyCode.DOWN)
            player.changeDirection(Direction.DOWN);
        else if(ke.getCode() == KeyCode.LEFT)
            player.changeDirection(Direction.LEFT);
        else if(ke.getCode() == KeyCode.RIGHT)
            player.changeDirection(Direction.RIGHT);
    }
    
    public void play()
    {
        frameTimer.play();
    }
}
