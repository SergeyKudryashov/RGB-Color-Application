package com.ss.rgbcolorsapplication.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ss.rgbcolorsapplication.R;

public class RgbFragment extends Fragment {
    public final int MAX_CLICK_COUNT = 3;
    public static final int[] COLORS = new int[] {R.color.red, R.color.blue, R.color.orange };

    private ConstraintLayout mConstraintLayout;
    private static int mClickedCount = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rgb, container, false);
        mConstraintLayout = view.findViewById(R.id.fragment_constraint_layout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Integer color = getArguments().getInt("color");
        mConstraintLayout.setBackgroundColor(color);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mClickedCount < MAX_CLICK_COUNT) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, RgbFragment.newInstance(getResources().getColor(COLORS[mClickedCount])))
                            .addToBackStack("tag")
                            .commit();
                    mClickedCount++;
                } else {
                    Toast.makeText(getActivity(), "END", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public static void decClickedCount() {
        mClickedCount--;
    }

    public static int getClickedCount() {
        return mClickedCount;
    }

    public static RgbFragment newInstance(int color) {
        Bundle args = new Bundle();
        args.putInt("color", color);
        RgbFragment fragment = new RgbFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
