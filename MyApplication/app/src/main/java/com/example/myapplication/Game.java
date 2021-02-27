package com.example.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.util.Scanner;


public class Game extends View {
    public int w,h;
    public Player player;
    public Zid[] zidovi;
    public SensorManager sensorManager;
    public Sensor sensor;
    public SensorEventListener eventListener;
    public ValueAnimator timer;
    public long staroVreme;
    public Level[] levels;
    public int trenutniNivo;

    public Zid cilj;

    public Button playBtn;
    public Button optionsBtn;
    public LinearLayout gameMenu;

    public Game(Context context){
        super(context);
        DisplayMetrics displayMetrics=new DisplayMetrics();

        ((Activity) getContext())
                .getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        w=displayMetrics.widthPixels;
        h=displayMetrics.heightPixels;

        staroVreme=0;

        player=new Player(w/2,h/2,25);

        Zid.boja=new Paint();
        Zid.boja.setColor(Color.BLUE);
        Zid.boja.setStyle(Paint.Style.FILL);
        trenutniNivo=0;
        zidovi=ucitajZidove();

        cilj=new Zid(700,1000,50,50);

        sensorManager=(SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        eventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                player.ax=-event.values[0]/10;
                player.ay=event.values[1]/10;
//                update();
//                invalidate();
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(eventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        timer=ValueAnimator.ofInt(0,100);
        timer.setDuration(1000);
        timer.setRepeatCount(ValueAnimator.INFINITE);
        timer.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                long vreme=System.currentTimeMillis();
                if(vreme-staroVreme>1000/60){
                    staroVreme=vreme;
                    update();
                    invalidate();
                }
            }
        });
    }
    public Zid[] ucitajZidove(){
        Zid[] z=new Zid[7];
        z[0]=new Zid(1*w/7,1*h/8,1*w/7,2*h/8);
        z[1]=new Zid(2*w/7,2*h/8,1*w/7,1*h/8);
        z[2]=new Zid(3*w/7,1*h/8,1*w/7,2*h/8);
        z[3]=new Zid(5*w/7,1*h/8,1*w/7,4*h/8);
        z[4]=new Zid(1*w/7,4*h/8,4*w/7,1*h/8);
        z[5]=new Zid(1*w/7,6*h/8,2*w/7,1*h/8);
        z[6]=new Zid(4*w/7,6*h/8,2*w/7,1*h/8);
        return z;
    }
//    public Zid[] ucitajZidove(int i){
//        return  levels[i];
//    }
    public void ucitajNivoe(Context context){
        try {
            for(int i=0;i<5;i++){
                File file=new File(context.getFilesDir(),"level"+i+".txt");
                Scanner scanner=new Scanner(file);
                String[] red= scanner.nextLine().split(" ");
                player.x=Integer.parseInt(red[0]);
                player.y=Integer.parseInt(red[1]);
                red=scanner.nextLine().split(" ");
                int x=Integer.parseInt(red[0]);
                int y=Integer.parseInt(red[1]);
                int w=Integer.parseInt(red[2]);
                int h=Integer.parseInt(red[3]);
                Zid cilj=new Zid(x,y,w,h);
                while(scanner.hasNextLine()){
                    x=Integer.parseInt(red[0]);
                    y=Integer.parseInt(red[1]);
                    w=Integer.parseInt(red[2]);
                    h=Integer.parseInt(red[3]);

                }
            }
        }catch (Exception e) {

        }
    }
    public void update(){
        player.update(w,h,zidovi);
        if(player.stigaoDoCilja(cilj)){
            //resetujemo poziciju igraca

            //ucita se novi nivo
//            trenutniNivo++;
//            zidovi=ucitajZidove(trenutniNivo);
//            cilj=new Zid();

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.draw(canvas);
        for(int i=0;i<zidovi.length;i++){
            zidovi[i].draw(canvas);
        }
        Zid.boja.setColor(Color.rgb(200,150,150));
        canvas.drawRect(cilj.x,cilj.y,cilj.x+cilj.w,cilj.y+cilj.h,Zid.boja);
        Zid.boja.setColor(Color.BLACK);
    }
}