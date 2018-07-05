package com.example.shang.itemdecorationdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class LinearItemDecortaion extends RecyclerView.ItemDecoration {
    private int divHeight = 20;
    private String TAG = "LinearItemDecortaion";
    private final Paint paint;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(0, 0, 0, divHeight);

    }

    public LinearItemDecortaion(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }



    @Override

    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //super.onDraw(c, parent, state);
        drawVertical(c, parent, state);

    }

    private final Rect mBounds = new Rect();

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            c.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
            Log.d(TAG, "left=="+left+"right==");
        } else {
            left = 0;
            right = parent.getWidth();
            Log.d(TAG, "right=="+right+"left=="+left);
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            int bottoms = child.getBottom();
            int top = bottom - divHeight;
            Log.d(TAG, "getBottom()==" + bottoms + "mBounds.bottom==" + mBounds.bottom + "Math.round(child.getTranslationY()" + bottom);
            c.drawRect(left, top, right, bottom,paint );
        }


    }
}
