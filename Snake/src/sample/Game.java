package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Game {
    public Snake snake;
    public int w,h;
    public int sirinaPolja;
    public Canvas canvas;
    public GraphicsContext context;
    public KeyCode key;
    public boolean gameOver;
    public Point hrana;
    public Random random;
    public int brzina;

    public Game(int w,int h){
        snake=new Snake();
        snake.ubaci(w/2,h/2);
        for(int i=0;i<10;i++)snake.ubaci();
        this.w=w;
        this.h=h;
        sirinaPolja=30;
        canvas=new Canvas(w*sirinaPolja,h*sirinaPolja);
        context=canvas.getGraphicsContext2D();
        gameOver=false;
        key=KeyCode.D;
        random=new Random();
        hrana=new Point(random.nextInt(w),random.nextInt(h));
        brzina=10;
    }

    public void draw(){
        context.clearRect(0,0,w*sirinaPolja,h*sirinaPolja);
        for(int i=0;i<snake.n;i++){
            int x=snake.niz[i].x*sirinaPolja;
            int y=snake.niz[i].y*sirinaPolja;
            context.fillRect(x,y,sirinaPolja,sirinaPolja);
        }
        context.setFill(Paint.valueOf("#ff0000"));
        context.fillRect(hrana.x*sirinaPolja,hrana.y*sirinaPolja,sirinaPolja,sirinaPolja);
        context.setFill(Paint.valueOf("#000000"));
    }

    public void update(){
        snake.pomeri(w,h);
        gameOver=snake.proveri();
        if(snake.niz[0].x==hrana.x&&snake.niz[0].y==hrana.y){
            snake.ubaci();
            brzina++;
            hrana=new Point(random.nextInt(w),random.nextInt(h));
            while (snake.preklapanjeSaHranom(hrana)){
                hrana=new Point(random.nextInt(w),random.nextInt(h));
            }
        }
    }

    public void input(){
        switch (key){
            case D:{
                if(snake.smer.x!=-1)
                   snake.smer = new Point(1,0);
            }break;
            case A:{
                if(snake.smer.x!=1)
                    snake.smer=new Point(-1,0);
            }break;
            case W:{
                if(snake.smer.y!=1)
                    snake.smer=new Point(0,-1);
            }break;
            case S:{
                if(snake.smer.y!=-1)
                    snake.smer=new Point(0,1);
            }break;
            case SPACE:{
                if(snake.n<40)
                snake.ubaci();
            };break;
        }
    }
}
