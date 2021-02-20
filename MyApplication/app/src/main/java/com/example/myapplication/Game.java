package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class Game extends View {
    public int w,h;
    public Player player;
    public Zid[] zidovi;
    public SensorManager sensorManager;
    public Sensor sensor;
    public SensorEventListener eventListener;
    public ValueAnimator timer;
    public long staroVreme;

    public Button playBtn;
    public Button optionsBtn;
    public LinearLayout gameMenu;

    public Game(Context context){
        super(context);
        w=720;
        h=1000;

        staroVreme=0;

        player=new Player(w/2,h/2,25);

        Zid.boja=new Paint();
        Zid.boja.setColor(Color.BLUE);
        Zid.boja.setStyle(Paint.Style.FILL);
        zidovi=ucitajZidove();


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
    public void update(){
        player.update(w,h,zidovi);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.draw(canvas);
        for(int i=0;i<zidovi.length;i++){
            zidovi[i].draw(canvas);
        }
    }
}