package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.Serializable;
import java.lang.reflect.Array;

public class Bat implements Serializable {

    private int width;
    private Paint paint;
    private Rect rect;
    private int x;
    private int y;

    public Bat(int x, int y, int left, int top, int right, int bottom) {
        this.x = x;
        this.y = y;
        this.width = right - left;
        rect = new Rect(left, top, right, bottom);
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
    }

    public int[] getRectBody() {
        int[] body = {rect.left, rect.top, rect.right, rect.bottom};
        return body;
    }


    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }
}
