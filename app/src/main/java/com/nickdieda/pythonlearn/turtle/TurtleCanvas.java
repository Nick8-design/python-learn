package com.nickdieda.pythonlearn.turtle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class TurtleCanvas {
    private float x, y;           // Current position
    private float heading;        // Current angle in degrees
    private boolean penDown;      // Is pen down?
    private Path path;            // Stores drawn path
    private Paint paint;          // Drawing style

    public TurtleCanvas(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.heading = 0; // facing right
        this.penDown = true;
        this.path = new Path();
        this.path.moveTo(startX, startY);

        paint = new Paint();
        paint.setColor(0xFF000000); // Black
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void forward(float distance) {
        float newX = (float) (x + distance * Math.cos(Math.toRadians(heading)));
        float newY = (float) (y + distance * Math.sin(Math.toRadians(heading)));
        if (penDown) {
            path.lineTo(newX, newY);
        } else {
            path.moveTo(newX, newY);
        }
        x = newX;
        y = newY;
    }

    public void backward(float distance) {
        forward(-distance);
    }

    public void left(float angle) {
        heading -= angle;
    }

    public void right(float angle) {
        heading += angle;
    }

    public void penUp() {
        penDown = false;
    }

    public void penDown() {
        penDown = true;
    }

    public void goTo(float newX, float newY) {
        x = newX;
        y = newY;
        path.moveTo(newX, newY);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}
