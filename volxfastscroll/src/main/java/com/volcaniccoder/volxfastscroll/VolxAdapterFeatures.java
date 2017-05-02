package com.volcaniccoder.volxfastscroll;

public class VolxAdapterFeatures {

    private int paramsHeight;
    private int paramsHeightDp;
    private int textSize;
    private int textColor;
    private int activeColor;

    public VolxAdapterFeatures(int paramsHeight, int paramsHeightDp, int textSize, int textColor, int activeColor) {
        this.paramsHeight = paramsHeight;
        this.paramsHeightDp = paramsHeightDp;
        this.textSize = textSize;
        this.textColor = textColor;
        this.activeColor = activeColor;
    }

    public int getParamsHeight() {
        return paramsHeight;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public int getParamsHeightDp() {
        return paramsHeightDp;
    }
}
