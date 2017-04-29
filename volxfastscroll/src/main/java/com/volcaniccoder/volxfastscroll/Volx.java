package com.volcaniccoder.volxfastscroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.VISIBLE;

public class Volx implements Runnable {

    private Context context;

    private int activeColor;

    private int backgroundColor;

    private int textSize;

    private int textColor;

    private int barWidth; //dp

    private int barHeight; //dp

    private int middleTextSize;

    private int middleLayoutSize; //dp

    private int middleBackgroundColor;

    private int middleStrokeColor;

    private int rightStrokeColor;

    private int middleTextColor;

    private int middleStrokeWidth; //dp

    private int rightStrokeWidth; //dp

    private int delayMillis;

    private int minItem;

    private FrameLayout parentLayout;

    private RecyclerView userRecyclerView; //adapter must be IVolxAdapter

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private TextView middleText;

    private List<String> allStringList = new ArrayList<>();
    private List<Character> charArr = new ArrayList<>();
    private List<VolxCharModel> charList = new ArrayList<>();

    private int lastPos = -1;
    private int blinkCount = 0;
    private boolean isTouched = true;

    private int itemHeight;

    private List<Integer> positionList = new ArrayList<>();

    private VolxUtils utils;

    private FrameLayout rightIndicatorLayout;

    public Volx(Builder builder) {
        this.activeColor = builder.activeColor;
        this.backgroundColor = builder.backgroundColor;
        this.textSize = builder.textSize;
        this.textColor = builder.textColor;
        this.barWidth = builder.barWidth;
        this.barHeight = builder.barHeight;
        this.middleTextSize = builder.middleTextSize;
        this.middleLayoutSize = builder.middleLayoutSize;
        this.middleBackgroundColor = builder.middleBackgroundColor;
        this.middleStrokeColor = builder.middleStrokeColor;
        this.rightStrokeColor = builder.rightStrokeColor;
        this.middleTextColor = builder.middleTextColor;
        this.rightStrokeWidth = builder.rightStrokeWidth;
        this.middleStrokeWidth = builder.middleStrokeWidth;
        this.delayMillis = builder.delayMillis;
        this.minItem = builder.minItem;
        this.parentLayout = builder.parentLayout;
        this.userRecyclerView = builder.userRecyclerView;
        this.execute();
    }

    public void execute() {

        context = parentLayout.getContext();
        utils = new VolxUtils(context);

        if (!(userRecyclerView.getAdapter() instanceof IVolxAdapter)) {
            Toast.makeText(context, "Please implement IVolxAdapter in your own adapter", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Object> objectList = ((IVolxAdapter) userRecyclerView.getAdapter()).getList();

        if (objectList == null || objectList.isEmpty())
            return;

        Class foo = objectList.get(0).getClass();
        int counter = -1;

        for (Object obj : objectList) {

            counter++;

            for (Field field : foo.getDeclaredFields()) {

                ValueArea annotation = field.getAnnotation(ValueArea.class);

                if (annotation != null) {

                    field.setAccessible(true);

                    try {
                        allStringList.add(field.get(obj).toString());
                        Character c = field.get(obj).toString().charAt(0);

                        if (!charArr.contains(c)) {
                            charArr.add(c);
                            charList.add(new VolxCharModel(c, false));
                            positionList.add(counter);
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
        }

        if (minItem > 0 && charList.size() < minItem)
            return;

        initViews();

        removeViewsWithDelay();

        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScreenCreated(parentLayout.getMeasuredHeight(), this);
            }
        });

        userRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == SCROLL_STATE_DRAGGING && !isTouched) {
                    isTouched = true;
                    rightIndicatorLayout.setVisibility(View.VISIBLE);
                    removeViewsWithDelay();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int posTop = ((LinearLayoutManager) (userRecyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                middleText.setText("" + allStringList.get(posTop).toUpperCase().charAt(0));
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (middleText.getVisibility() != VISIBLE)
                    middleText.setVisibility(View.VISIBLE);

                if (newState == SCROLL_STATE_IDLE)
                    rightIndicatorLayout.setVisibility(View.VISIBLE);

            }

        });

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int y = (int) event.getY();

                if (rightIndicatorLayout.getVisibility() == View.GONE)
                    setViewsVisibility(true);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        middleText.setVisibility(VISIBLE);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        blinkCount = y / itemHeight;

                        if (blinkCount > -1 && blinkCount != lastPos && blinkCount < charList.size()) {

                            ((VolxAdapter) mAdapter).clearBlinks();
                            VolxCharModel changeModel = ((VolxAdapter) mAdapter).getCharListModelAt(blinkCount);
                            changeModel.setBlink(true);
                            mAdapter.notifyDataSetChanged();

                            ((LinearLayoutManager) (userRecyclerView.getLayoutManager())).scrollToPositionWithOffset(positionList.get(blinkCount).intValue(), 0);
                            middleText.setText(changeModel.getCharacter().toString().toUpperCase());

                            lastPos = blinkCount;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        if (rightIndicatorLayout.getVisibility() == View.VISIBLE) {
                            ((VolxAdapter) mAdapter).clearBlinks();
                            mAdapter.notifyDataSetChanged();
                            lastPos = -1;
                            setViewsVisibility(false);
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void removeViewsWithDelay() {
        mRecyclerView.postDelayed(Volx.this, delayMillis);
    }

    private void setViewsVisibility(boolean isShow) {

        if (!isShow) {
            rightIndicatorLayout.setVisibility(View.GONE);
            middleText.setVisibility(View.GONE);
            isTouched = false;
            return;
        }

        rightIndicatorLayout.setVisibility(View.VISIBLE);
        middleText.setVisibility(View.VISIBLE);
    }

    private void initViews() {

        // Adding middle circle text to the center of parent layout

        FrameLayout.LayoutParams textParams = new FrameLayout.LayoutParams(utils.dpToPx(middleLayoutSize), utils.dpToPx(middleLayoutSize));
        textParams.gravity = Gravity.CENTER;

        middleText = new TextView(context);
        middleText.setTextColor(middleTextColor);
        middleText.setTextSize(middleTextSize);
        middleText.setBackgroundResource(R.drawable.middle_text_bg);
        middleText.setGravity(Gravity.CENTER);
        middleText.setVisibility(View.GONE);
        utils.changeDrawableColor(middleText, middleBackgroundColor, middleStrokeColor, middleStrokeWidth);

        parentLayout.addView(middleText, textParams);

        //Creating right side layout and adding it to the right side of parent layout

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(utils.dpToPx(barWidth), utils.dpToPx(barHeight));
        params.gravity = Gravity.CENTER | Gravity.RIGHT;
        params.setMargins(utils.dpToPx(2), utils.dpToPx(4), utils.dpToPx(2), utils.dpToPx(4));

        rightIndicatorLayout = new FrameLayout(context);
        rightIndicatorLayout.setBackgroundResource(R.drawable.layout_shape);
        utils.changeDrawableColor(rightIndicatorLayout, backgroundColor, rightStrokeColor, rightStrokeWidth);

        parentLayout.addView(rightIndicatorLayout, params);

        // Adding recycler view into the right side layout

        FrameLayout.LayoutParams listParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listParams.gravity = Gravity.CENTER | Gravity.RIGHT;
        listParams.setMargins(utils.dpToPx(1), utils.dpToPx(4), utils.dpToPx(1), utils.dpToPx(4));

        mRecyclerView = new RecyclerView(context);
        mRecyclerView.setBackgroundColor(Color.TRANSPARENT);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        rightIndicatorLayout.addView(mRecyclerView, listParams);

    }

    @Override
    public void run() {

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rightIndicatorLayout.getVisibility() == VISIBLE) {
                    setViewsVisibility(false);
                }
            }
        });

    }

    public int getActiveColor() {
        return activeColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBarWidth() {
        return barWidth;
    }

    public void onScreenCreated(int height, ViewTreeObserver.OnGlobalLayoutListener listener) {
        parentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

        if (barHeight == ViewGroup.LayoutParams.MATCH_PARENT)
            itemHeight = (height - utils.dpToPx(16)) / (charList.size());
        else
            itemHeight = (utils.dpToPx(barHeight) - utils.dpToPx(16)) / (charList.size());

        mAdapter = new VolxAdapter(charList, new VolxAdapterFeatures(itemHeight, textSize, textColor, activeColor));
        mRecyclerView.setAdapter(mAdapter);
    }


    public static class Builder {

        private int activeColor = Color.CYAN;

        private int backgroundColor = Color.BLACK;

        private int textSize = 18;

        private int textColor = Color.WHITE;

        private int barWidth = 24;

        private int barHeight = ViewGroup.LayoutParams.MATCH_PARENT;

        private int middleTextSize = 16;

        private int middleLayoutSize = 48;

        private int middleBackgroundColor = Color.rgb(67, 67, 67);

        private int middleStrokeColor = Color.BLACK;

        private int rightStrokeColor = Color.rgb(204, 204, 204);

        private int middleStrokeWidth = 4;

        private int rightStrokeWidth = 3;

        private int middleTextColor = Color.WHITE;

        private int delayMillis = 3000;

        private int minItem = 0;

        private FrameLayout parentLayout;

        private RecyclerView userRecyclerView;

        public Builder setActiveColor(int activeColor) {
            this.activeColor = activeColor;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setBarWidth(int barWidth) {
            this.barWidth = barWidth;
            return this;
        }

        public Builder setBarHeight(int barHeight) {
            this.barHeight = barHeight;
            return this;
        }

        public Builder setMiddleTextSize(int middleTextSize) {
            this.middleTextSize = middleTextSize;
            return this;
        }

        public Builder setMiddleLayoutSize(int middleLayoutSize) {
            this.middleLayoutSize = middleLayoutSize;
            return this;
        }

        public Builder setMiddleBackgroundColor(int middleBackgroundColor) {
            this.middleBackgroundColor = middleBackgroundColor;
            return this;
        }

        public Builder setMiddleStrokeColor(int middleStrokeColor) {
            this.middleStrokeColor = middleStrokeColor;
            return this;
        }

        public Builder setRightStrokeColor(int rightStrokeColor) {
            this.rightStrokeColor = rightStrokeColor;
            return this;
        }

        public Builder setRightStrokeWidth(int rightStrokeWidth) {
            this.rightStrokeWidth = rightStrokeWidth;
            return this;
        }

        public Builder setMiddleStrokeWidth(int middleStrokeWidth) {
            this.middleStrokeWidth = middleStrokeWidth;
            return this;
        }

        public Builder setMiddleTextColor(int middleTextColor) {
            this.middleTextColor = middleTextColor;
            return this;
        }

        public Builder setDelayMillis(int delayMillis) {
            this.delayMillis = delayMillis;
            return this;
        }

        public Builder setMinItem(int minItem) {
            this.minItem = minItem;
            return this;
        }

        public Builder setParentLayout(FrameLayout parentLayout) {
            this.parentLayout = parentLayout;
            return this;
        }

        public Builder setUserRecyclerView(RecyclerView userRecyclerView) {
            this.userRecyclerView = userRecyclerView;
            return this;
        }

        public Volx build() {
            return new Volx(this);
        }
    }
}
