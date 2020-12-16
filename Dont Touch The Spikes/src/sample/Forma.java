package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Forma {
    public Canvas canvas;
    public VBox GameMenu;
    public Button StartGameBtn;
    public Game game;
    public Thread timer;

    public void init(){
        game=new Game(300,600,canvas);
        timer=new Thread(()->{
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
//           game.reset();
           GameMenu.setVisible(true);
        });
        game.draw();
    }

    public void buttonOnClick(){
        GameMenu.setVisible(false);
        timer.start();
    }
}
