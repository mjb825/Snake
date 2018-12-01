/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        Slider rSlider = new Slider(0, 255, 255);
        Slider gSlider = new Slider(0, 255, 0);
        Slider bSlider = new Slider(0, 255, 0);
        Slider aSlider = new Slider(0, 1, 1);
        // color select textfields
        TextField rField = new TextField("255");
        rField.setMaxWidth(40);
        rField.setEditable(false);/*
        rField.textProperty().addListener(l -> {
           if(!rField.getText().matches("(^[1-9][0-9]{1,2}$) || (^[1-9][0-9]$) || (^[0-9]$)") && rField.getText().matches(".+")) {
               rField.setText(rField.getText().substring(0, rField.getText().length() - 1));
           }
           else
               rSlider.setValue(new Double(rField.getText()));
        });*/
        TextField gField = new TextField("0");
        gField.setMaxWidth(40);
        gField.setEditable(false);
        TextField bField = new TextField("0");
        bField.setMaxWidth(40);
        bField.setEditable(false);
        TextField aField = new TextField("100");
        aField.setMaxWidth(40);
        aField.setEditable(false);
        // add sliders and labels
        options.add(rSlider, 0, 0);
        options.add(new Label("R: "), 2, 0);
        options.add(rField, 3, 0);
        options.add(gSlider, 0, 1);
        options.add(new Label("G: "), 2, 1);
        options.add(gField, 3, 1);
        options.add(bSlider, 0, 2);
        options.add(new Label("B: "), 2, 2);
        options.add(bField, 3, 2);
        options.add(aSlider, 0, 3);
        options.add(new Label("A%: "), 2, 3);
        options.add(aField, 3, 3);
        
        //options.add(new BlankSpace(80, 10), 1, 0);
        options.add(new BlankSpace(20, 200), 0, 4);
        
        // radio buttons for selecting which category to edit
        HBox categories = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton headRadio = new RadioButton("HEAD");
        headRadio.setToggleGroup(group);
        RadioButton tailRadio = new RadioButton("TAIL");
        tailRadio.setToggleGroup(group);
        RadioButton foodRadio = new RadioButton("FOOD");
        foodRadio.setToggleGroup(group);
        categories.getChildren().addAll(headRadio, tailRadio, foodRadio);
        
        // set head as beginning category
        headRadio.fire();
        
        options.add(categories, 0, 4);
        
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
        options.add(head, 4, 0, 1, 30);
        options.add(tail, 5, 0, 1, 30);
        options.add(food, 6, 0, 1, 30);
        

        size.textProperty().addListener(l -> {
            // only allow values [1, 99.99]
            if(size.getText().matches("(([1-9][0-9])|[1-9])([.][0-9]{0,2})?"))
                if(headRadio.isSelected())
                    head.previewSize(size.getText());
                else if(tailRadio.isSelected())
                    tail.previewSize(size.getText());
                else
                    food.previewSize(size.getText());
            // trim textfield if doesn't result in a legit match
            else if(size.getText().matches(".+"))
                size.setText(size.getText().substring(0, size.getText().length() - 1));
        });

        //[change] combine all of these into one method
        rSlider.valueProperty().addListener(l -> {
            r = (int)rSlider.getValue();
            rField.setText("" + r);
            if(headRadio.isSelected())
                head.previewColor(Color.rgb(r, g, b, a));
            else if(tailRadio.isSelected())
                tail.previewColor(Color.rgb(r, g, b, a));
            else
                food.previewColor(Color.rgb(r, g, b, a));
        });
        
        gSlider.valueProperty().addListener(l -> {
            g = (int)gSlider.getValue();
            gField.setText("" + g);
            if(headRadio.isSelected())
                head.previewColor(Color.rgb(r, g, b, a));
            else if(tailRadio.isSelected())
                tail.previewColor(Color.rgb(r, g, b, a));
            else
                food.previewColor(Color.rgb(r, g, b, a));
        });
        
        bSlider.valueProperty().addListener(l -> {
            b = (int)bSlider.getValue();
            bField.setText("" + b);
            if(headRadio.isSelected())
                head.previewColor(Color.rgb(r, g, b, a));
            else if(tailRadio.isSelected())
                tail.previewColor(Color.rgb(r, g, b, a));
            else
                food.previewColor(Color.rgb(r, g, b, a));
        });
        
        aSlider.valueProperty().addListener(l -> {
            a = aSlider.getValue();
            aField.setText("" + (int)(a * 100));
            if(headRadio.isSelected())
                head.previewColor(Color.rgb(r, g, b, a));
            else if(tailRadio.isSelected())
                tail.previewColor(Color.rgb(r, g, b, a));
            else
                food.previewColor(Color.rgb(r, g, b, a));
        });
        

        
        getChildren().addAll(options);

        
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
