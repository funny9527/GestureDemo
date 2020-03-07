package com.szy.multitouch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestureFrameLayout extends FrameLayout {

    private IPath path;

    private Map<Integer, List<Record>> recordMap = new HashMap<>();

    public GestureFrameLayout(Context context) {
        super(context);
    }

    public GestureFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int pointIndex = event.getActionIndex();
        int pointId = event.getPointerId(pointIndex);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointId = event.getPointerId(i);
                    handleEvent(event, pointId, i);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                handleEvent(event, pointId, pointIndex);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
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
        list.add(new Record(event.getX(pointIndex), event.getY(pointIndex)));
    }

    public static class Record {
        public float x;
        public float y;

        public Record(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public void setPath(IPath path) {
        this.path = path;
    }
}
