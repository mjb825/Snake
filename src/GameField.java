

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

import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class GameField extends Pane
{    
    private Snake player;
    private Timeline frameTimer;
    private Food food;

    public GameField()
    {
        // start player in center of field
        player = new Snake(12, 9);

        food = new Food();
        generateFood();
        getChildren().add(food);

        setBackground(new Background(new BackgroundFill(new Color(.95,.95,.95,1), new CornerRadii(0), new Insets(0))));
        
        // add player to field
        getChildren().add(player.getFirst());
        
        frameTimer = new Timeline(new KeyFrame(Duration.seconds(1.0/8.0),
            e->updateFrame()));
        frameTimer.setCycleCount(Animation.INDEFINITE);
    }

    public void addPiece()
    {
        // create a copy of last piece
        Tail piece = player.getLast().copy();
        
        // set new piece position according to direction copied from last piece
        if(piece.getDirection() == Direction.N)
            piece.setY(piece.getY() + 1);
        else if(piece.getDirection() == Direction.S)
            piece.setY(piece.getY() - 1);
        else if(piece.getDirection() == Direction.E)
            piece.setX(piece.getX() + 1);
        else if(piece.getDirection() == Direction.W)
            piece.setX(piece.getX() - 1);
        
        // add piece to player
        player.add(piece);
        
        // add piece to game field
        getChildren().add(piece);
    }

    public void generateFood()
    {
        boolean found = true;
        // coordinates for food (upper limit = 23, 17) (n * 20 + 10) (480x360)
        int x = 24;
        int y = 18;
        
        int tried = 1;
        
        // food generation restrictions based off of snake pieces coordinates
        ArrayList<Tail> tail = player.getTail();

            
        while(found)
        {
            // keep track of how many spaces the food has tried generating in
            tried++;
            
            //if(tried >= (24 * 18)) {
            //    System.out.println("YOU WON!!!!");
            //    break;
            //}
            
            // generate random coordinates for food
            x = (int)(Math.random() * 24);
            y = (int)(Math.random() * 18);
            
            // don't think it's possible, but just in case
            if(x >= 24 || y >= 18) {
                tried--;
                break;
            }
            
            // prevent generating food in space occupied by snake
            for (Tail piece : tail) {
                
                if(x == piece.getX() && y == piece.getY()) {
                    found = true;
                    break;
                } else {
                    found = false;
                }
                
            }
        }
        
        food.setX(x);
        food.setY(y);
        
        food.setCenterX(x * 20 + 10);
        food.setCenterY(y * 20 + 10);

    }

    public void updateFrame()
    {
        // get first piece from snake
        Tail head = player.getFirst();
        
        // get tail from snake
        ArrayList<Tail> tail = player.getTail();
        
        // pause game when running into edges, running into self
        if(head.getX() < 0 || head.getX() >= 24 || head.getY() < 0 || head.getY() >= 18) {
            pause();
            return;
        }
        
        // pause game when first piece runs into tail of snake
        for(int i = 1; i < tail.size(); i++) {
            
            if(head.getX() == tail.get(i).getX() && head.getY() == tail.get(i).getY()) {
                pause();
                return;
            }
            
        }
        
        
        //[add] logic when the game is over
        if(head.getX() == food.getX() && head.getY() == food.getY()) {
            addPiece();
            //[add] if player.getLength() == (width / 20) * (height / 20) YOU WIN! EXIT SOMEHOW   
            generateFood();
        }
        player.updateFrame();
    }

    public void handleKey(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.UP)
            player.changeDirection(Direction.N);
        else if(ke.getCode() == KeyCode.DOWN)
            player.changeDirection(Direction.S);
        else if(ke.getCode() == KeyCode.LEFT)
            player.changeDirection(Direction.E);
        else if(ke.getCode() == KeyCode.RIGHT)
            player.changeDirection(Direction.W);
        else if(ke.getCode() == KeyCode.SPACE)// {
            addPiece();//player.updateFrame();}
    }

    public void play()
    {
        frameTimer.play();
    }
    
    public void pause()
    {
        frameTimer.pause();
    }
}
