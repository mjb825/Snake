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
    
    private int r;
    private int g;
    private int b;
    private double a;
    private double size;
    private ArrayList<Color> headColors;
    private ArrayList<Color> tailColors;
    private ArrayList<Color> foodColors;
    private ArrayList<Double> headSizes;
    private ArrayList<Double> tailSizes;
    private ArrayList<Double> foodSizes;
    
    private CheckBox colorGrad;
    private Slider colorStepSlider;
    private CheckBox sizeGrad;
    private Slider sizeStepSlider;
    private TextField colorGradAmount;
    private TextField sizeGradAmount;
    
    private CheckBox headUnique;
    private CheckBox sequence;
    private CheckBox mirror;

    public Settings() {
    
        headColors = new ArrayList<Color>();
        tailColors = new ArrayList<Color>();
        foodColors = new ArrayList<Color>();
        headSizes = new ArrayList<Double>();
        tailSizes = new ArrayList<Double>();
        foodSizes = new ArrayList<Double>();
        
        headColors.add(Color.RED);
        
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
        colorGrad = new CheckBox("Gradient");
        colorGradAmount = new TextField();
        colorGradAmount.setMaxWidth(60);
        
        colorGradAmount.textProperty().addListener(l -> {
            
            // trim last character if value is not in range [1, 29]
            if(!colorGradAmount.getText().matches("([1-9])||(1[0-9])||(20)") && colorGradAmount.getText().matches(".+")) {
                colorGradAmount.setText(colorGradAmount.getText().substring(0, colorGradAmount.getText().length() - 1));
            }
        });
        
        colorStepSlider = new Slider(2, 20, 2);
        TextField colorSliderField = new TextField("2");
        colorSliderField.setMaxWidth(40);
        colorSliderField.setEditable(false);
        
        colorStepSlider.valueProperty().addListener(l -> {
            colorSliderField.setText("" + (int)colorStepSlider.getValue());
        });
        
        colorGradient.getChildren().addAll(colorGrad, colorStepSlider);
        colorGradient.setSpacing(6);
        options.add(colorGradient, 0, 3);
        options.add(colorSliderField, 3, 3);
        
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
        sizeGrad = new CheckBox("Gradient");
        sizeGradAmount = new TextField();
        sizeGradAmount.setMaxWidth(60);
        
        sizeStepSlider = new Slider(2, 20, 2);
        TextField sizeSliderField = new TextField("2");
        sizeSliderField.setMaxWidth(40);
        sizeSliderField.setEditable(false);
        
        sizeStepSlider.valueProperty().addListener(l -> {
            sizeSliderField.setText("" + (int)sizeStepSlider.getValue());
        });
        
        sizeGradient.getChildren().addAll(sizeGrad, sizeStepSlider);
        sizeGradient.setSpacing(6);
        options.add(sizeGradient, 0, 10);
        options.add(sizeSliderField, 3, 10);
        
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
        headUnique = new CheckBox("Head Unique");
        sequence = new CheckBox("Sequence");
        mirror = new CheckBox("Mirror");
        gameOptions.getChildren().addAll(headUnique, sequence, mirror);
        gameOptions.setSpacing(6);
        options.add(gameOptions, 0, 14);
        
        /*********************
         * CATEGORY PREVIEWS *
         *********************/
        // add previews for HEAD, TAIL, FOOD
        SettingsPreview head = new SettingsPreview("HEAD", headColors, headSizes);
        SettingsPreview tail = new SettingsPreview("TAIL", tailColors, tailSizes);
        SettingsPreview food = new SettingsPreview("FOOD", foodColors, foodSizes);
        // add previews to options
        options.add(head, 4, 0, 1, 30);
        options.add(tail, 5, 0, 1, 30);
        options.add(food, 6, 0, 1, 30);
        
        /*************************
         * ACTIONS AND LISTENERS *
         *************************/
        colorAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addColor();
            else if(tailRadio.isSelected())
                tail.addColor();
            else
                food.addColor();
        });
        
        colorRemove.setOnAction(e -> {
            if(headRadio.isSelected())
                head.removeColor();
            else if(tailRadio.isSelected())
                tail.removeColor();
            else
                food.removeColor();
        });
        
        sizeAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addSize();
            else if(tailRadio.isSelected())
                tail.addSize();
            else
                food.addSize();
        });
        
        sizeRemove.setOnAction(e -> {
            if(headRadio.isSelected())
                head.removeSize();
            else if(tailRadio.isSelected())
                tail.removeSize();
            else
                food.removeSize();
        });

        //[change] combine all of these into one method
        rSlider.valueProperty().addListener(l -> {
            r = (int)rSlider.getValue();
            rField.setText("" + r);
            if(headRadio.isSelected())
                head.previewColor();
            else if(tailRadio.isSelected())
                tail.previewColor();
            else
                food.previewColor();
        });
        
        gSlider.valueProperty().addListener(l -> {
            g = (int)gSlider.getValue();
            gField.setText("" + g);
            if(headRadio.isSelected())
                head.previewColor();
            else if(tailRadio.isSelected())
                tail.previewColor();
            else
                food.previewColor();
        });
        
        bSlider.valueProperty().addListener(l -> {
            b = (int)bSlider.getValue();
            bField.setText("" + b);
            if(headRadio.isSelected())
                head.previewColor();
            else if(tailRadio.isSelected())
                tail.previewColor();
            else
                food.previewColor();
        });
        
        aSlider.valueProperty().addListener(l -> {
            a = aSlider.getValue();
            aField.setText("" + (int)(a * 100));
            if(headRadio.isSelected())
                head.previewColor();
            else if(tailRadio.isSelected())
                tail.previewColor();
            else
                food.previewColor();
        });
        
        sizeSlider.valueProperty().addListener(l -> {
            size = sizeSlider.getValue();
            sizeField.setText("" + (int)size);
            if(headRadio.isSelected())
                head.previewSize();
            else if(tailRadio.isSelected())
                tail.previewSize();
            else
                food.previewSize();
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
        
        private ArrayList<Color> colorsList;
        private ArrayList<Double> sizesList;
        
        // test whether previous values have been assigned or not
        private boolean prevColorsSet;
        private boolean prevSizeSet;
        
        // previous values used to calculate gradient
        // double for colors for better accuracy despite color still being assigned an int
        private double prevR;
        private double prevG;
        private double prevB;
        private double prevA;
        private double prevSize;
        
        public SettingsPreview(){}
        
        public SettingsPreview(String title, ArrayList<Color> colorsList, ArrayList<Double> sizesList)
        {
            // set title for previews
            this.title = new Label(title);
            this.title.setStyle("-fx-font: 12 monospace;");
            add(this.title, 1, 0);
            
            // set number placeholders above sizes
            Label placeHolder = new Label("##");
            placeHolder.setStyle("-fx-font: 12 monospace");
            add(placeHolder, 0, 0);
            
            // set amount of previews
            amount = 24;
            
            // assign array lists
            this.colorsList = colorsList;
            this.sizesList = sizesList;
            
            colorPreview = new Rectangle[amount];
            sizePreview = new Label[amount];
            
            for(int i = 0; i < amount; i++) {
                
                Rectangle color = new Rectangle(30, 15);
                Label size = new Label("?");
                
                
                
                colorPreview[i] = color;
                sizePreview[i] = size;
                
                size.setStyle("-fx-font: 12 monospace;");
                
                color.setStroke(Color.BLACK);
                color.setStrokeWidth(1);
                
                color.setFill(Color.rgb(0, 0, 0, 0));
                
                if(i == 0) {
                    color.setStrokeWidth(2);
                    size.setUnderline(true);
                }
            
                // i + 1 since title gets 0th position
                add(color, 1, i + 1);
                add(size, 0, i + 1);
                
            }
            
            setVgap(-1);
            setHgap(6);
            setPadding(new Insets(0, 0, 0, 12));
            
            // current color and size
            colorPosition = 0;
            sizePosition = 0;
        }
        
        public void previewColor()
        {
            // in case there are more colors added then there are previews (use modulus)
            colorPreview[colorPosition % amount].setFill(Color.rgb(r, g, b, a));
        }
        
        public void previewSize()
        {            
            // in case there are more sizes added then there are previews (use modulus)
            sizePreview[sizePosition % amount].setText("" + (int)size);
        }
        
        public void addColor()
        {
            int howMany = 1;
            boolean gradientSet = false;
            
            double gradR = 0;
            double gradG = 0;
            double gradB = 0;
            double gradA = 0;
            
            // store colors
            int tempR = r;
            int tempG = g;
            int tempB = b;
            double tempA = a;
            
            // adjust how many according to colorStepSlider
            //[update] howMany = StepSlider >  colorPosition ? colorPosition : StepSlider
            // prevent causing multiple loops and isEmpty test shouldn't be required either
            if(colorGrad.isSelected())
                howMany = (int)colorStepSlider.getValue();
            
            for(int i = 1; i < howMany; i++) {
                // set previous values in case of gradient
                //[add] logic for transition will go here!! using howMany!
                
                // if previous values haven't been set, set them to current colors before use
                if(!prevColorsSet) {
                    prevR = r;
                    prevG = g;
                    prevB = b;
                    prevA = a;
                    prevColorsSet = true;
                }
                
                // calculate value used for gradient
                if(!gradientSet) {
                    gradR = Math.abs(r - prevR) / howMany;
                    gradG = Math.abs(g - prevG) / howMany;
                    gradB = Math.abs(b - prevB) / howMany;
                    gradA = Math.abs(a - prevA) / howMany;
                    gradientSet = true;
                }
                
                /*
                r = r == (int)prevR ? r : (int) ( (Math.abs(r - prevR) / howMany) * (i) );
                
                

                if(!gradientSet) {
                    gradR =
                    gradientSet = true;
                }
*/
//                System.out.println("r: " + r + " prevR: " + prevR);
//                System.out.println("g: " + g + " prevG: " + prevG);
//                System.out.println("b: " + b + " prevB: " + prevB);
//                System.out.println("a: " + a + " prevA: " + prevA);

                //System.out.println(r - prevR);

                //System.out.println(r + "==" + prevR);
                //System.out.println("Calculated: " + ((int) ( (Math.abs(r - prevR) / howMany) * (howMany - (i)) )));
                
//                r = r == (int)prevR ? r : (int) ( (Math.abs(r - prevR) / howMany) * (howMany - i) );
//                g = g == (int)prevG ? g : (int) ( (Math.abs(g - prevG) / howMany) * (howMany - i) );
//                b = b == (int)prevB ? b : (int) ( (Math.abs(b - prevB) / howMany) * (howMany - i) );
//                a = a == prevA ? a : ( (Math.abs(a - prevA) / howMany) * (howMany - i) );
                
/* somehow more accurate?*/
                int rHuh = r - (int)prevR < 0 ? howMany - i : i;
                int gHuh = g - (int)prevG < 0 ? howMany - i : i;
                int bHuh = b - (int)prevB < 0 ? howMany - i : i;
                int aHuh = a - prevA < 0 ? howMany - i : i;
                
                r = r == (int)prevR ? r : (int) ( gradR * rHuh );
                g = g == (int)prevG ? g : (int) ( gradG * gHuh );
                b = b == (int)prevB ? b : (int) ( gradB * bHuh );
                a = a == prevA ? a : ( gradA * aHuh );
/**/

//                r = r == (int)prevR ? r : (int) ( gradR * (i) );
//                g = g == (int)prevG ? g : (int) ( gradG * (i) );
//                b = b == (int)prevB ? b : (int) ( gradB * (i) );
//                a = a == prevA ? a : ( gradA * (i) );
                
                
//                r = (int)(gradR * (howMany - i));
//                g = (int)(gradG * (howMany - i));
//                b = (int)(gradB * (howMany - i));
                //a = (prevA * (howMany - i));
                
                
//                System.out.println("r: " + r + " prevR: " + prevR);
//                System.out.println("g: " + g + " prevG: " + prevG);
//                System.out.println("b: " + b + " prevB: " + prevB);
//                System.out.println("a: " + a + " prevA: " + prevA);
                
                previewColor();
                colorPreview[colorPosition % amount].setStrokeWidth(1);
                colorPosition++;
                colorPreview[colorPosition % amount].setStrokeWidth(2);

                // rgb(prevR, prevG, prevB, prevA) ???
                
                // add to list
                colorsList.add(Color.rgb(r, g, b, a));
            }
            
            // restore colors
            r = tempR;
            g = tempG;
            b = tempB;
            a = tempA;
            
            previewColor();
            colorPreview[colorPosition % amount].setStrokeWidth(1);
            colorPosition++;
            colorPreview[colorPosition % amount].setStrokeWidth(2);

            // add to list
            colorsList.add(Color.rgb(r, g, b, a));
            
            
            // set previous values to current values
            prevR = r;
            prevG = g;
            prevB = b;
            prevA = a;
            prevColorsSet = true;
        }
        
        public void removeColor()
        {
            // how many colors to remove from list and how many previews to reset
            int howMany = 1;
            
            // adjust how many according to colorStepSlider
            //[update] howMany = StepSlider >  colorPosition ? colorPosition : StepSlider
            // prevent causing multiple loops and isEmpty test shouldn't be required either
            if(colorGrad.isSelected())
                howMany = (int)colorStepSlider.getValue();
            
            for(int i = 0; i < howMany; i++) {
                // reset color preview
                // in case there are more colors added then there are previews (use modulus)
                colorPreview[colorPosition % amount].setFill(Color.rgb(0, 0, 0, 0));

                colorPreview[colorPosition % amount].setStrokeWidth(1);
                colorPosition = colorPosition == 0 ? 0 : colorPosition - 1;
                colorPreview[colorPosition % amount].setStrokeWidth(2);

                // remove from list if not empty
                if(!colorsList.isEmpty())
                    colorsList.remove(colorPosition);
            }
        }
        
        public void addSize()
        {
            
            int howMany = 1;
            
            if(sizeGrad.isSelected())
                howMany = (int)sizeStepSlider.getValue();
            
            // set previous values in case of gradient
            //prevSize = size;
            
            for(int i = 0; i < howMany; i++) {
                previewSize();
                sizePreview[sizePosition % amount].setUnderline(false);
                sizePosition++;
                sizePreview[sizePosition % amount].setUnderline(true);
                sizesList.add(size);
            }
        }
        
        public void removeSize()
        {
            // how many to remove from list and how many previews to reset
            int howMany = 1;
            
            // adjust how many according to StepSlider
            if(sizeGrad.isSelected())
                howMany = (int)sizeStepSlider.getValue();
            
            for(int i = 0; i < howMany; i++) {
                // reset size preview
                // in case there are more sizes added then there are previews (use modulus)
                sizePreview[sizePosition % amount].setText("?");

                sizePreview[sizePosition % amount].setUnderline(false);
                sizePosition = sizePosition == 0 ? 0 : sizePosition - 1;
                sizePreview[sizePosition % amount].setUnderline(true);
                if(!sizesList.isEmpty())
                    sizesList.remove(sizePosition);
            }
        }
        
    }
    
    public ArrayList<Color> getHeadColors()
    {
        return headColors;
    }
    
    public ArrayList<Double> getHeadSizes()
    {
        return headSizes;
    }
    
    public ArrayList<Color> getTailColors()
    {
        return tailColors;
    }
    
    public ArrayList<Double> getTailSizes()
    {
        return tailSizes;
    }
    
    public ArrayList<Color> getFoodColors()
    {
        return foodColors;
    }
    
    public ArrayList<Double> getFoodSizes()
    {
        return foodSizes;
    }
    
    public boolean headUnique()
    {
        return headUnique.isSelected();
    }
    
    public boolean sequence()
    {
        return sequence.isSelected();
    }
    
    public boolean mirror()
    {
        return mirror.isSelected();
    }
    
}
