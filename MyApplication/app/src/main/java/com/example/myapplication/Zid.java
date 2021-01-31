package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Zid {
    public int x,y,w,h;
    public static Paint boja;
    public Zid(int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        boja=new Paint();
        boja.setColor(Color.BLUE);
        boja.setStyle(Paint.Style.FILL);
    }
    public void draw(Canvas canvas){
        canvas.drawRect(x,y,x+w,y+h,boja);
    }
}
