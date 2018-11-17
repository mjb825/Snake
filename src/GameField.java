

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
        player = new Snake(0, 6);

        food = new Food();
        generateFood();
        getChildren().add(food);

        setBackground(new Background(new BackgroundFill(new Color(.95,.95,.95,1), new CornerRadii(0), new Insets(0))));
        
        // add player to field
        for(int i = 0; i < player.getTail().size(); i++)
            getChildren().add(player.getTail().get(i));
        
        frameTimer = new Timeline(new KeyFrame(Duration.seconds(1.0/6.0),
            e->updateFrame()));
        frameTimer.setCycleCount(Animation.INDEFINITE);
    }

    public void addPiece()
    {

        ArrayList<Tail> tail = player.getTail();
        //[add] getLastPiece and getFirstPiece methods to Snake class
        //[remove] what is lastPiece even being used for????
        Tail lastPiece = tail.get(tail.size() - 1).copy();
        //[change] is move even that necessary???
        Move move = new Move(lastPiece.x, lastPiece.y, lastPiece.direction);
        Direction direction = move.getDirection();
        // THIS DOESN'T WORK IF HEAD PIECE IS IN A DIFFERENT DIRECTION THAN THE TAIL
        // PIECE NEEDS TO DUPLICATE THE LAST PIECE'S QUEUE
        // PASS ONLY DIRECTION TO CONSTRUCTOR, maybe not even that, just have constructor do work
        if(direction == Direction.UP)
            player.add(move.getX(), move.getY() + 1, direction);
            //move.setY(move.getY() + 0); 
        else if(direction == Direction.DOWN)
            player.add(move.getX(), move.getY() - 1, direction);
            //move.setY(move.getY() - 0);
        else if(direction == Direction.LEFT)
            player.add(move.getX() + 1, move.getY(), direction);
            //move.setX(move.getX() + 0);
        else if(direction == Direction.RIGHT)
            player.add(move.getX() - 1, move.getY(), direction);
            //move.setX(move.getX() - 0);


        getChildren().add(tail.get(tail.size() - 1));


        /*
        player.add();
        ArrayList<Tail> tail = player.getTail();
        Tail lastPiece = tail.get(tail.size() - 1);
        getChildren().add(lastPiece);
        */
    }

    public void generateFood()
    {
        boolean found = true;
        // coordinates for food (upper limit = 39, 29) (n * 20 + 10) (800x600)
        int x = 40;
        int y = 30;
        
        int used = 1;
        
        // food generation restrictions based off of snake pieces coordinates
        ArrayList<Tail> tail = player.getTail();

            
        while(found)
        {
            used++;
            
            if(used >= (2*6)) {
                found = false;
                System.out.println("YOU WON!!!!");
                break;
            }
            
            x = (int)(Math.random() * 6);
            y = (int)(Math.random() * 2);
            
            // don't think it's possible, but just in case
            if(x >= 40 || y >= 30) {
                used--;
                break;
            }
            
            for (Tail piece : tail) {
                
                if(x == piece.x && y == piece.y) {
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

        System.out.println("food:\nx: " + x + " y: " + y);
    }

    public void updateFrame()
    {
        //[add] logic for running into edges, running into self
        //[add] logic when the game is over
        Tail head = player.getTail().get(0);
        Move move = new Move(head.x, head.y, head.direction);
        Direction direction = move.getDirection();
        //if(move.getX() == food.getX() && move.getY() == food.getY()) {
            //addPiece();
          //  generateFood();
        //}
        if(head.x == food.getX() && head.y == food.getY()) {
            //addPiece();
            //for(int i = 0; i < 2000; i++)
                addPiece();
            //[add] if player.getLength() == (width / 20) * (height / 20) YOU WIN! EXIT SOMEHOW   
            generateFood();
        }
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
