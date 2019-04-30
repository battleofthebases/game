package com.example.botb;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.example.botb.view.fragments.GameView;
import com.example.botb.view.fragments.OpponentView;
import com.example.botb.view.fragments.statePageAdapter;
import com.example.botb.view.objects.Draggable;
import java.io.IOException;
import java.util.List;

public class GameActivity extends AppCompatActivity {

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

    private void setupViewPager(ViewPager viewPager) {
        statePageAdapter adapter = new statePageAdapter(getSupportFragmentManager());
        adapter.addFragment(gameview, "GameView");
        adapter.addFragment(opponentView, "OpponentView");
        viewPager.setAdapter(adapter);
    }

}
