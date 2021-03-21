package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlockApp extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Flock flock=new Flock(500,500);
        Pane pane=new Pane(flock.tree.canvas);

        flock.tree.canvas.setOnMouseClicked(mouseEvent -> {
            flock.add(mouseEvent.getX(),mouseEvent.getY());
//            flock.update();
        });

        Timeline timer=new Timeline(new KeyFrame(Duration.millis(1000/60),(actionEvent)->{
            flock.update();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
