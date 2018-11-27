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


/**
 * Main Menu for Snake game
 * @author Matthew Below
 */
public class MainMenu extends VBox {
    
    public MainMenu(Stage stage, boolean isDiagonal, boolean isReverse)
    {
        // logo
        Image image = new Image("logo.png");
        ImageView logo = new ImageView(image);
        
        // checkbox options
        HBox options = new HBox();
        CheckBox diagonal = new CheckBox("Diagonal");
        CheckBox reverse = new CheckBox("Reverse");
        diagonal.setSelected(isDiagonal);
        reverse.setSelected(isReverse);
//        
//        diagonal.setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
//        
//        reverse.setOnKeyPressed(ke-> startGame(ke, diagonal, reverse, stage));
        
        // add to options and set properties
        options.getChildren().addAll(diagonal, reverse);
        options.setAlignment(Pos.CENTER);
        options.setSpacing(20);
        
        // start label
        Label start = new Label("PRESS ESCAPE BUTTON");
        start.setStyle("-fx-underline: true;");
        
        getChildren().add(logo);
        getChildren().add(options);
        getChildren().add(start);
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
            GameField game = new GameField(diagonal.isSelected(), reverse.isSelected(), stage);
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
