package com.example.botb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        boolean localWin = getIntent().getBooleanExtra("LOCAL_WIN", false);

        runOnUiThread(() -> {
            TextView winnerText = findViewById(R.id.txt_winner);
            if (localWin) {
                winnerText.setText("WINNER");
                winnerText.setTextColor(Color.parseColor("#066000"));
            } else {
                winnerText.setText("LOSER");
                winnerText.setTextColor(Color.parseColor("#600000"));
            }
        });

        Button playAgainButton = findViewById(R.id.btn_play_again);
        playAgainButton.setOnClickListener(v -> startActivity(new Intent(GameEndActivity.this, MainActivity.class)));
    }
}
