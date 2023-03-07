package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import ru.pavlenty.surfacegame2.R;

public class Bullet {

    private int x;
    private int y;
    private int speed;
    private boolean visible;

    public Bullet(int startX, int startY) {
        x = startX;
        y = startY;
        speed = 15;
        visible = true;
    }

    public void update() {
        x += speed;
        if (x > GameView.WIDTH) {
            visible = false;
        }
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, 5, paint);
    }

    public boolean isVisible() {
        return visible;
    }

    public Rect getBounds() {
        return new Rect(x-5, y-5, x+5, y+5);
    }
}
