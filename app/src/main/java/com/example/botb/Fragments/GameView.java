package com.example.botb.Fragments;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayout;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.botb.R;
import com.example.botb.objects.Draggable;
import com.example.botb.objects.Droppable;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;



public class GameView extends Fragment {
    private static final String TAG = "";

    private int Width;
    private int Height;

    private boolean toggle = true;

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(new Runnable() {
              @Override
              public void run() {
                  Height=v.getMeasuredHeight();
                  Width=v.getMeasuredWidth();
              }
        });

        GridLayout layout = view.findViewById(R.id.grid);
        layout.setRowCount(4);
        layout.setColumnCount(4);

        for (int i = 0; i < layout.getRowCount(); i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i, 1,1);
            for (int j = 0; j < layout.getColumnCount(); j++) {
                GridLayout.Spec colSpec = GridLayout.spec(j, 1,1);
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new ViewGroup.LayoutParams(100, 100));


                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setId(R.id.parent + i + j);
                linearLayout.setGravity(Gravity.FILL_HORIZONTAL);
                linearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_droptarget));
                Droppable droppable = new Droppable(getActivity(), linearLayout);

                if (toggle){
                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageResource(R.drawable.ic_launcher_background);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    linearLayout.addView(imageView);
                    new Draggable(imageView);
                }
                toggle = !toggle;

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(linearLayout, myGLP);
            }
        }

        return view;
    }


}
