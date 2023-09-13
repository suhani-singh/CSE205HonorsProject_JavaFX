import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class MainProject extends Application
{
    private Stage window;
    private Scene main, scene1, scene2, scene3;

    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
    	try {
    	window = stage;
    	
    	//Main Page
    	
    	BorderPane mainPg = new BorderPane();

    	Button button1 = new Button("Level 1");
        button1.setOnAction(e -> window.setScene(scene1));
        Button buttonl2 = new Button("Level 2");
        buttonl2.setOnAction(e -> window.setScene(scene2));
        Button buttonl3 = new Button("Level 3");
        buttonl3.setOnAction(e -> window.setScene(scene3));
        
        VBox mainMenu = new VBox();
        Label label1 = new Label("Coloring Book: Paint By Numbers"); 
        label1.setFont(new Font("Comic Sans MS", 30));
        label1.setTextFill(Paint.valueOf("#4682B4"));
        
        HBox levelButtons = new HBox();
        levelButtons.getChildren().addAll(button1, buttonl2, buttonl3);
        
        Button instructions = new Button("Instructions");
        
        
        levelButtons.setAlignment(Pos.CENTER);
        levelButtons.setSpacing(10);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setSpacing(30);
        
        mainMenu.getChildren().addAll(label1, levelButtons);
        mainPg.setCenter(mainMenu);
        mainPg.setStyle("-fx-background-color: linear-gradient(to top,  #FFFACD, #DDA0DD)");
        main = new Scene(mainPg, 5*(500/3), 4.25*(400/3));
      
        //Level 1 Page
        String[] colorsStrL1 = new String[]{"1: Red", "2: Green", "3: Orange", "4: Beige", "5: Yellow", "6: Purple"};
        DrawPane level1 = new DrawPane("Level One", "levelOnePic.png", colorsStrL1);
        VBox bottomL1 = new VBox();
        Button backButton1 = new Button("Back");
        backButton1.setOnAction(e -> window.setScene(main));
        
        bottomL1.setPadding(new Insets(12, 20, 20, 20));
        bottomL1.getChildren().add(backButton1);
        level1.setBottom(bottomL1);
        
        scene1 = new Scene(level1, 5*(500/3), 4.25*(400/3));
        
        //Level 2 Page
        String[] colorsStrL2 = new String[]{"1: Red Orange", "2: Yellow", "3: Brown", "4: Green", "5: Blue", "6: Light Blue", "7: Orange", "8: Purple"};
        DrawPane level2 = new DrawPane("Level Two", "levelTwoPic.png", colorsStrL2);
        VBox bottomL2 = new VBox();
        Button backButton2 = new Button("Back");
        backButton2.setOnAction(e -> window.setScene(main));
        
        bottomL2.setPadding(new Insets(12, 20, 20, 20));
        bottomL2.getChildren().add(backButton2);
        level2.setBottom(bottomL2);
        
        scene2 = new Scene(level2, 5*(500/3), 4.25*(400/3));
        
        //Level 3 Page
        String[] colorsStrL3 = new String[]{"1: Pink", "2: Beige", "3: Light Green", "4: Green", "5: Leaf Green", "6: Dark Green", 
        		"7: Yellow", "8: Navy Blue", "9: Blue", "10: Brown", "11: Tan", "12: Light Blue"};
        DrawPane level3 = new DrawPane("Level Three", "levelThreePic.png", colorsStrL3);
        VBox bottomL3 = new VBox();
        Button backButton3 = new Button("Back");
        backButton3.setOnAction(e -> window.setScene(main));
        
        bottomL3.setPadding(new Insets(12, 20, 20, 20));
        bottomL3.getChildren().add(backButton3);
        level3.setBottom(bottomL3);
        
        scene3 = new Scene(level3, 5*(500/3), 4.25*(400/3));
        
        
        window.setScene(main);
        window.setTitle("Paint By Numbers");
        window.show();
    }catch (Exception e){
        	
        }
    }

}