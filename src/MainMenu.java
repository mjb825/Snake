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
    
    public void setHighScores()
    {
        try {
        try (
            FileInputStream file = new FileInputStream("scores.dat");
            DataInputStream input = new DataInputStream(file);
        ) {
                    
            highScore[0]=(input.readInt());
            highScore[1]=(input.readInt());
            highScore[2]=(input.readInt());
            highScore[3]=(input.readInt());
        }
        } catch (IOException e) {}
    }
    
    public void writeHighScores()
    {
        try {
        try (
            FileOutputStream file = new FileOutputStream(data, false);
            DataOutputStream output = new DataOutputStream(file);
        ) {
            output.writeInt(highScore[0]);
            output.writeInt(highScore[1]);
            output.writeInt(highScore[2]);
            output.writeInt(highScore[3]);
        }
        } catch(IOException e) {}
    }
    
    public MainMenu(){}
    
    public MainMenu(Stage stage, boolean isDiagonal, boolean isReverse)
    {
        // file for storing high scores
        data = new File("scores.dat");
        
        // if highscore file doesn't exist, make a new one with 1 as high score for all categories
        try {
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
        } catch (IOException e) {}
        
        // update array for storing high scores from file
        highScore = new int[4];
        setHighScores();
        
        // logo
        Image image = new Image("logo.png");
        ImageView logo = new ImageView(image);
        
        // high score label
        lblHighScore = new Label();
        showHighScore(isDiagonal, isReverse);
        
        // checkbox options
        HBox options = new HBox();
        diagonal = new CheckBox("Diagonal");
        reverse = new CheckBox("Reverse");
        // set checkbox options according to parameters
        diagonal.setSelected(isDiagonal);
        reverse.setSelected(isReverse);
        // update high score label when checkboxes are changed
        diagonal.setOnAction(e->showHighScore(diagonal.isSelected(), reverse.isSelected()));
        reverse.setOnAction(e->showHighScore(diagonal.isSelected(), reverse.isSelected()));
        // add to options and set properties
        options.getChildren().addAll(diagonal, reverse);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(20);
        
        // start label
        Label start = new Label("PRESS ESCAPE BUTTON");
        start.setStyle("-fx-underline: true;");
        
        // add elements to vbox and set properties
        getChildren().addAll(logo, options, lblHighScore, start);
        setAlignment(Pos.CENTER);
        setSpacing(40);
        
        // set key press event so user can start game by pressing escape button
        setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
    }
    
    // start game when user presses escape button
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
    
}
