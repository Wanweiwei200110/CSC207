package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Ball ball;
    private Bat bat;

    public GameView(Context context) {
        super(context);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        getHolder().addCallback(this);
        int screen_x = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screen_y = Resources.getSystem().getDisplayMetrics().heightPixels;
        int center = screen_x / 2;
        int halfBlockWidth = screen_x / 10;
        int blockHeight = screen_y / 20;

        bat = new Bat(center, screen_y - (blockHeight / 2), center - halfBlockWidth,
                screen_y - blockHeight,
                center + halfBlockWidth, screen_y);
        ball = new Ball(center, screen_y - blockHeight - (screen_y / 50), screen_y / 50);
    }@Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void update() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));

            bat.draw(canvas);
            ball.draw(canvas);
        }
    }
}
