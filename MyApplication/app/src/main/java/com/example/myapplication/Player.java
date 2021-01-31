package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player {
    public float x,y,r;
    public float ax,ay;
    public float vx,vy;
    Paint boja;
    public Player(int x,int y,int r){
        ax=0;
        ay=0;
        vx=0;
        vy=0;
        this.x=x;
        this.y=y;
        this.r=r;
        boja=new Paint();
        boja.setColor(Color.BLUE);
        boja.setStyle(Paint.Style.FILL);
    }

    public void update(int sirinaEkrana,int visinaEkrana,Zid[] zidovi){
        vx+=ax;
        vy+=ay;
        x+=vx;
        y+=vy;
        if(x+r>sirinaEkrana){
            x=sirinaEkrana-r;
            vx=0;
        }
        if(y+r>visinaEkrana){
            y=visinaEkrana-r;
            vy=0;
        }
        if(x-r<0){
            x=r;
            vx=0;
        }
        if(y-r<0){
            y=r;
            vy=0;
        }
        udaraUZidove(zidovi);
    }

    public void udaraUZidove(Zid[] zidovi){
        for(int i=0;i<zidovi.length;i++){
            float zlevo=zidovi[i].x,zdesno=zidovi[i].x+zidovi[i].w;
            float zgore=zidovi[i].y,zdole=zidovi[i].y+zidovi[i].h;
            float plevo=x-r,pdesno=x+r,pgore=y-r,pdole=y+r;

            if((plevo>zdesno||pdesno<zlevo||pgore>zdole||pdole<zgore)){
                continue;
            }

            boolean[] preklapanje=new boolean[]{false,false,false,false};
            if(plevo>zlevo&&plevo<zdesno)preklapanje[3]=true;
            if(pdesno>zlevo&&pdesno<zdesno)preklapanje[1]=true;
            if(pgore>zgore&&pgore<zdole)preklapanje[0]=true;
            if(pdole>zgore&&pdole<zdole)preklapanje[2]=true;

            if(preklapanje[0]==true&&preklapanje[1]==true&&preklapanje[3]==true){
                y=zdole+r;
                vy=0;
            }
            if(preklapanje[0]==true&&preklapanje[1]==true&&preklapanje[2]==true){
                x=zlevo-r;
                vx=0;
            }
            if(preklapanje[2]==true&&preklapanje[1]==true&&preklapanje[3]==true){
                y=zgore-r;
                vy=0;
            }
            if(preklapanje[0]==true&&preklapanje[2]==true&&preklapanje[3]==true){
                x=zdesno+r;
                vx=0;
            }
//            if(plevo>zlevo&&plevo<zdesno){
//                x=zdesno+r;
//                vx=0;
//            }
//            if(pdesno>zlevo&&pdesno<zdesno){
//                x=zlevo-r;
//                vx=0;
//            }
//            if(pgore>zgore&&pgore<zdole){
//                y=zdole+r;
//                vy=0;
//            }
//            if(pdole>zgore&&pdole<zdole){
//                y=zgore-r;
//                vy=0;
//            }
        }
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,r,boja);
//        canvas.drawText(ax+" "+ay,150,150,boja);
    }
}
