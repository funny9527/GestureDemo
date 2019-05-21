package com.szy.multitouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IPath {

    private DecorFrameLayout layout;
    private Pager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        layout.setPath(this);
        pager = findViewById(R.id.pager);
        pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void show(Map<Integer, List<DecorFrameLayout.Record>> map) {
        pager.show(map);
    }
}
