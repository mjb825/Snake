    
    
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
    import java.util.ArrayList;
    
    public class GameField extends Pane
    {    
    private Snake player; // will be class Snake in future
    private Timeline frameTimer;
    
    public GameField()
    {
        player = new Snake(20, 15);
        /*
        player.add(20, 16);
        player.add(20, 17);
        player.add(20, 18);
        player.add(20, 19);
        player.add(20, 20);
        player.add(20, 21);
        player.add(20, 22);
        player.add(20, 23);
        player.add(20, 24);
        player.add(20, 25);
        player.add(20, 26);
        player.add(20, 27);
        player.add(20, 28);
        player.add(20, 29);
        player.add(20, 30);
        player.add(20, 31);
        player.add(20, 32);
        player.add(20, 33);
*/
        
        
        
        for(int i = 0; i < player.getTail().size(); i++)
            getChildren().add(player.getTail().get(i));
        
        
        //addPiece();
        
        
        //getChildren().add(player.add());

        
        
        frameTimer = new Timeline(new KeyFrame(Duration.seconds(1.0/8.0),
            e->updateFrame()));
            
        frameTimer.setCycleCount(Animation.INDEFINITE);
    }
    
    public void addPiece()
    {
        ArrayList<Tail> tail = player.getTail();
        Tail lastPiece = tail.get(tail.size() - 1).copy();
        Move move = lastPiece.getCurrentMove();
        Direction direction = move.getDirection();
        
        if(direction == Direction.UP)
            player.add(move.getX(), move.getY() + 1);
            //move.setY(move.getY() + 0); 
        else if(direction == Direction.DOWN)
            player.add(move.getX(), move.getY() - 1);
            //move.setY(move.getY() - 0);
        else if(direction == Direction.LEFT)
            player.add(move.getX() + 1, move.getY());
            //move.setX(move.getX() + 0);
        else if(direction == Direction.RIGHT)
            player.add(move.getX() - 1, move.getY());
            //move.setX(move.getX() - 0);
        
        
        getChildren().add(tail.get(tail.size() - 1));
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
        else if(ke.getCode() == KeyCode.SPACE)
            addPiece();
    }
    
    public void play()
    {
        frameTimer.play();
    }
}
