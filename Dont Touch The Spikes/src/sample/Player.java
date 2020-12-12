package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Player {
        public double x,y,g,ax,ay;
        public int w,h;
        public Player(double x,double y, int w,int h,double g,double ax){
            this.x=x;
            this.y=y;
            this.g=g;
            this.w=w;
            this.h=h;
            this.ay=0;
            this.ax=ax;
        }
        public void update(){
            ay+=g;
            x+=ax;
            y+=ay;
        }
        public boolean udaraUZid(int sirinaEkrana){
            if(x<0) return true;
            if(x+w>sirinaEkrana)return true;
            return false;
        }
        public boolean udaraUPrepreku(Prepreka[] prepreke,int sirinaEkrana,int brojPrepreka){
            for(int i=0;i<brojPrepreka;i++){
                int tx,ty,tw,th;
                if(prepreke[i].strana==0) {
                    tx = 0;
                }else{
                    tx=sirinaEkrana-prepreke[i].w;
                }
                ty=prepreke[i].y;
                tw=prepreke[i].w;
                th=prepreke[i].h;
                boolean udaraUzid=true;
                if((x+w<tx))udaraUzid= false;
                if((ty+th<y))udaraUzid= false;
                if((y+h<ty))udaraUzid=  false;
                if((tx+tw<x))udaraUzid=false;
                if(udaraUzid)return true;
            }
            return false;
        }
        public  void draw(Canvas canvas){
            GraphicsContext context=canvas.getGraphicsContext2D();
            context.fillRect(x,y,w,h);
//            context.rect(x,y,w,h);
//            context.fill();
        }
}
