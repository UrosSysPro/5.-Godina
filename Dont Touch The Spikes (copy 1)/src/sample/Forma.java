package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Forma {
    public Canvas canvas;
    public VBox GameMenu;
    public Button StartGameBtn;
    public StackPane stackPane;
    public AnchorPane anchorPane;

    public VBox infoBox;
    public Label gamesPlayed;
    public Label bestScore;

    public Game game;
    public Thread timer;
    public Timeline timeline;

    public void init(){
        game=new Game(300,600,canvas,anchorPane);

//        timer=new Thread(()->{
//           while (!game.gameOver){
//               game.input();
//               game.update();
//               game.draw();
//               try {
//                   Thread.sleep(1000/60);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//           }
////           game.reset();
//           GameMenu.setVisible(true);
//        });

        timeline=new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/60),actionEvent -> {
            game.input();
            game.update();
            game.draw();
            if(game.gameOver==true){
                timeline.pause();
                infoBox.setVisible(true);
                GameMenu.setVisible(true);
            }
        }));
        game.draw();
    }

    public void buttonOnClick(){
        GameMenu.setVisible(false);
        infoBox.setVisible(false);
        game.restart();
//        timer.start();
        timeline.play();
    }
}
