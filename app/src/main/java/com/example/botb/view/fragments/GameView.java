package com.example.botb.view.fragments;

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

import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;


public class GameView extends Fragment {
    private static final String TAG = "";

    private int Width;
    private int Height;

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

        // Create the board
        createBoard();

        return view;
    }

    public void createBoard() {

        // Get local board
        Board board = InputManager.getInstance().getLocalBoard();

        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(10);
        layout.setColumnCount(8);

        int lineHeight = Height/layout.getRowCount();
        int lineWidth = Width/layout.getColumnCount();

        for (int x = 0; x < layout.getRowCount(); x++) {
            for (int y = 0; y < layout.getColumnCount(); y++) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setLayoutParams(new ViewGroup.LayoutParams(lineHeight,lineWidth ));

                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setId(R.id.parent + x + y);
                linearLayout.setGravity(Gravity.FILL_HORIZONTAL);
                linearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape));
                Droppable droppable = new Droppable(getActivity(), linearLayout);
                droppable.setLocation(x, y);
                droppable.gameView = this;

                // Get placeable
                Placeable placeable = board.getPlaceable(x, y);

                if (placeable != null){
                    Draggable imageView = new Draggable(getContext());
                    imageView.setImageResource(R.drawable.ic_launcher_background);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    linearLayout.addView(imageView);

                    // Set draggable location
                    imageView.setLocation(x, y);
                }

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(x, 1,1);
                GridLayout.Spec colSpec = GridLayout.spec(y, 1,1);
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(linearLayout, myGLP);
            }
        }

    }


}
