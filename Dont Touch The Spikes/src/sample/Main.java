package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas=new Canvas(600,600);
        Prepreka p=new Prepreka(40,0,30,30);
        p.draw(canvas);
        p=new Prepreka(30,1,30,30);
        p.draw(canvas);
        Pane pane=new Pane(canvas);
        Scene scene=new Scene(pane);

        primaryStage.setTitle("Hello World");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
