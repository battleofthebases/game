package com.example.botb;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.botb.Fragments.GameView;
import com.example.botb.Fragments.OpponentView;
import com.example.botb.Fragments.statePageAdapter;

public class GameActivity extends AppCompatActivity {

    private Button mainButton;

    private statePageAdapter adapter;
    private ViewPager viewPager;

    private int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainButton = (Button) findViewById(R.id.Main_button);

        adapter = new statePageAdapter(getSupportFragmentManager());
        viewPager =  (ViewPager) findViewById(R.id.contianer);
        setupViewPager(viewPager);



        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(toggle);
                if (toggle == 0){toggle = 1;} else {toggle = 0;}
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        statePageAdapter adapter = new statePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GameView(), "GameView");
        adapter.addFragment(new OpponentView(), "OpponentView");
        viewPager.setAdapter(adapter);
    }

}
