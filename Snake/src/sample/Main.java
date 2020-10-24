package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Game game=new Game(20,20);

        Pane root=new Pane();
        root.getChildren().add(game.canvas);

        Scene scene=new Scene(root);

        scene.setOnKeyPressed(keyEvent -> {
            game.key=keyEvent.getCode();
        });

        Thread thread=new Thread(() -> {
            while (game.gameOver==false){
                game.input();
                game.update();
                game.draw();
                try {
                    Thread.sleep(1000/game.brzina);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        primaryStage.setTitle("Hello World");
        primaryStage.setOnCloseRequest(windowEvent -> game.gameOver=true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
