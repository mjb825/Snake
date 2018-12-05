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
    private String[] highScoreUser;
    private File data;
    private CheckBox diagonal;
    private CheckBox reverse;
    private Game gameApp;
    Settings settings;

    
    public MainMenu(){}
    
    public MainMenu(Game gameApp, Stage stage, boolean isDiagonal, boolean isReverse)
    {
        this.gameApp = gameApp;
        settings = new Settings(stage, this, gameApp);
        
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
                    output.writeUTF("???");
                    output.writeInt(1);
                    output.writeUTF("???");
                    output.writeInt(1);
                    output.writeUTF("???");
                    output.writeInt(1);
                    output.writeUTF("???");
                }
            }
        } catch (IOException e) {}
        
        // update array for storing high scores from file
        highScore = new int[4];
        highScoreUser = new String[4];
        setHighScores();
        
        // logo
        Image image = new Image("logo.png");
        ImageView logo = new ImageView(image);
        
        // high score label
        lblHighScore = new Label();
        lblHighScore.setStyle("-fx-font: bold 18 monospace;");
        showRecord(isDiagonal, isReverse);
        
        // checkbox options
        HBox options = new HBox();
        diagonal = new CheckBox("Diagonal");
        reverse = new CheckBox("Reverse");
        // set checkbox options according to parameters
        diagonal.setSelected(isDiagonal);
        reverse.setSelected(isReverse);
        // update high score label when checkboxes are changed
        diagonal.setOnAction(e->showRecord(diagonal.isSelected(), reverse.isSelected()));
        reverse.setOnAction(e->showRecord(diagonal.isSelected(), reverse.isSelected()));
        // add to options and set properties
        options.getChildren().addAll(diagonal, reverse);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(20);
        
        // start label
        Label start = new Label("PRESS ESCAPE BUTTON");
        start.setStyle("-fx-underline: true; -fx-font-size: 14;");
        
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
            GameField game = new GameField(diagonal.isSelected(), reverse.isSelected(), stage, this, gameApp);
            gameApp.getScene().setRoot(game);
            gameApp.getScene().setOnKeyPressed(kke->game.handleKey(kke));
            stage.setScene(gameApp.getScene());
            game.play();
        }
        else if(ke.getCode() == KeyCode.SHIFT) {
            settings.updatePreviews();
            gameApp.getScene().setRoot(settings);
            gameApp.getScene().setOnKeyPressed(kke->settings.handleKey(kke));
            stage.setScene(gameApp.getScene());
        }
    }

    public Settings getSettings()
    {
        return settings;
    }
    
    /***************
     * HIGH SCORES *
     ***************/

    public void showRecord(boolean diagonal, boolean reverse)
    {
        lblHighScore.setText("High Score: " + highScore[(diagonal?1:0)+(reverse?2:0)] + " (" + highScoreUser[(diagonal?1:0)+(reverse?2:0)] + ")");
    }
    
    public void setRecord(boolean diagonal, boolean reverse, int score, String name)
    {
        highScore[(diagonal?1:0)+(reverse?2:0)] = score;
        highScoreUser[(diagonal?1:0)+(reverse?2:0)] = name;
    }
    
    public int getRecord(boolean diagonal, boolean reverse)
    {
        return highScore[(diagonal?1:0)+(reverse?2:0)];
    }
    
    public void setHighScore(int[] highScore)
    {
        this.highScore = highScore;
    }
    
    public int[] getHighScore()
    {
        return highScore;
    }
    
    public void setHighScoreUser(String[] highScoreUser)
    {
        this.highScoreUser = highScoreUser;
    }
    
    public String[] getHighScoreUser()
    {
        return highScoreUser;
    }
    
    public void setHighScores()
    {
        try {
            try (
                FileInputStream file = new FileInputStream("scores.dat");
                DataInputStream input = new DataInputStream(file);
            ) {
                highScore[0]=(input.readInt());
                highScoreUser[0]=(input.readUTF());
                highScore[1]=(input.readInt());
                highScoreUser[1]=(input.readUTF());
                highScore[2]=(input.readInt());
                highScoreUser[2]=(input.readUTF());
                highScore[3]=(input.readInt());
                highScoreUser[3]=(input.readUTF());
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
                output.writeUTF(highScoreUser[0]);
                output.writeInt(highScore[1]);
                output.writeUTF(highScoreUser[1]);
                output.writeInt(highScore[2]);
                output.writeUTF(highScoreUser[2]);
                output.writeInt(highScore[3]);
                output.writeUTF(highScoreUser[3]);
            }
        } catch(IOException e) {}
    }    
}
