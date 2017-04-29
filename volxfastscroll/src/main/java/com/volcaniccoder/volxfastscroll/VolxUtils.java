package com.volcaniccoder.volxfastscroll;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class VolxUtils {

    private Context mContext;

    public VolxUtils(Context mContext) {
        this.mContext = mContext;
    }

    public int dpToPx(int dp) {
        if (dp <= 0) return ViewGroup.LayoutParams.MATCH_PARENT;
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void changeDrawableColor(View v , int backgroundColor, int strokeColor, int strokeWidthDp){
        GradientDrawable drawable = ((GradientDrawable)v.getBackground());
        drawable.setColor(backgroundColor);
        drawable.setStroke(dpToPx(strokeWidthDp), strokeColor);
    }

}
