package com.example.botb.view.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;
import com.example.botb.view.objects.Nexus;
import com.example.botb.view.objects.Shield;
import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends Fragment {

    private static final String TAG = "";

    protected int Height;

    protected int Width;

    View v;

    private List<Draggable> draggables = new ArrayList<Draggable>();

    public void createBoard(Boolean view) {
        // Get local board

        com.example.botb.model.Board board;
        if (view) {
            board = InputManager.getInstance().getLocalBoard();
        } else {
            board = InputManager.getInstance().getRemoteBoard();
        }
        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(10);
        layout.setColumnCount(8);

        int lineHeight = Height / layout.getRowCount();
        int lineWidth = Width / layout.getColumnCount();

        for (int x = 0; x < layout.getRowCount(); x++) {
            for (int y = 0; y < layout.getColumnCount(); y++) {

                Droppable droppable = new Droppable(getContext(), view);
                droppable.setLayoutParams(new ViewGroup.LayoutParams(lineHeight, lineWidth));
                droppable.setOrientation(LinearLayout.HORIZONTAL);
                droppable.setId(R.id.parent + x + y);
                droppable.setGravity(Gravity.CENTER);
                droppable.setLocation(x, y);
                droppable.gameView = this;

                // Get placeable
                Placeable placeable = board.getPlaceable(x, y);

                if (placeable != null) {
                    Draggable draggable;
                    if (placeable.getName() == "Nexus") {
                        draggable = createNexus(view);
                    } else {
                        draggable = createShield(view);
                    }
                    droppable.addView(draggable);
                    draggables.add(draggable);
                    draggable.setLocation(x, y);
                }

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(x, 1, 1);
                GridLayout.Spec colSpec = GridLayout.spec(y, 1, 1);
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(droppable, myGLP);
            }
        }
    }

    public List<Draggable> getDraggables() {
        return this.draggables;
    }

    private Draggable createNexus(Boolean view) {
        Nexus nexus = new Nexus(getContext(), view);
        nexus.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nexus.setAdjustViewBounds(true);
        nexus.setScaleType(ImageView.ScaleType.FIT_XY);
        return nexus;
    }

    private Draggable createShield(Boolean view) {
        Shield shield = new Shield(getContext(), view);
        shield.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        shield.setAdjustViewBounds(true);
        shield.setScaleType(ImageView.ScaleType.FIT_XY);
        return shield;
    }
}
