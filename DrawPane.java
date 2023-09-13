import java.util.ArrayList;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawPane extends BorderPane
{

    // add instance variables
    private ArrayList<ArrayList> strokeList;
    private CanvasPane canvas;
    private Button btnClear, btnUndo;
    private ArrayList<ArrayList> tempList;
    private double radius;
    private String colorStr;
    private ComboBox<String> colors;
    private Label colorsLabel;
    private Color currentColor;
    private Slider slider;
    private ImageView image1;
    private Rectangle colorSq;

    //constructor
    public DrawPane(String level, String imageStr, String[] colorsStr)
    {
    	strokeList = new ArrayList<ArrayList>();
        tempList = new ArrayList<ArrayList>();
    	
    	//top
    	Label labelL1 = new Label(level);
        labelL1.setFont(new Font("Comic Sans MS", 30));
        labelL1.setTextFill(Paint.valueOf("#4682B4"));
        this.setStyle("-fx-background-color: linear-gradient(to top,  #FFFACD, #DDA0DD)");
        
        VBox labelCenterL1 = new VBox();
        labelCenterL1.setAlignment(Pos.CENTER);
        labelCenterL1.setSpacing(30);
        labelCenterL1.getChildren().add(labelL1);
        labelCenterL1.setPadding(new Insets(12, 20, 0, 20));
        this.setTop(labelCenterL1);
           
        //bottom
        Pane bottomL1 = new Pane();
        Button backButton1 = new Button("Back");
        bottomL1.getChildren().add(backButton1);
        bottomL1.setPadding(new Insets(12, 20, 12, 20));
        this.setBottom(bottomL1);
    	
        
      //left
        colorStr = colorsStr[0];
        colorsLabel = new Label("Color: " + getColorStr());
        colorsLabel.setFont(new Font("Comic Sans MS", 20));
        colorsLabel.setTextFill(Paint.valueOf("#4682B4"));
        colors = new ComboBox<String>();
        for(String s : colorsStr) {
        	colors.getItems().add(s);
        }
        colors.setValue(colorsStr[0]);
        currentColor = getColor(colorsStr[0]);
        VBox left = new VBox();
        left.setMaxWidth((750/3));
        left.setMaxHeight((400/3));
        left.setMinHeight((400/3));
        left.setMinWidth((750/3));
        left.setSpacing(20);
        left.setPadding(new Insets(12, 20, 12, 20));
        
        colorSq = new Rectangle(30, 30, (Paint) currentColor);
        
        HBox showColor = new HBox();
        showColor.getChildren().addAll(colorsLabel, colorSq);
        showColor.setSpacing(10);
        
        left.getChildren().addAll(showColor, colors);
        this.setLeft(left);
        colors.setOnAction(new ComboHandler());
        
        //right
        slider = new Slider(1, 10, 1);         
        slider.setOrientation(Orientation.VERTICAL);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setPadding(new Insets(12, 20, 12, 0));
        slider.setMajorTickUnit(1.0);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        this.setRight(slider);
        
 
        //center
        StackPane root = new StackPane();
        image1 = new ImageView(imageStr);
        Image image = new Image(imageStr);
        image1.setImage(image);
        image1.setFitHeight(400);
        image1.setFitWidth(500);
        root.setMaxSize(500, 400);
        root.setMinSize(500, 400);
         
        VBox centerV = new VBox();
         
        btnUndo = new Button("Undo");
        btnClear = new Button("Clear");
     	btnUndo.setOnAction(new ButtonHandler());
     	btnClear.setOnAction(new ButtonHandler());
     	canvas = new CanvasPane(500, 400);
     	
     	HBox buttonsL1 = new HBox();
        buttonsL1.setAlignment(Pos.CENTER);
        buttonsL1.setSpacing(30);
        buttonsL1.setPadding(new Insets(12, 20, 12, 20));

        buttonsL1.getChildren().addAll(btnUndo, btnClear);
         
        root.getChildren().addAll(image1, canvas);
        centerV.getChildren().addAll(root, buttonsL1);
        centerV.setPadding(new Insets(12, 20, 0, 20));
        this.setCenter(centerV);
        
        PointerHandler pH = new PointerHandler();
        canvas.setOnMouseReleased(pH);
        canvas.setOnMouseDragged(pH);
        canvas.setOnMousePressed(pH);
    }

    
    public String getColorStr() {
    	String[] colorNameArray = colorStr.split(": ");
    	return colorNameArray[1];
    }
    
    public Color getColor(String colorR) {
		Color c = Color.BLACK;
		String currColor = getColorStr();
		if(currColor.equals("Red")) {
    		c = Color.RED;
    	} else if(currColor.equals("Green")) {
    		c = Color.GREEN;
    	} else if(currColor.equals("Red Orange")) {
    		c = Color.ORANGERED;
    	} else if(currColor.equals("Orange")) {
    		c = Color.ORANGE;
    	} else if(currColor.equals("Beige")) {
    		c = Color.BEIGE;
    	} else if(currColor.equals("Yellow")) {
    		c = Color.YELLOW;
    	} else if(currColor.equals("Purple")) {
    		c = Color.PURPLE;
    	} else if(currColor.equals("Pink")) {
    		c = Color.PINK;
    	} else if(currColor.equals("Blue")) {
    		c = Color.BLUE;
    	} else if(currColor.equals("Brown")) {
    		c = Color.BROWN;
    	} else if(currColor.equals("Light Green")) {
    		c = (Color) Paint.valueOf("#00ff80");
    	} else if(currColor.equals("Green")) {
    		c = Color.GREEN;
    	} else if(currColor.equals("Leaf Green")) {
    		c = Color.GREENYELLOW;
    	} else if(currColor.equals("Dark Green")) {
    		c = Color.DARKGREEN;
    	} else if(currColor.equals("Light Blue")) {
    		c = Color.CORNFLOWERBLUE;
    	} else if(currColor.equals("Tan")) {
    		c = Color.TAN;
    	} else if(currColor.equals("Navy Blue")) {
    		c = Color.CADETBLUE;
    	}
		return c;
    }

    /**
     * CanvasPane is the panel where Circles will be drawn on.
     */
    private class CanvasPane extends Pane
    {   
        private ArrayList<Circle> placeholderStroke;
               
        public CanvasPane(int width, int height)
        {
        	placeholderStroke = new ArrayList<Circle>();
        	this.setWidth(width);
        	this.setHeight(height);
        }

        public void drawPlaceHolder(int x, int y, double radius, boolean last)
        {
            Circle tempPlaceholderCircles = new Circle(x, y, radius, currentColor);
        	placeholderStroke.add(tempPlaceholderCircles);      	
            if (last == false)
            {
            	 canvas.getChildren().add(tempPlaceholderCircles);
            }

        }        
        
        public void erasePlaceHolder(boolean last)
        {
        	for(Circle c: placeholderStroke) {
        		canvas.getChildren().remove(c);
        	}
            placeholderStroke.clear();
        }
        
        public ArrayList<Circle> getPlaceHolder(){
        	return placeholderStroke;
        }
        
        public void repaint(){
        	this.getChildren().clear();
            for (ArrayList<Circle> a : strokeList){
            	for (Circle c : a) {
            		this.getChildren().addAll(c);
                 }
            }
        }
    }

    /**
     * ButtonListener defines actions to take in case the "Undo" or "Clear"
     * button is clicked
     */
    private boolean clrPressed = false;
    private class ButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent e)
        {
            Object source = e.getSource();
            // Check if source refers to the Clear button
            if (source == btnClear)
            {
                //write your code here              
            	strokeList.clear();
            	canvas.repaint();
            	clrPressed = true;            	
            }
            // Check if source refers to the Undo button
            else if (source == btnUndo)
            {
                if(strokeList.size() > 0 && clrPressed == false) {
            		strokeList.remove(strokeList.get(strokeList.size()-1));
            		tempList.remove(tempList.get(tempList.size()-1));
            	} else if(strokeList.size() == 0 && clrPressed == true) {
            		for(int i = 0; i < tempList.size(); i++) {
            			strokeList.add(tempList.get(i));
            		}
            		canvas.repaint();
            		clrPressed = false;
            	}
                canvas.repaint();
            }
        }

    }

    /**
     * ComboHandler is a listener class used to set the color chosen by the user via the
     * ComboBox of Colors.
     */
    private class ComboHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent e)
        {
        	colorStr = colors.getValue();
        	currentColor = getColor(colorStr);
            colorsLabel.setText("Color: " + getColorStr());
            colorSq.setFill(currentColor);
        }

    }
    private int x, y;
    private ArrayList<Circle> strokeSave = new ArrayList<Circle>();
    /**
     * PointerHandler is a listener class that handles any mouse events on the Canvas
     */
    private class PointerHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent e)
        {
            if(e.getEventType()== MouseEvent.MOUSE_DRAGGED) {
        		x = (int) e.getX();
        		y = (int) e.getY();
        		radius = (int) slider.getValue() * 2;
        		
        		if(x-radius >= image1.getLayoutBounds().getMinX() && x+radius <= image1.getLayoutBounds().getMaxX()) {
        			if(y-radius >= image1.getLayoutBounds().getMinY() && y+radius <= image1.getLayoutBounds().getMaxY()) {
        				canvas.drawPlaceHolder(x, y, radius, false);
        			}
        		}
        	} 
        	if(e.getEventType()== MouseEvent.MOUSE_RELEASED) {
        		ArrayList<Circle> strokeSaveHolder = new ArrayList<Circle>();
        		strokeSaveHolder = canvas.getPlaceHolder();
        		for(Circle c : strokeSaveHolder) {
        			strokeSave.add(c);
        		}
        		canvas.erasePlaceHolder(false);
        		if(strokeSave.size() != 0) {
        			ArrayList<Circle> stroke = new ArrayList<Circle>();
        			for (Circle c : strokeSave) {
        				stroke.add(c);
        			}
        			strokeList.add(stroke);
        			tempList.add(stroke);
        		}
        		canvas.repaint();
        		strokeSave.clear();
        	}
        }

    }
}