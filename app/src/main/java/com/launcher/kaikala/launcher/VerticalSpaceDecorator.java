package com.launcher.kaikala.launcher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by skai0001 on 1/28/17.
 */

public class VerticalSpaceDecorator extends RecyclerView.ItemDecoration {
    private final int vericalSpaceHeight;
    private Drawable divider;


    public VerticalSpaceDecorator(Context context, int resId, int vericalSpaceHeight) {
        this.vericalSpaceHeight = vericalSpaceHeight;
        this.divider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = vericalSpaceHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int childIdx = 0; childIdx < parent.getChildCount(); childIdx++) {
            View item = parent.getChildAt(childIdx);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) item.getLayoutParams();
            int top = item.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            this.divider.setBounds(left, top, right, bottom);
            this.divider.draw(c);
        }
    }
}

