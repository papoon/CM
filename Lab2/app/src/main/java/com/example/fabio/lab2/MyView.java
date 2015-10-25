package com.example.fabio.lab2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

    private Paint mPaint = new Paint();
    private Path mPath;
    ArrayList<Path> m_Path_list =  new ArrayList<Path>();
    GestureDetector gestureDetector;

    public MyView(Context context) {
        super(context);
        init(null, 0);

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i< m_Path_list.size(); i++){
            canvas.drawPath(m_Path_list.get(i), mPaint);
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        float eventX = event.getX();
        float eventY = event.getY();



        int maskedAction = event.getActionMasked();


        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:


                int pointerIndex1 = MotionEventCompat.getActionIndex(event);

                mPath = new Path();
                mPath.moveTo(eventX, eventY);

                m_Path_list.add(pointerIndex1, mPath);
                return true;

            case MotionEvent.ACTION_POINTER_DOWN:


                int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                Log.d("Aqui", "Duplo toque" + x + " Y " + y);
                mPath = new Path();
                mPath.moveTo(x, y);
                m_Path_list.add(pointerIndex, mPath);

                return true;

            case MotionEvent.ACTION_MOVE:
                int numPointers = event.getPointerCount();
                int pointerI = 0;
                //int point_move = 0;
                for (int i = 0; i < numPointers; i++) {


                    pointerI = event.getPointerId(i);
                    Log.d("pointer id - move",Integer.toString(pointerI));

                    float x1 = event.getX(i);
                    float y1 = event.getY(i);

                    m_Path_list.get(pointerI).lineTo(x1, y1);

                }

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        List<Integer> color = Arrays.asList(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY);
        int x = 0;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {


            Random r = new Random();
            int mColorId = r.nextInt(color.size());


            mPaint.setColor(color.get(mColorId));

            float x = e.getX();
            float y = e.getY();

            Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");

            return true;
        }
        public void onLongPress(MotionEvent e) {

            float mStrokeWidth = mPaint.getStrokeWidth();
            x = x + 1;
            mPaint.setStrokeWidth(mStrokeWidth+x);
            Log.e("", "Longpress detected");
        }
    }



}
