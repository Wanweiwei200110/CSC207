package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private int x;
    private int y;
    private int radius;
    private int horspeed;
    private int verspeed;
    private Paint paint;


    public Ball(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        horspeed = 0;
        verspeed = 0;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    public int getRadius(){
        return radius;
    }

    public void setHorspeed(int speed){
        horspeed = speed;
    }

    public void setVerspeed(int speed){
        verspeed = speed;
    }

    public int getHorspeed(){
        return horspeed;
    }

    public int getVerspeed(){
        return verspeed;
    }

    public void update(){
        y = y + verspeed;
        x = y + horspeed;
    }

    public void stop(){
        horspeed = 0;
        verspeed = 0;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x, y, radius, paint);
    }
}

