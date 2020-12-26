package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
        public double x,y,g,ax,ay;
        public int w,h;

        public Image slika;
        public Tacka[] niz;
        public int frameCounter;

        public Player(double x,double y, int w,int h,double g,double ax){
            frameCounter=0;
            this.x=x;
            this.y=y;
            this.g=g;
            this.w=w;
            this.h=h;
            this.ay=0;
            this.ax=ax;
            slika=new Image("slika.jpg");
            niz=new Tacka[4];
            for(int i=0;i<niz.length;i++){
                niz[i]=new Tacka((int)x,(int)y,10);
            }
        }
        public void update(){
            ay+=g;
            x+=ax;
            y+=ay;

            if(frameCounter>=5) {
                for (int i = niz.length - 1; i >= 1; i--) {
                    niz[i].x = niz[i - 1].x;
                    niz[i].y = niz[i - 1].y;
                    niz[i].r = 10-i;
                }
                niz[0].x = (int) x;
                niz[0].y = (int) y;
                niz[0].r = 10;
                frameCounter=0;
            }else{
                for(int i=0;i<niz.length;i++){
//                    niz[i].r--;
                }
            }

            frameCounter++;
        }

        public boolean udaraUZid(int sirinaEkrana){
            if(x<0) return true;
            if(x+w>sirinaEkrana)return true;
            return false;
        }

        public boolean udaraUPrepreku(Prepreka[] prepreke,int sirinaEkrana,int visinaEkrana,int brojPrepreka){
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

            if(y<Prepreka.h||y+h>visinaEkrana-Prepreka.h)return true;

            return false;
        }

        public  void draw(Canvas canvas){
            GraphicsContext context=canvas.getGraphicsContext2D();
//            context.fillRect(x,y,w,h);
            context.drawImage(slika,x,y,w,h);

            if(ax>0){
                for(int i=0;i<niz.length;i++){
                    context.fillOval(niz[i].x-niz[i].r/2,niz[i].y+h-niz[i].r/2,niz[i].r,niz[i].r);
                }
            }else {
                for (int i = 0; i < niz.length; i++) {
                    context.fillOval(niz[i].x+w-niz[i].r/2, niz[i].y+h-niz[i].r/2, niz[i].r, niz[i].r);
                }
            }
        }
}
