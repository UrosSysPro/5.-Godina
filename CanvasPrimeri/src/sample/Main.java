package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas=new Canvas(500,500);
        GraphicsContext context=canvas.getGraphicsContext2D();


        context.setFill(new Color(3.0/255, 17.0/255, 252.0/255,1));
        kvadrat(100,100,200,100,context);


        context.setFill(new Color(242.0/255, 66.0/255, 27.0/255,1));
        krug(150,350,100,context);


        context.setStroke(Color.rgb(240,40,15));
        context.setLineWidth(5);
        linija(context);



        Pane pane=new Pane(canvas);
        Scene scene=new Scene(pane);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void kvadrat(int x,int y,int w,int h,GraphicsContext context){
        context.fillRect(x,y,w,h);
    }
    public void krug(int x,int y,int r,GraphicsContext context){
        context.fillOval(x-r/2,y-r/2,r,r);
    }
    public void linija(GraphicsContext context){
        context.beginPath();
        context.moveTo(300,300);
        context.lineTo(300,350);
        context.lineTo(350,350);
        context.closePath();

        context.stroke();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
