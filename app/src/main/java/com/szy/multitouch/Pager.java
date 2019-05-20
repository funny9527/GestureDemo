package com.szy.multitouch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

public class Pager extends View {

    private Map<Integer, List<DecorFrameLayout.Record>> map;
    private Paint paint = new Paint();

    public Pager(Context context) {
        super(context);
    }

    public Pager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Pager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Pager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void show(Map<Integer, List<DecorFrameLayout.Record>> map) {
        Log.d("test", "show");
        this.map = map;
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d("test", "onDraw");
        if (this.map != null) {
            Log.d("test", "onDraw real " + map.size());
            for (Map.Entry<Integer, List<DecorFrameLayout.Record>> entry : map.entrySet()) {
                List<DecorFrameLayout.Record> list = entry.getValue();
                @SuppressLint("CI_DrawAllocation") float[] lines = new float[list.size() * 2];
                int i = 0;
                for (DecorFrameLayout.Record record : list) {
                    lines[i++] = record.x;
                    lines[i++] = record.y;
                }

                Log.d("test", "lines " + lines.length);
                paint.setStrokeWidth(list.get(0).s);
                canvas.drawLines(lines, paint);
            }
            this.map.clear();
            this.map = null;
        }
    }
}
