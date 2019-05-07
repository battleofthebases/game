package com.example.botb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        boolean localWin = getIntent().getBooleanExtra("LOCAL_WIN", false);

        final String endState;
        if (localWin) {
            endState = getString(R.string.won);
        } else {
            endState = getString(R.string.lost);
        }

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                TextView statusTextView = (TextView) findViewById(R.id.end_State);
                statusTextView.setText(endState);
            }
        });
    }
}
