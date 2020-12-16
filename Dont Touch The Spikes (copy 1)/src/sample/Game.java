package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    public int w,h;
    public Canvas canvas;
    public AnchorPane anchorPane;
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

    public Game(int w,int h,Canvas canvas,AnchorPane anchorPane){
        this.anchorPane=anchorPane;
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
        Prepreka.h=40;
        Prepreka.w=25;
        random=new Random();
        lastPressedKey=null;
        gameOver=false;
        this.w=w;
        this.h=h;
        this.canvas=canvas;
        canvas.setWidth(w);
        canvas.setHeight(h);
        smer=1;
        player=new Player(w/2,h/2,30,30,0.1,1.5);
        brojPrepreka=3;
        prepreke=new Prepreka[100];
        rasporediPrepreke();
        bojaPozadine();
    }

    public String ColorUString(){
        String boja="rgb(";
        int r=(int)(foreColor[colorIndex].getRed()*255);
        int g=(int)(foreColor[colorIndex].getGreen()*255);
        int b=(int)(foreColor[colorIndex].getBlue()*255);

        boja+=r+",";
        boja+=g+",";
        boja+=b+")";
        return boja;
    }

    public void bojaPozadine(){
        String stil="-fx-background-color:"+ColorUString()+";";
        anchorPane.setStyle(stil);
    }

    public void restart(){
        gameOver=false;
        colorIndex=0;
        score=0;
        brojPrepreka=3;
        smer=1;
        rasporediPrepreke();
        player.x=w/2;
        player.y=h/2;
        player.ax=1.5;
        player.ay=0;
        bojaPozadine();
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


        drawScore();

        context.setFill(Color.rgb(0,0,0));
        player.draw(canvas);

        context.setFill(foreColor[colorIndex]);
        for(int i=0;i<brojPrepreka;i++){
            prepreke[i].draw(canvas);
        }
        int n=w/Prepreka.h;


        for(int i=0;i<n;i++){
            context.beginPath();
            context.moveTo(i*Prepreka.h,0);
            context.lineTo((i+1)*Prepreka.h,0);
            context.lineTo(i*Prepreka.h+Prepreka.h/2,Prepreka.w);
            context.closePath();
            context.fill();

            context.beginPath();
            context.moveTo(i*Prepreka.h,h);
            context.lineTo((i+1)*Prepreka.h,h);
            context.lineTo(i*Prepreka.h+Prepreka.h/2,h-Prepreka.w);
            context.closePath();
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
                bojaPozadine();
            }
            brojPrepreka++;
            rasporediPrepreke();
        }
        if(player.udaraUPrepreku(prepreke,w,h,brojPrepreka)){
            gameOver=true;
            System.out.println("kraj igre");
        }
    }

    public void drawScore(){
        GraphicsContext context=canvas.getGraphicsContext2D();
        context.setLineWidth(20);
        context.setStroke(foreColor[colorIndex]);
        int r=200;
        context.strokeOval(w/2-r/2,h/2-r/2,r,r);

    }
}
