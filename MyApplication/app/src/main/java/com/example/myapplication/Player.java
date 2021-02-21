package com.example.myapplication;

import android.content.pm.PackageInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player {
    public float x,y,r,ax,ay,vx,vy;
    public Paint boja;
    public Player(float x,float y,float r){
        this.x=x;
        this.y=y;
        this.r=r;
        this.ax=0;
        this.ay=0;
        this.vx=0;
        this.vy=0;
        this.boja=new Paint();
        boja.setStyle(Paint.Style.FILL);
        boja.setColor(Color.BLUE);
    }

    public void update(int sirinaEkrana,int visinaEkrana,Zid[] zidovi){
        vx+=ax;
        vy+=ay;
        x+=vx;
        y+=vy;
        if(x+r>sirinaEkrana){
            x=sirinaEkrana-r;
            ax=0;
            vx=(float) (-vx*0.5);
        }
        if(y+r>visinaEkrana){
            y=visinaEkrana-r;
            ay=0;
            vy=(float) (-vy*0.5);
        }
        if(x-r<0){
            x=r;
            ax=0;
            vx=(float) (-vx*0.5);
        }
        if(y-r<0){
            y=r;
            ay=0;
            vy=(float) (-vy*0.5);
        }
        updateZidoviPravougaonik(zidovi);
    }

    public void updateZidoviPravougaonik(Zid[] zidovi){
        float pdole=y+r,pgore=y-r,pdesno=x+r,plevo=x-r;

        for(int i=0;i<zidovi.length;i++){
            float zdole=zidovi[i].y+zidovi[i].h,zgore=zidovi[i].y;
            float zdesno=zidovi[i].x+zidovi[i].w,zlevo=zidovi[i].x;
            boolean[] sudar=new boolean[4];
            sudar[2]=zdole>pgore&&zdole<pdole&&(!(zlevo>pdesno||zdesno<plevo));
            sudar[3]=zlevo>plevo&&zlevo<pdesno&&(!(zgore>pdole||zdole<pgore));
            sudar[0]=zgore>pgore&&zgore<pdole&&(!(zlevo>pdesno||zdesno<plevo));
            sudar[1]=zdesno>plevo&&zdesno<pdesno&&(!(zgore>pdole||zdole<pgore));
//          slucajevi sa 3 ili 1 preklapanje
            if((sudar[1]&&sudar[2]&&sudar[3])||(sudar[2]&&!sudar[1]&&!sudar[3])){
                y=zdole+r;
                vy=0;
            }
            if((sudar[1]&&sudar[2]&&sudar[0])||(sudar[1]&&!sudar[0]&&!sudar[2])){
                x=zdesno+r;
                vx=0;
            }
            if((sudar[0]&&sudar[1]&&sudar[3])||(sudar[0]&&!sudar[1]&&!sudar[3])){
                y=zgore-r;
                vy=0;
            }
            if((sudar[0]&&sudar[2]&&sudar[3])||(sudar[3]&&!sudar[0]&&!sudar[2])){
                x=zlevo-r;
                vx=0;
            }
//            slucajevi sa dva preklapanja
            if(sudar[2]&&sudar[3]&&!sudar[1]&&!sudar[0]){
                if(pdesno-zlevo>zdole-pgore){
                    y=zdole+r;
                    vy=0;
                }else{
                    x=zlevo-r;
                    vx=0;
                }
            }
            if(sudar[2]&&sudar[1]&&!sudar[3]&&!sudar[0]){
                if(zdole-pgore>zdesno-plevo){
                    x=zdesno+r;
                    vx=0;
                }else{
                    y=zdole+r;
                    vy=0;
                }
            }
            if(sudar[1]&&sudar[0]&&!sudar[3]&&!sudar[2]){
                if(zdesno-plevo>pdole-zgore){
                    y=zgore-r;
                    vy=0;
                }else{
                    x=zdesno+r;
                    vx=0;
                }
            }
            if(sudar[0]&&sudar[3]&&!sudar[1]&&!sudar[2]){
                if(pdesno-zlevo>pdole-zgore){
                    y=zgore-r;
                    vy=0;
                }else{
                    x=zlevo-r;
                    vx=0;
                }
            }
        }
    }

    public void updateZidoviKrug(Zid[] zidovi){

    }

    public void draw(Canvas canvas){
//        canvas.drawCircle(x,y,r,boja);
        canvas.drawRect(x-r,y-r,x+r,y+r,boja);
    }

    public boolean  stigaoDoCilja(Zid cilj){

        if(!(x+r<cilj.x||x>cilj.x+cilj.w)){
            return true;
        }
        if(!(y+r<cilj.y||y>cilj.y+cilj.h)){
            return true;
        }

        return false;
    }
}