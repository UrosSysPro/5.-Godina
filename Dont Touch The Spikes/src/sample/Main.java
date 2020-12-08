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
<<<<<<< HEAD
        Game game=new Game(300,600);
        game.update();
        game.draw();
        Thread t=new Thread(() -> {
            while (!game.gameOver){
                game.update();
                game.draw();
                try {
                    Thread.sleep(1000/20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Pane pane=new Pane(game.canvas);
        Scene scene=new Scene(pane);

        primaryStage.setTitle("Hello World");
        primaryStage.setOnCloseRequest(windowEvent -> {
            game.gameOver=true;
        });
        primaryStage.setOnShown(windowEvent ->{
            t.start();
        });
=======
        Canvas canvas=new Canvas(600,600);
        Prepreka p=new Prepreka(40,0,30,30);
        p.draw(canvas);
        p=new Prepreka(30,1,30,30);
        p.draw(canvas);
        Pane pane=new Pane(canvas);
        Scene scene=new Scene(pane);

        primaryStage.setTitle("Hello World");

>>>>>>> 347ea68a7d4babf9b24ef34107a7fa539628d601
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
