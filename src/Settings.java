/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    double size;
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
        size = 6;
        
        GridPane options = new GridPane();
        
        /**************************
         * CATEGORY RADIO BUTTONS *
         **************************/
        
        // category label
        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        options.add(categoryLabel, 0, 0);
        
        // category radio buttons
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
        categories.setSpacing(6);
        options.add(categories, 0, 1);
        
        /*****************
         * COLOR OPTIONS *
         *****************/
        // color options label
        Label colorLabel = new Label("Color Options");
        colorLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        options.add(colorLabel, 0, 2);
        
        // color gradient options
        HBox colorGradient = new HBox();
        CheckBox colorGrad = new CheckBox("Gradient");
        TextField colorGradAmount = new TextField();
        colorGradAmount.setMaxWidth(60);
        colorGradient.getChildren().addAll(colorGrad, colorGradAmount);
        colorGradient.setSpacing(6);
        options.add(colorGradient, 0, 3);
        
        // color buttons (add, remove)
        HBox colorButtons = new HBox();
        Button colorAdd = new Button("Add");
        Button colorRemove = new Button("Remove");
        colorButtons.getChildren().addAll(colorAdd, colorRemove);
        colorButtons.setSpacing(6);
        options.add(colorButtons, 0, 4);
        
        /****************
         * COLOR SELECT *
         ****************/
        
        // color select sliders
        Slider rSlider = new Slider(0, 255, 255);
        Slider gSlider = new Slider(0, 255, 0);
        Slider bSlider = new Slider(0, 255, 0);
        Slider aSlider = new Slider(0, 1, 1);
        
        // add sliders
        options.add(rSlider, 0, 5);
        options.add(gSlider, 0, 6);
        options.add(bSlider, 0, 7);
        options.add(aSlider, 0, 8);
        
        // color select textfields
        TextField rField = new TextField("255");
        rField.setMaxWidth(40);
        rField.setEditable(false);
        TextField gField = new TextField("0");
        gField.setMaxWidth(40);
        gField.setEditable(false);
        TextField bField = new TextField("0");
        bField.setMaxWidth(40);
        bField.setEditable(false);
        TextField aField = new TextField("100");
        aField.setMaxWidth(40);
        aField.setEditable(false);
        
        // add labels and textfields
        options.add(new Label("R: "), 2, 5);
        options.add(rField, 3, 5);
        options.add(new Label("G: "), 2, 6);
        options.add(gField, 3, 6);
        options.add(new Label("B: "), 2, 7);
        options.add(bField, 3, 7);
        options.add(new Label("A: "), 2, 8);
        options.add(aField, 3, 8);
        
        /****************
         * SIZE OPTIONS *
         ****************/
        // size options label
        Label sizeLabel = new Label("Size Options");
        sizeLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        options.add(sizeLabel, 0, 9);
        
        // size gradient options
        HBox sizeGradient = new HBox();
        CheckBox sizeGrad = new CheckBox("Gradient");
        TextField sizeGradAmount = new TextField();
        sizeGradAmount.setMaxWidth(60);
        sizeGradient.getChildren().addAll(sizeGrad, sizeGradAmount);
        sizeGradient.setSpacing(6);
        options.add(sizeGradient, 0, 10);
        
        // size buttons (add, remove)
        HBox sizeButtons = new HBox();
        Button sizeAdd = new Button("Add");
        Button sizeRemove = new Button("Remove");
        sizeButtons.getChildren().addAll(sizeAdd, sizeRemove);
        sizeButtons.setSpacing(6);
        options.add(sizeButtons, 0, 11);
        
        /***************
         * SIZE SELECT *
         ***************/
        Slider sizeSlider = new Slider(4, 20, 6);
        
        options.add(sizeSlider, 0, 12);
        
        // size select textfield
        TextField sizeField = new TextField("6");
        sizeField.setMaxWidth(40);
        sizeField.setEditable(false);
        
        // add labels and textfields
        options.add(new Label("#: "), 2, 12);
        options.add(sizeField, 3, 12);
        
        /****************
         * GAME OPTIONS *
         ****************/
        // game options label
        Label gameLabel = new Label("Game Options");
        gameLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        options.add(gameLabel, 0, 13);
        
        // game options checkboxes
        HBox gameOptions = new HBox();
        CheckBox headUnique = new CheckBox("Head Unique");
        CheckBox sequence = new CheckBox("Sequence");
        CheckBox mirror = new CheckBox("Mirror");
        gameOptions.getChildren().addAll(headUnique, sequence, mirror);
        gameOptions.setSpacing(6);
        options.add(gameOptions, 0, 14);
        
        /*********************
         * CATEGORY PREVIEWS *
         *********************/
        // add previews for HEAD, TAIL, FOOD
        SettingsPreview head = new SettingsPreview("HEAD", Color.RED, 6.0);
        SettingsPreview tail = new SettingsPreview("TAIL", Color.BLUE, 6.0);
        SettingsPreview food = new SettingsPreview("FOOD", Color.ORANGE, 6.0);
        // add previews to options
        options.add(head, 4, 0, 1, 30);
        options.add(tail, 5, 0, 1, 30);
        options.add(food, 6, 0, 1, 30);
        
        colorAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addColor();
            else if(tailRadio.isSelected())
                tail.addColor();
            else
                food.addColor();
        });
        
        sizeAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addSize();
            else if(tailRadio.isSelected())
                tail.addSize();
            else
                food.addSize();
        });

//        size.textProperty().addListener(l -> {
//            // only allow values [1, 99.99]
//            if(size.getText().matches("(([1-9][0-9])|[1-9])([.][0-9]{0,2})?"))
//                if(headRadio.isSelected())
//                    head.previewSize(size.getText());
//                else if(tailRadio.isSelected())
//                    tail.previewSize(size.getText());
//                else
//                    food.previewSize(size.getText());
//            // trim textfield if doesn't result in a legit match
//            else if(size.getText().matches(".+"))
//                size.setText(size.getText().substring(0, size.getText().length() - 1));
//        });

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
        
        sizeSlider.valueProperty().addListener(l -> {
            size = sizeSlider.getValue();
            sizeField.setText("" + (int)size);
            if(headRadio.isSelected())
                head.previewSize(size);
            else if(tailRadio.isSelected())
                tail.previewSize(size);
            else
                food.previewSize(size);
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
        
        public SettingsPreview(String title, Color defaultColor, double defaultSize)
        {
            // set title for previews
            this.title = new Label(title);
            this.title.setStyle("-fx-font: 12 monospace;");
            add(this.title, 1, 0);
            
            // set number placeholders above sizes
            Label placeHolder = new Label("##");
            placeHolder.setStyle("-fx-font: 12 monospace");
            add(placeHolder, 0, 0);
            
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
                    size.setText((int)defaultSize + "");
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
            setHgap(6);
            setPadding(new Insets(0, 0, 0, 12));
            
            // current color and size
            colorPosition = 0;
            sizePosition = 0;
        }
        
        public void previewColor(Color color)
        {
            // in case there are more colors added then there are previews (use modulus)
            colorPreview[colorPosition % amount].setFill(color);
        }
        
        public void previewSize(double value)
        {
            // in case there are more sizes added then there are previews (use modulus)
            sizePreview[sizePosition % amount].setText("" + (int)value);
        }
        
        public void addColor()
        {
            colorPosition++;
            //colors.add(color);
        }
        
        public void removeColor()
        {
            colorPosition--;
            colors.remove(colorPosition);
        }
        
        public void addSize()
        {
            sizePosition++;
            //sizes.add(value);
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
