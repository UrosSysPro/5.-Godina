package sample;

<<<<<<< HEAD
import javafx.scene.canvas.Canvas;

public class Game {
    public int w,h;
    public Canvas canvas;
    public Player player;
    public Prepreka[] prepreke;
    public int brojPrepreka;
    public int smer;
    public boolean gameOver;
    public Game(int w,int h){
        gameOver=false;
        this.w=w;
        this.h=h;
        canvas=new Canvas(w,h);
        smer=1;
        player=new Player(w/2,h/2,30,30,0.1,1);
        brojPrepreka=3;
        prepreke=new Prepreka[8];
        for (int i = 0; i < brojPrepreka; i++) {
            prepreke[i]=new Prepreka(i*40,smer,30,30);
        }
    }
    public void draw(){
        player.draw(canvas);
        for(int i=0;i<brojPrepreka;i++){
            prepreke[i].draw(canvas);
        }
    }
    public void update(){
        player.update();
        gameOver=player.udaraUZid(w);
        gameOver=player.udaraUPrepreku(prepreke,w,brojPrepreka);
    }
=======
public class Game {
>>>>>>> 347ea68a7d4babf9b24ef34107a7fa539628d601
}
