/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;
import java.io.*;

/**
 * Main Menu for Snake game
 * @author Matthew Below
 */
public class MainMenu extends VBox {
    
    private Label lblHighScore;
    private int[] highScore;
    private File data;
    private CheckBox diagonal;
    private CheckBox reverse;
    
    public void showHighScore(boolean a, boolean b)
    {
        lblHighScore.setText("High Score: " + highScore[(a?1:0)+(b?2:0)]);
    }
    
    public void setHighScore(boolean a, boolean b, int score)
    {
        highScore[(a?1:0)+(b?2:0)] = score;
    }
    
    public int getHighScore(boolean a, boolean b)
    {
        return highScore[(a?1:0)+(b?2:0)];
    }
    
    public void setHighScore(int[] highScore)
    {
        this.highScore = highScore;
    }
    
    public int[] getHighScore()
    {
        return highScore;
    }
    
    public void setHighScores() throws IOException
    {
        try (
            FileInputStream file = new FileInputStream("scores.dat");
            DataInputStream input = new DataInputStream(file);
        ) {
                    
            highScore[0]=(input.readInt());
            highScore[1]=(input.readInt());
            highScore[2]=(input.readInt());
            highScore[3]=(input.readInt());
        }
        
    }
    
    public void writeHighScores() throws IOException
    {
        try (
            FileOutputStream file = new FileOutputStream(data, false);
            DataOutputStream output = new DataOutputStream(file);
        ) {
            output.writeInt(highScore[0]);
            output.writeInt(highScore[1]);
            output.writeInt(highScore[2]);
            output.writeInt(highScore[3]);
        }
    }
    
    public MainMenu(Stage stage, boolean isDiagonal, boolean isReverse) throws IOException
    {
        
        highScore = new int[4];
        data = new File("scores.dat");
        
        lblHighScore = new Label("<insert high score here>");
        
        if(!data.exists())
        {
            try (
                FileOutputStream file = new FileOutputStream(data, false);
                DataOutputStream output = new DataOutputStream(file);
            ) {
                output.writeInt(1);
                output.writeInt(1);
                output.writeInt(1);
                output.writeInt(1);
            }
        }
        
        setHighScores();
        
        // logo
        Image image = new Image("logo.png");
        ImageView logo = new ImageView(image);
        
        // checkbox options
        HBox options = new HBox();
        diagonal = new CheckBox("Diagonal");
        reverse = new CheckBox("Reverse");
        

        
        diagonal.setSelected(isDiagonal);
        reverse.setSelected(isReverse);
        
        diagonal.setOnAction(e->showHighScore(diagonal.isSelected(), reverse.isSelected()));
        reverse.setOnAction(e->showHighScore(diagonal.isSelected(), reverse.isSelected()));
        showHighScore(isDiagonal, isReverse);
//        
//        diagonal.setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
//        
//        reverse.setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
        
        // add to options and set properties
        options.getChildren().addAll(diagonal, reverse);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(20);
        
        // high score label
        
        
        
        // start label
        Label start = new Label("PRESS ESCAPE BUTTON");
        start.setStyle("-fx-underline: true;");
        
        getChildren().addAll(logo, options, lblHighScore, start);
        setAlignment(Pos.CENTER);
        
        setSpacing(40);
        
        
        setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
        
        /*
        //GameField game = new GameField(false, false); 
        MainMenu menu = new MainMenu(stage);
        
        // 480, 360 -> 470, 350
        // 500, 380 -> 490, 370
        //Scene scene = new Scene(game, 490, 370);
        //scene.setOnKeyPressed(ke->game.handleKey(ke));
        Scene scene = new Scene(menu, 490, 370);
        
        stage.setScene(scene);
        //stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
        
        //game.play();
        */
    }
    
    public void startGame(KeyEvent ke, CheckBox diagonal, CheckBox reverse, Stage stage)
    {
        if(ke.getCode() == KeyCode.ESCAPE) {
            GameField game = new GameField(diagonal.isSelected(), reverse.isSelected(), stage, this);
            Scene scene = new Scene(game, 500, 380);
            scene.setOnKeyPressed(kke->game.handleKey(kke));
            stage.setScene(scene);
            game.play();
        }
    }
    /*
    public void startGame()
    {
        //GameField game = new GameField(false, false); 
        MainMenu menu = new MainMenu(stage);
        
        // 480, 360 -> 470, 350
        // 500, 380 -> 490, 370
        //Scene scene = new Scene(game, 490, 370);
        //scene.setOnKeyPressed(ke->game.handleKey(ke));
        Scene scene = new Scene(menu, 490, 370);
        
        stage.setScene(scene);
        //stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
        
        //game.play();
    }
*/
    
    class Logo extends StackPane
    {
        
        public Logo()
        {
            Image image = new Image("logo.png");
            ImageView imageView = new ImageView(image);
            getChildren().add(imageView);
            
            //setStyle("-fx-background-color: red;");
        }
        
    }
    

    
    class Options extends HBox {
        
        public Options()
        {
            CheckBox diagonal = new CheckBox("Diagonal");
            CheckBox reverse = new CheckBox("Reverse");
            
            getChildren().addAll(diagonal, reverse);
            
            setAlignment(Pos.CENTER);
            //setPadding(new Insets(40));
            setSpacing(20);
        }
        
    }
    
}
