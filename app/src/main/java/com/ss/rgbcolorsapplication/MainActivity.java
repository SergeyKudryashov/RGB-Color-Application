package com.ss.rgbcolorsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ss.rgbcolorsapplication.fragment.RgbFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, RgbFragment.newInstance(getResources().getColor(RgbFragment.COLORS[0])))
                .commit();

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
