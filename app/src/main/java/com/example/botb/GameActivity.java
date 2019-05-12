package com.example.botb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.botb.controller.InputManager;
import com.example.botb.controller.InputSubscriber;
import com.example.botb.view.fragments.BoardViewPager;
import com.example.botb.view.fragments.BoardPagerAdapter;
import com.example.botb.view.fragments.LocalBoardFragment;
import com.example.botb.view.fragments.RemoteBoardFragment;
import com.example.botb.view.grid.GridCell;
import com.example.botb.view.grid.GridPlaceable;
import java.io.IOException;
import java.util.List;

public class GameActivity extends AppCompatActivity implements InputSubscriber {

    private static final String TAG = "GameActivity";

    public LocalBoardFragment localBoardFragment = new LocalBoardFragment();

    public RemoteBoardFragment remoteBoardFragment = new RemoteBoardFragment();

    private Handler handler;

    private InputManager inputManager;

    private Button readyButton;

    private TextView statusText;

    private BoardViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        readyButton = findViewById(R.id.btn_ready);
        statusText = findViewById(R.id.txt_status);

        inputManager = InputManager.getInstance();
        inputManager.subscribe(this);

        handler = new Handler();

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        readyButton.setOnClickListener(v -> {
            List<GridPlaceable> gridPlaceables = localBoardFragment.getGridPlaceables();
            List<GridCell> gridCells = remoteBoardFragment.getGridCells();

            for (GridPlaceable d : gridPlaceables) {
                d.StopDrag();
            }
            for (GridCell gridCell : gridCells) {
                gridCell.setOnClikcListener();
            }
            try {
                inputManager.setInitialLocalBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.runOnUiThread(() -> {
                readyButton.setText("You are ready!");
                readyButton.setEnabled(false);
                statusText.setText("Waiting for opponent");
            });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        inputManager.setupGame();
    }

    @Override
    public void connectionClosed() {
        inputManager.unsubscribe(this);
        finish();
    }

    @Override
    public void connectionOpen() {
        Log.e(TAG, " This function should not get called in this class!");
    }

    @Override
    public void gameEnd(final boolean localWin) {
        inputManager.unsubscribe(this);
        Intent intent = new Intent(getBaseContext(), GameEndActivity.class);
        intent.putExtra("LOCAL_WIN", localWin);
        startActivity(intent);
        finish();
    }

    @Override
    public void matched() {
        Log.e(TAG, " This function should not get called in this class!");
    }

    @Override
    public void newAction(final boolean isLocalAction) {
        this.runOnUiThread(() -> {
            localBoardFragment.updateBoard(isLocalAction);
            remoteBoardFragment.updateBoard(isLocalAction);

            // Automatically swipe to other board after a slight delay
            handler.postDelayed(() -> {
                int item = viewPager.getCurrentItem() + (isLocalAction ? -1 : 1);
                viewPager.setCurrentItem(item, true);
                statusText.setText((isLocalAction ? "Opponent's" : "Your") + " turn");
            }, 1000);
        });
    }

    @Override
    public void gameStart(boolean localTurn) {
        this.runOnUiThread(() -> {
            remoteBoardFragment.setInitialBoard();
            readyButton.setVisibility(View.INVISIBLE);

            // Automatically swipe to other board after a slight delay
            handler.postDelayed(() -> {
                int item = viewPager.getCurrentItem() + (localTurn ? 1 : -1);
                viewPager.setCurrentItem(item, true);
                statusText.setText((localTurn ? "Your" : "Opponent's") + " turn");
            }, 1000);
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        BoardPagerAdapter adapter = new BoardPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(localBoardFragment, "LocalBoardFragment");
        adapter.addFragment(remoteBoardFragment, "RemoteBoardFragment");
        viewPager.setAdapter(adapter);
    }
}
