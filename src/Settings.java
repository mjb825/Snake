/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Adjust size and color of Snake
 * @author Owner
 */
public class Settings extends Pane {
    
    int r;
    int g;
    int b;
    double a;
    ArrayList<Color> snakeColors;
    ArrayList<Color> tailColors;
    ArrayList<Color> foodColors;

    public Settings() {
    
        snakeColors = new ArrayList<Color>();
        snakeColors.add(Color.RED);
        
        r = 255;
        g = 0;
        b = 0;
        a = 1;
        
        /****************
         * COLOR SELECT *
         ****************/
        GridPane colorSelect = new GridPane();
        // color select sliders
        Slider rSlider = new Slider();
        Slider gSlider = new Slider();
        Slider bSlider = new Slider();
        Slider aSlider = new Slider();
        // color select labels
        Label rLabel = new Label("R: 255");
        Label gLabel = new Label("G: 0");
        Label bLabel = new Label("B: 0");
        Label aLabel = new Label("A: 100%");
        // add sliders and labels
        colorSelect.add(rSlider, 0, 0);
        colorSelect.add(rLabel, 1, 0);
        colorSelect.add(gSlider, 0, 1);
        colorSelect.add(gLabel, 1, 1);
        colorSelect.add(bSlider, 0, 2);
        colorSelect.add(bLabel, 1, 2);
        colorSelect.add(aSlider, 0, 3);
        colorSelect.add(aLabel, 1, 3);
        
        /*********
         * SNAKE *
         *********/
        Circle snake[] = new Circle[19];
        for(int i = 0; i < snake.length; i++) {
            snake[i] = new Circle(6);
            snake[i].setStyle("-fx-stroke: black; -fx-fill: rgb(255, 0, 0, 1); -fx-stroke-width: 2;");
            snake[i].setCenterX(490);
            snake[i].setCenterY(i * 20 + 10);
        }
//        Circle snake = new Circle(6);
//        snake.setStyle("-fx-stroke: black; -fx-fill: rgb(255, 0, 0, 1); -fx-stroke-width: 2;");
//        
//        snake.setCenterX(250);
//        snake.setCenterY(250);
        

        
        rSlider.valueProperty().addListener(l -> {
            r = ((int)(rSlider.getValue() / 100 * 255));
            rLabel.setText("R: " + r);
            snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        gSlider.valueProperty().addListener(l -> {
            g = ((int)(gSlider.getValue() / 100 * 255));
            gLabel.setText("G: " + g);
            snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        bSlider.valueProperty().addListener(l -> {
            b = ((int)(bSlider.getValue() / 100 * 255));
            bLabel.setText("B: " + b);
            snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        aSlider.valueProperty().addListener(l -> {
            a = (aSlider.getValue() / 100);
            aLabel.setText("A: " + (int)(a * 100) + "%");
            snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        rSlider.setValue(100);
        gSlider.setValue(0);
        bSlider.setValue(0);
        aSlider.setValue(100);
        
        getChildren().addAll(colorSelect);
        
        for(int i = 0; i < snake.length; i++) {
            getChildren().add(snake[i]);
        }
        
    }

    
}
