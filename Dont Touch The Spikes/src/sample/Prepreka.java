package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Prepreka {
    public int y;
    public int strana;
    public static int w,h;
    public Prepreka(int y,int strana){
        this.y=y;
        this.strana=strana;
    }
    public void draw(Canvas canvas){
        GraphicsContext context=canvas.getGraphicsContext2D();
        if(strana==0){
            context.beginPath();
            context.moveTo(0,y);
            context.lineTo(0,y+h);
            context.lineTo(w,y+(double)h/2);
            context.closePath();
            context.fill();
        }else{
            context.beginPath();
            context.moveTo(canvas.getWidth(),y);
            context.lineTo(canvas.getWidth(),y+h);
            context.lineTo(canvas.getWidth()-w,y+h/2);
            context.closePath();
            context.fill();
        }

    }
}
