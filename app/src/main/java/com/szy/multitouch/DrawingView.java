package com.szy.multitouch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;
import java.util.Map;

public class DrawingView extends View {

    private Map<Integer, List<GestureFrameLayout.Record>> map;
    private Paint paint = new Paint();

    private int size = 0;

    public DrawingView(Context context) {
        super(context);
        init(context);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        size = (int) Math.sqrt((double) (
                context.getResources().getDisplayMetrics().widthPixels
                        * context.getResources().getDisplayMetrics().widthPixels
                        + context.getResources().getDisplayMetrics().heightPixels
                        * context.getResources().getDisplayMetrics().heightPixels
                ));
    }

    public void show(Map<Integer, List<GestureFrameLayout.Record>> map) {
        this.map = map;
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (this.map != null) {
            for (Map.Entry<Integer, List<GestureFrameLayout.Record>> entry : map.entrySet()) {
                List<GestureFrameLayout.Record> list = entry.getValue();
                @SuppressLint("CI_DrawAllocation") float[] lines = new float[list.size() * 2];
                int i = 0;
                for (GestureFrameLayout.Record record : list) {
                    lines[i++] = record.x;
                    lines[i++] = record.y;
                }

                canvas.drawLines(lines, paint);
            }
            this.map.clear();
            this.map = null;
        }
    }
}
