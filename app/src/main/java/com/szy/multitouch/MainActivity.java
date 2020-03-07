package com.szy.multitouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IPath {

    private GestureFrameLayout layout;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        layout.setPath(this);
        drawingView = findViewById(R.id.layer);
        drawingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void show(Map<Integer, List<GestureFrameLayout.Record>> map) {
        drawingView.show(map);
    }
}
