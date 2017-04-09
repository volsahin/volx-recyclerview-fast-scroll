package com.volcaniccoder.volxfastscroll;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;

public class VolxUtils {

    private Context mContext;

    public VolxUtils(Context mContext) {
        this.mContext = mContext;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void changeDrawableColor(View v , int color){
        ((GradientDrawable)v.getBackground()).setColor(color);
    }
}
