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
        Game game=new Game(300,600,new Canvas());
        game.update();
        game.draw();
        Thread t=new Thread(() -> {
            while (!game.gameOver){
                game.input();
                game.update();
                game.draw();
                try {
                    Thread.sleep(1000/60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        primaryStage.setTitle("Hello World");
        primaryStage.setOnCloseRequest(windowEvent -> {
            game.gameOver=true;
        });
        primaryStage.setOnShown(windowEvent ->{
            t.start();
        });
        Pane pane=new Pane(game.canvas);
        Scene scene=new Scene(pane);
        scene.setOnKeyPressed(keyEvent -> {
            game.lastPressedKey=keyEvent.getCode();
        });

        primaryStage.setTitle("Hello World");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
