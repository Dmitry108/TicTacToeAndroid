package ru.bdim.tictactoe.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import ru.bdim.tictactoe.R;

public class CustomBoard extends View {

    private int x;
    private int y;
    private int imgId;
    private int imgX;
    private int imgO;
    private char[][] field;
    private Paint paint;
    private float size;
    private static final int SIZE = 3;
    private boolean isNew;
    private static final String TAG = "...";

    public CustomBoard(Context context) {
        super(context);
        init();
    }

    public CustomBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        paint = new Paint();
        isNew = true;
        imgX = R.drawable.x;
        imgO = R.drawable.o;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int a;
        if (widthMeasureSpec < heightMeasureSpec){
            a = widthMeasureSpec;
            heightMeasureSpec = a;
        } else {
            a = heightMeasureSpec;
            widthMeasureSpec = a;
        }


        Log.d(TAG, "widthMeasureSpec = " + widthMeasureSpec + "heightMeasureSpec = " + heightMeasureSpec + ", a = " + a + ", size = " + size);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isNew){
            Bitmap bm;
            Rect rt;
            int imgId;
            for (int y = 0, h = (getHeight() - getPaddingTop() - getPaddingBottom())/SIZE, top;
                 y < SIZE; y++){
                top = y*h + h/6;
                for (int x = 0, w = (getWidth() - getPaddingStart() - getPaddingEnd())/SIZE, left;
                     x < SIZE; x++){
                    left = x*w + w/6;
                    switch (field[y][x]){
                        case 'X': imgId = imgX; break;
                        case 'O': imgId = imgO; break;
                        default: imgId = -1;
                    }
                    if (imgId != -1) {
                        bm = BitmapFactory.decodeResource(getResources(), imgId);
                        rt = new Rect(left,top,left+(int)(2*w/3),top+(int)(2*h/3));
                        canvas.drawBitmap(bm, null, rt, paint);
                        Log.d(TAG, "Отрисовка хода " + x + " " + y);

                    }
                }
            }
        }
    }

//    public void step(int x, int y, int imgId) {
//        this.x = x;
//        this.y = y;
//        this.imgId = imgId;
//        isNew = false;
//        Log.d(TAG, "x = " + x + ", y = " + y);
//        invalidate();
//    }

    public void clear() {
        isNew = true;
        invalidate();
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void printField(char[][] field) {
        this.field = field;
        if (isNew) isNew = false;

        invalidate();
    }
}
