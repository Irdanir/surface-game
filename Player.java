package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;
    private boolean boosting;
    private boolean isShooting; // new variable
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect detectCollision;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;
        isShooting = false; // initialize to false

        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void update() {
        if (boosting) {
            speed += 2;
        } else {
            speed -= 5;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + GRAVITY;
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

        // Shooting logic
        if (isShooting && System.currentTimeMillis() - lastShootTime > SHOOT_DELAY) {
            Bullet bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullet));
            bullet.setX(x + bitmap.getWidth() / 2);
            bullet.setY(y - bitmap.getHeight() / 2);
            bullets.add(bullet);
            lastShootTime = System.currentTimeMillis();
        }

        // Update bullets
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }


    public void shoot() {
        Bullet bullet = new Bullet(x + width, y + height/2);
        bullets.add(bullet);
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public int getWidth() {
        return bitmap.getWidth();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}