package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Forma {
    public Canvas canvas;
    public VBox GameMenu;
    public Button StartGameBtn;
    public AnchorPane anchorPane;
    public VBox infoBox;
    public Label gamesPlayedLabel;
    public Label bestScoreLabel;
    public FlowPane flowPane;
    public Image[] pticice;


    public Game game;
    public Timeline timeline;
    public Thread timer;

    public void init() throws FileNotFoundException {
        ucitajPticice();
        game=new Game(300,600,canvas,anchorPane);
        flowPane.setMaxSize(300,600);
        upisiULabel();
        timeline=new Timeline();
        timeline.setCycleCount(-1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/60), actionEvent -> {
            game.input();
            game.update();
            game.draw();
            if(game.gameOver){
                if(game.score>game.bestScore){
                    game.bestScore=game.score;
                }
                game.brojIgara++;
                upisiULabel();
                try {
                    upisiPodatkeUFajl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.reset();
                timeline.pause();
                GameMenu.setVisible(true);
                infoBox.setVisible(true);
            }
        }));
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
        game.draw();
    }
    public void ucitajPticice(){
        int brojPticica=2;
        pticice=new Image[brojPticica];
        for(int i=0;i<brojPticica;i++){
            pticice[i]=new Image("Pticice/slika"+(i+1)+".jpg",150,50,true,false);
        }
        for(int i=0;i<brojPticica;i++){
            ImageView view=new ImageView(pticice[i]);
            StackPane s=new StackPane(view);
            s.setPrefWidth(150);
            s.setPrefHeight(50);
            flowPane.getChildren().add(s);
            view.setOnMouseClicked(mouseEvent -> {
                game.player.slika= ((ImageView)mouseEvent.getTarget()).getImage();
                closeStore();
            });
        }
    }

    public void buttonOnClick(){
        GameMenu.setVisible(false);
        infoBox.setVisible(false);
//        timer.start();
        timeline.play();
    }

    public void upisiULabel(){
        bestScoreLabel.setText("Best Score:"+game.bestScore);
        gamesPlayedLabel.setText("Games Played:"+game.brojIgara);
    }
    public void upisiPodatkeUFajl() throws IOException {
        FileWriter writer =new FileWriter("podaci.txt");
        writer.write(game.bestScore+"\n");
        writer.write(game.brojIgara+"");
        writer.close();
    }

    public void openStore(){
        flowPane.setStyle("-fx-background-color:white");
        flowPane.setVisible(true);
    }
    public void closeStore(){
        flowPane.setVisible(false);
    }
}
