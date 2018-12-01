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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
        GridPane options = new GridPane();
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
        options.add(rSlider, 0, 0);
        options.add(rLabel, 2, 0);
        options.add(gSlider, 0, 1);
        options.add(gLabel, 2, 1);
        options.add(bSlider, 0, 2);
        options.add(bLabel, 2, 2);
        options.add(aSlider, 0, 3);
        options.add(aLabel, 2, 3);
        
        options.add(new BlankSpace(80, 10), 1, 0);
        options.add(new BlankSpace(20, 200), 0, 4);
        
        // text field for custom sizes
        TextField size = new TextField();
        options.add(size, 0, 5);
        
        // simple label to test size textfield
        Label preview = new Label("6");
        options.add(preview, 0, 6);
        
        size.textProperty().addListener(l -> {
            if(size.getText().matches("[0-9]+")) {
                
                preview.setText(size.getText());
            }
            else
                preview.setText("[Numbers Only]");
        });
        
        // add previews for HEAD, TAIL, FOOD
        SettingsPreview head = new SettingsPreview("HEAD");
        SettingsPreview tail = new SettingsPreview("TAIL");
        SettingsPreview food = new SettingsPreview("FOOD");
        // add previews to options
        options.add(head, 3, 0, 1, 30);
        options.add(tail, 4, 0, 1, 30);
        options.add(food, 5, 0, 1, 30);
        
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
        
        getChildren().addAll(options);
        
        for(int i = 0; i < snake.length; i++) {
            getChildren().add(snake[i]);
        }
        
    }

    private class SettingsPreview extends GridPane
    {
        Label title;
        Rectangle[] colors;
        Label[] sizes;
        
        public SettingsPreview(){}
        
        public SettingsPreview(String title)
        {
            this.title = new Label(title);
            this.title.setStyle("-fx-font: 12 monospace;");
            
            add(this.title, 1, 0);
            
            for(int i = 1; i < 12; i++) {
                
                Rectangle color = new Rectangle(30, 15);
                Label size = new Label("6");
                size.setStyle("-fx-font: 12 monospace;");
                
                color.setStyle("-fx-fill: none; -fx-stroke: black; -fx-stroke-width: 1;");
                
                add(color, 1, i);
                add(size, 0, i);
                
            }
            
            setVgap(-1);
        }
        
    }
    
    private class BlankSpace extends Rectangle
    {
        
        public BlankSpace(){}
        
        public BlankSpace(double width, double height)
        {
            super(width, height);
            setFill(Color.rgb(0, 255, 0, 0.1));
        }
        
    }
    
}
