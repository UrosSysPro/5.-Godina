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
        polje p=new polje("tekst.txt");
        int w,h,sirina=30;
        w=p.w;
        h=p.h;
        Canvas canvas=new Canvas(w*sirina,h*sirina);
        p.start(0,0);
        p.nacrtaj(canvas,sirina);
        p.ispis();
        Pane pane =new Pane(canvas);
        Scene scene=new Scene(pane);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
