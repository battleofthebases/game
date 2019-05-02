package com.example.botb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.example.botb.model.Board;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.fragments.GameView;
import com.example.botb.view.fragments.OpponentView;
import com.example.botb.view.fragments.statePageAdapter;
import com.example.botb.view.objects.Draggable;
import java.io.IOException;
import java.util.List;

public class GameActivity extends AppCompatActivity implements InputSubscriber {

    public GameView gameview = new GameView();

    public OpponentView opponentView = new OpponentView();

    private statePageAdapter adapter;

    private Button mainButton;

    private Button startGameButton;

    private int toggle = 0;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainButton = (Button) findViewById(R.id.Main_button);
        startGameButton = (Button) findViewById(R.id.startGame);
        final InputManager inputManager = InputManager.getInstance();
        inputManager.subscribe(this);

        adapter = new statePageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.contianer);
        setupViewPager(viewPager);

        mainButton.setOnClickListener(new View.OnClickListener() {

            Boolean toggle = true;

            @Override
            public void onClick(View v) {

                List<Draggable> draggables = gameview.getDraggables();

                if (toggle) {
                    for (Draggable d : draggables) {
                        d.StopDrag();
                    }
                } else {
                    for (Draggable d : draggables) {
                        d.StartDrag();
                    }
                }
                toggle = !toggle;

                try {
                    inputManager.setInitialLocalBoard();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    inputManager.setInitialLocalBoard();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionOpen() {

    }

    @Override
    public void matched() {

    }

    @Override
    public void newAction(final boolean isLocalAction) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameview.updateBoard(isLocalAction);
                opponentView.updateBoard(isLocalAction);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        statePageAdapter adapter = new statePageAdapter(getSupportFragmentManager());
        adapter.addFragment(gameview, "GameView");
        adapter.addFragment(opponentView, "OpponentView");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setInitialOpponentBoard(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentView.setInitialBoard();
            }
        });
    }



}
