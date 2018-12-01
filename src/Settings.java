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
        
        //options.add(new BlankSpace(80, 10), 1, 0);
        options.add(new BlankSpace(20, 200), 0, 4);
        
        // text field for custom sizes
        TextField size = new TextField();
        options.add(size, 0, 5);
        
        // simple label to test size textfield
        Label preview = new Label("Size [ 1, 99.99 ]");
        preview.setStyle("-fx-font: 12 monospace;");
        options.add(preview, 0, 6);
        
        
        // add previews for HEAD, TAIL, FOOD
        SettingsPreview head = new SettingsPreview("HEAD", Color.RED, 6.0);
        SettingsPreview tail = new SettingsPreview("TAIL", Color.BLUE, 6.0);
        SettingsPreview food = new SettingsPreview("FOOD", Color.ORANGE, 6.0);
        // add previews to options
        options.add(head, 3, 0, 1, 30);
        options.add(tail, 4, 0, 1, 30);
        options.add(food, 5, 0, 1, 30);
        

        size.textProperty().addListener(l -> {
            // only allow values [1, 99.99]
            if(size.getText().matches("(([1-9][0-9])|[1-9])([.][0-9]{0,2})?"))
                head.previewSize(size.getText());
            // trim textfield if doesn't result in a legit match
            else if(size.getText().matches(".+"))
                size.setText(size.getText().substring(0, size.getText().length() - 1));
        });

        
        rSlider.valueProperty().addListener(l -> {
            r = ((int)(rSlider.getValue() / 100 * 255));
            rLabel.setText("R: " + r);
            head.previewColor(Color.rgb(r, g, b, a));
            //snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        gSlider.valueProperty().addListener(l -> {
            g = ((int)(gSlider.getValue() / 100 * 255));
            gLabel.setText("G: " + g);
            head.previewColor(Color.rgb(r, g, b, a));
            //snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        bSlider.valueProperty().addListener(l -> {
            b = ((int)(bSlider.getValue() / 100 * 255));
            bLabel.setText("B: " + b);
            head.previewColor(Color.rgb(r, g, b, a));
            //snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        aSlider.valueProperty().addListener(l -> {
            a = (aSlider.getValue() / 100);
            aLabel.setText("A: " + (int)(a * 100) + "%");
            head.previewColor(Color.rgb(r, g, b, a));
            //snake[0].setFill(Color.rgb(r, g, b, a));
        });
        
        rSlider.setValue(100);
        gSlider.setValue(0);
        bSlider.setValue(0);
        aSlider.setValue(100);
        
        getChildren().addAll(options);
        
        // set and preview default colors 
        //head.previewColor(Color.RED);
        //tail.previewColor(Color.BLUE);
        //food.previewColor(Color.ORANGE);
        
    }

    private class SettingsPreview extends GridPane
    {
        private Label title;
        private Rectangle[] colorPreview;
        private Label[] sizePreview;
        private int colorPosition;
        private int sizePosition;
        private int amount; // amount of previews (color and size)
        
        private ArrayList<Color> colors;
        private ArrayList<Double> sizes;
        
        public SettingsPreview(){}
        
        public SettingsPreview(String title, Color defaultColor, Double defaultSize)
        {
            // set title for previews
            this.title = new Label(title);
            this.title.setStyle("-fx-font: 12 monospace;");
            add(this.title, 1, 0);
            
            // set amount of previews (49 if you want two columns)
            amount = 24;
            
            // initialize colors and sizes arraylists
            colors = new ArrayList<Color>();
            sizes = new ArrayList<Double>();
            
            colorPreview = new Rectangle[amount];
            sizePreview = new Label[amount];
            
            for(int i = 0; i < amount; i++) {
                
                Rectangle color = new Rectangle(30, 15);
                Label size = new Label("?");
                
                
                
                colorPreview[i] = color;
                sizePreview[i] = size;
                
                size.setStyle("-fx-font: 12 monospace;");
                
                color.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
                
                color.setFill(Color.rgb(0, 0, 0, 0));
                
                if(i == 0) {
                    color.setFill(defaultColor);
                    size.setText(defaultSize + "");
                    colors.add(defaultColor);
                    sizes.add(defaultSize);
                }
                
                
                if(i >= 24) {
                    add(color, 3, i - 24);
                    add(size, 2, i - 24);
                }
                else {
                    // i + 1 since title gets 0th position
                    add(color, 1, i + 1);
                    add(size, 0, i + 1);
                }
                
            }
            
            setVgap(-1);
            
            // current color and size
            colorPosition = 0;
            sizePosition = 0;
        }
        
        public void previewColor(Color color)
        {
            // in case there are more colors added then there are previews (use modulus)
            colorPreview[colorPosition % amount].setFill(color);
        }
        
        public void previewSize(String value)
        {
            // in case there are more sizes added then there are previews (use modulus)
            sizePreview[sizePosition % amount].setText(value);
        }
        
        public void addColor(Color color)
        {
            colorPosition++;
            colors.add(color);
        }
        
        public void removeColor()
        {
            colorPosition--;
            colors.remove(colorPosition);
        }
        
        public void addSize(Double value)
        {
            sizePosition++;
            sizes.add(value);
        }
        
        public void removeSize()
        {
            sizePosition--;
            sizes.remove(sizePosition);
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
