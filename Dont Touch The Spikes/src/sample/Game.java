package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    public int w,h;
    public Canvas canvas;
    public Player player;
    public Prepreka[] prepreke;
    public int brojPrepreka;
    public int smer;
    public boolean gameOver;
    public KeyCode lastPressedKey;
    public Random random;
    public int score;
    public Color[] foreColor;
    public Color[] backColor;
    public int colorIndex;

    public Game(int w,int h){
        foreColor=new Color[]{
                Color.rgb(30,30,30),
                Color.rgb(150,0,200)
        };
        backColor=new Color[]{
                Color.rgb(200,200,200),
                Color.rgb(255,255,255)
        };

        colorIndex=0;
        score=0;
        Prepreka.h=30;
        Prepreka.w=30;
        random=new Random();
        lastPressedKey=null;
        gameOver=false;
        this.w=w;
        this.h=h;
        canvas=new Canvas(w,h);
        smer=1;
        player=new Player(w/2,h/2,30,30,0.1,3);
        brojPrepreka=3;
        prepreke=new Prepreka[100];
        rasporediPrepreke();
    }

    public void rasporediPrepreke(){
        for (int i = 0; i < brojPrepreka; i++) {
            int n=h/Prepreka.h;
            int r=random.nextInt(n);
            prepreke[i]=new Prepreka(r*Prepreka.h,smer);
        }
    }

    public void input(){
        if(lastPressedKey==KeyCode.SPACE){
            lastPressedKey=null;
            player.ay=-3;
        }
    }

    public void draw(){
        GraphicsContext context=canvas.getGraphicsContext2D();
//        context.clearRect(0,0,w,h);
        context.setFill(backColor[colorIndex]);
        context.fillRect(0,0,w,h);

        context.setFill(Color.rgb(0,0,0));
        player.draw(canvas);

        context.setFill(foreColor[colorIndex]);
        for(int i=0;i<brojPrepreka;i++){
            prepreke[i].draw(canvas);
        }
        int n=w/Prepreka.w;

        for(int i=0;i<n;i++){
            context.moveTo(i*Prepreka.w,0);
            context.lineTo((i+1)*Prepreka.w,0);
            context.lineTo(i*Prepreka.w+Prepreka.w/2,Prepreka.h);
            context.fill();

            context.moveTo(i*Prepreka.w,h);
            context.lineTo((i+1)*Prepreka.w,h);
            context.lineTo(i*Prepreka.w+Prepreka.w/2,h-Prepreka.h);
            context.fill();
        }
    }

    public void update(){
        player.update();
        if(player.udaraUZid(w)){
            smer=smer==0?1:0;
            player.ax=-player.ax;
            score++;
            if(score%10==0){
                colorIndex++;
            }
            brojPrepreka++;
            rasporediPrepreke();
        }
        if(player.udaraUPrepreku(prepreke,w,brojPrepreka)){
            gameOver=true;
            System.out.println("kraj igre");
        }
    }
}
