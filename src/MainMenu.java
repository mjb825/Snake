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


/**
 * Main Menu for Snake game
 * @author Matthew Below
 */
public class MainMenu extends VBox {
    
    public MainMenu()
    {
        Image image = new Image("logo.png");
        ImageView imageView = new ImageView(image);
        
        Label start = new Label("PRESS ENTER BUTTON");
        //start.setStyle("-fx-underline: true;");
        
        getChildren().add(imageView);
        getChildren().add(new Options());
        getChildren().add(start);
        setAlignment(Pos.CENTER);
        
        setSpacing(40);
    }
    

    
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
