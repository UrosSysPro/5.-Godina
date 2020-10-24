package sample;

import javafx.scene.layout.Pane;

public class Snake {
    public int n;
    public Point[] niz;
    public Point smer;
    public Snake(){
        n=0;
        niz=new Point[50];
        smer=new Point(1,0);
    }

    public void ubaci(int x,int y){
        niz[n]=new Point(x,y);
        n++;
    }

    public void ubaci(){
        niz[n]=new Point(niz[n-1].x,niz[n-1].y);
        n++;
    }

    public boolean proveri(){
        for(int i=1;i<n;i++){
            if(niz[0].x==niz[i].x&&niz[0].y==niz[i].y){
                return true;
            }
        }
        return false;
    }

    public void pomeri(int w,int h){
        for(int i=n-1;i>0;i--){
            niz[i].x=niz[i-1].x;
            niz[i].y=niz[i-1].y;
        }
        niz[0].x+=smer.x;
        niz[0].y+=smer.y;
        if(niz[0].x<0){
            niz[0].x=w-1;
        }
        if(niz[0].x>=w){
            niz[0].x=0;
        }
        if(niz[0].y<0){
            niz[0].y=h-1;
        }
        if(niz[0].y>=h){
            niz[0].y=0;
        }
    }

    public boolean preklapanjeSaHranom(Point hrana){
        for(int i=0;i<n;i++){
            if(niz[i].x==hrana.x&&niz[i].y==hrana.y){
                return true;
            }
        }
        return false;
    }
}
