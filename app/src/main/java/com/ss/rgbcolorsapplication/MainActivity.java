package com.ss.rgbcolorsapplication;

import android.annotation.SuppressLint;
import android.arch.lifecycle.BuildConfig;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ss.rgbcolorsapplication.fragment.RgbFragment;

import static com.ss.rgbcolorsapplication.BuildConfig.*;

public class MainActivity extends AppCompatActivity {

    private float X;
    private float Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, RgbFragment.newInstance(getResources().getColor(RgbFragment.COLORS[0])))
                .commit();

        showEventsInDebugMode();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showEventsInDebugMode() {
        Log.v("_____", "DEBUaG MODE: " + DEBUG);
        if (!DEBUG) {
            return;
        }
        final ConstraintLayout constraintLayout = findViewById(R.id.container);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(280, 360);
        final RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.transparentWhiteColor));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int _x = (int) event.getX();
                int _y = (int) event.getY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        X = recyclerView.getX() - event.getRawX();
                        Y = recyclerView.getY() - event.getRawY();
                    case MotionEvent.ACTION_MOVE:
                        recyclerView.animate()
                                .x(event.getRawX() + X)
                                .y(event.getRawY() + Y)
                                .setDuration(0)
                                .start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        constraintLayout.addView(recyclerView);


    }

    @Override
    public void onBackPressed() {
        if (RgbFragment.getClickedCount() > 1) {
            RgbFragment.decClickedCount();
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
