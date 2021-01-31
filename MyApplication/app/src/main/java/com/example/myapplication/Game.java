package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

public class Game extends View {
    public Player player;
    public Zid[] zidovi;
    public int w,h;
    public ValueAnimator timer;

    public Game(Context context){
        super(context);

        w=700;
        h=1000;
        player=new Player(w/2,h/2,50);
        zidovi=new Zid[1];
        zidovi[0]=new Zid(100,0,200,250);

        SensorManager sensorManager= (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener listener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                player.ax=-sensorEvent.values[0]/10;
                player.ay=sensorEvent.values[1]/10;
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        timer=ValueAnimator.ofInt(0,100);
        timer.setRepeatCount(ValueAnimator.INFINITE);
        timer.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                update();
                invalidate();
            }
        });
        timer.start();
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
