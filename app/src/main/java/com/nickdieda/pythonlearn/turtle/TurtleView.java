package com.nickdieda.pythonlearn.turtle;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TurtleView extends View {

    private float x, y; // Current position
    private float angle; // Current direction in degrees
    private boolean penDown;
    private Paint paint;
    private Path path;

    public TurtleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        x = 300; // starting X
        y = 300; // starting Y
        angle = 0;
        penDown = true;

        paint = new Paint();
        paint.setColor(0xFF000000); // Black color
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        path = new Path();
        path.moveTo(x, y);
    }

    // Move forward
    public void forward(float distance) {
        float newX = (float) (x + distance * Math.cos(Math.toRadians(angle)));
        float newY = (float) (y + distance * Math.sin(Math.toRadians(angle)));

        if (penDown) {
            path.lineTo(newX, newY);
        } else {
            path.moveTo(newX, newY);
        }

        x = newX;
        y = newY;
        invalidate();
    }

    // Turn left
    public void left(float degrees) {
        angle -= degrees;
    }

    // Turn right
    public void right(float degrees) {
        angle += degrees;
    }

    // Pen up
    public void penUp() {
        penDown = false;
    }

    // Pen down
    public void penDown() {
        penDown = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
