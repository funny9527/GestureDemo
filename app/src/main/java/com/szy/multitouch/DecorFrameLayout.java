package com.szy.multitouch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecorFrameLayout extends FrameLayout {

    private IPath path;

    private Map<Integer, List<Record>> recordMap = new HashMap<>();

    public DecorFrameLayout(Context context) {
        super(context);
    }

    public DecorFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DecorFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DecorFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int pointIndex = event.getActionIndex();
        int pointId = event.getPointerId(pointIndex);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("test", "ACTION_DOWN "
                        + " " + pointIndex
                        + " " + pointId
                        + " " + event.getX(pointIndex)
                        + " " + event.getY(pointIndex)
                        + " " + event.getSize()
                        + " " + event.getPressure());
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("test", "ACTION_POINTER_DOWN "
                        + " " + pointIndex
                        + " " + pointId
                        + " " + event.getX(pointIndex)
                        + " " + event.getY(pointIndex) + " "
                        + " " + event.getSize()
                        + " " + event.getPressure());
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointId = event.getPointerId(i);
                    Log.d("test", "ACTION_MOVE "
                            + " " + i
                            + " " + pointId
                            + " " + event.getX(i)
                            + " " + event.getY(i)
                            + " " + event.getSize()
                            + " " + event.getPressure());
                    handleEvent(event, pointId, i);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("test", "ACTION_POINTER_UP "
                        + " " + pointIndex
                        + " " + event.getPointerId(pointIndex)
                        + " " + event.getX(pointIndex)
                        + " " + event.getY(pointIndex)
                        + " " + event.getSize()
                        + " " + event.getPressure());
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d("test", "ACTION_UP "
                        + " " + pointIndex
                        + " " + event.getPointerId(pointIndex)
                        + " " + event.getX(pointIndex)
                        + " " + event.getY(pointIndex)
                        + " " + event.getSize()
                        + " " + event.getPressure());
                handleEvent(event, pointId, pointIndex);
                if (path != null) {
                    path.show(recordMap);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public void handleEvent(MotionEvent event, int pointId, int pointIndex) {
        List<Record> list = recordMap.get(pointId);
        if (list == null) {
            list = new ArrayList<>();
            recordMap.put(pointId, list);
        }
        list.add(new Record(event.getX(pointIndex), event.getY(pointIndex), event.getSize(pointIndex),
                event.getPressure(pointIndex), System.currentTimeMillis()));
    }

    public static class Record {
        public float x;
        public float y;
        public float s;
        public float p;
        public long t;

        public Record(float x, float y, float s, float p, long t) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.p = p;
            this.t = t;
        }
    }

    public void setPath(IPath path) {
        this.path = path;
    }
}
