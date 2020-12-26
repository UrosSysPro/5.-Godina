package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

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
    public int bestScore;
    public int brojIgara;

    public Game(int w,int h,Canvas canvas,AnchorPane anchorPane) throws FileNotFoundException {
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
        this.canvas=canvas;
        this.anchorPane=anchorPane;
        canvas.setWidth(w);
        canvas.setHeight(h);
        smer=1;
        player=new Player(w/2,h/2,30,30,0.1,3);
        brojPrepreka=3;
        prepreke=new Prepreka[100];
        rasporediPrepreke();
        promeniBojuPozadine();
        ucitajPodatke();
    }

    public void ucitajPodatke() throws FileNotFoundException {
        File file=new File("podaci.txt");
        Scanner scanner=new Scanner(file);
        bestScore=scanner.nextInt();
        brojIgara=scanner.nextInt();
        scanner.close();
    }

    public void nacrtajRezultat(){
        GraphicsContext context=canvas.getGraphicsContext2D();
        int r=200;
        int x=w/2-r/2;
        int y=h/2-r/2;
        context.fillOval(x,y,r,r);

        context.setFill(backColor[colorIndex]);
        int velicinaTeksta=90;
        context.setFont(new Font("Droid sans",velicinaTeksta));
        context.fillText(score/10+""+score%10,w/2-velicinaTeksta/2,h/2+velicinaTeksta/3);
    }

    public void promeniBojuPozadine(){
//        System.out.println(foreColor[colorIndex].toString());
        String boja="rgb(";
        boja+=foreColor[colorIndex].getRed()*255+",";
        boja+=foreColor[colorIndex].getGreen()*255+",";
        boja+=foreColor[colorIndex].getBlue()*255+")";
        anchorPane.setStyle("-fx-background-color:"+boja);
    }

    public void reset(){
        colorIndex=0;
        promeniBojuPozadine();
        score=0;
        gameOver=false;
        lastPressedKey=null;
        smer=1;
        player=new Player(w/2,h/2,30,30,0.1,3);
        brojPrepreka=3;
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

        context.setFill(Color.rgb(255,255,255));
        nacrtajRezultat();

        context.setFill(Color.rgb(0,0,0));
        player.draw(canvas);

        context.setFill(foreColor[colorIndex]);
        for(int i=0;i<brojPrepreka;i++){
            prepreke[i].draw(canvas);
        }
        int n=w/Prepreka.w;

        for(int i=0;i<n;i++){
            context.beginPath();
            context.moveTo(i*Prepreka.w,0);
            context.lineTo((i+1)*Prepreka.w,0);
            context.lineTo(i*Prepreka.w+Prepreka.w/2,Prepreka.h);
            context.closePath();
            context.fill();

            context.beginPath();
            context.moveTo(i*Prepreka.w,h);
            context.lineTo((i+1)*Prepreka.w,h);
            context.lineTo(i*Prepreka.w+Prepreka.w/2,h-Prepreka.h);
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
                promeniBojuPozadine();
            }
            brojPrepreka++;
            rasporediPrepreke();
        }
        if(player.udaraUPrepreku(prepreke,w,h,brojPrepreka)){
            gameOver=true;
            System.out.println("kraj igre");
        }
    }
}
