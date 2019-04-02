package com.example.botb.view.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.botb.R;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;
import com.example.botb.view.objects.Nexus;
import com.example.botb.view.objects.Shield;


public class OpponentView  extends Fragment {
    private static final String TAG = "";

    private int Width;
    private int Height;

    private boolean toggle = true;

    View v;

    //Nexus nexus = new Nexus(getContext(), 100, 90, 0, 5, 15,-10);

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

        /*GameGrid layout = view.findViewById(R.id.grid);
        layout.setRowCount(10);
        layout.setColumnCount(8);

        int lineHeight = Height/layout.getRowCount();
        int lineWidth = Width/layout.getColumnCount();

        int itemHeight = lineHeight;
        int itemWidth = lineWidth/2;

        for (int i = 0; i < layout.getRowCount(); i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i, 1,1);
            for (int j = 0; j < layout.getColumnCount(); j++) {
                GridLayout.Spec colSpec = GridLayout.spec(j, 1,1);


                Droppable linearLayout = new Droppable(getContext(),80, 100, 0,0, 0,0 );
                linearLayout.setLayoutParams(new ViewGroup.LayoutParams(lineHeight,lineWidth));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setId(R.id.parent + i + j);
                linearLayout.setGravity(Gravity.FILL_HORIZONTAL);

                if (toggle){
                    Shield shield = new Shield(getContext(), 56, 56, 2, 9, 0,0);
                    shield.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    shield.setAdjustViewBounds(true);
                    shield.setScaleType(ImageView.ScaleType.FIT_XY);
                    linearLayout.addView(shield);

                }
                toggle = !toggle;

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(linearLayout, myGLP);
            }
        }*/

        return view;
    }


}
