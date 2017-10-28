package com.volcaniccoder.volxfastscroll;

class VolxAdapterFeatures {

    private int paramsHeight;
    private float scale;
    private int barWidth;
    private int textSize;
    private int textColor;
    private int activeColor;

    public VolxAdapterFeatures(int paramsHeight, float scale ,int barWidth, int textSize, int textColor, int activeColor) {
        this.paramsHeight = paramsHeight;
        this.scale = scale;
        this.barWidth = barWidth;
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

    public int getBarWidth() {
        return barWidth;
    }

    public float getScale() {
        return scale;
    }
}
