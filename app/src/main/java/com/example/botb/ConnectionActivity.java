package com.example.botb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ConnectionActivity extends AppCompatActivity implements InputSubscriber {

    private static final String TAG = "ConnectionActivity";

    private InputManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        //Trying to connect
        inputManager = InputManager.getInstance();
        inputManager.subscribe(this);
        inputManager.connectToServer();
    }

    @Override
    public void connectionClosed() {
        inputManager.unsubscribe(this);
        finish();
    }

    @Override
    public void connectionOpen() {
        updateTheStatusText(getResources().getString(R.string.waiting_for_players));
    }

    @Override
    public void gameEnd(final boolean localWin) {
        Log.e(TAG, "GameEnd should not get called in this class!");
        //inputManager.unsubscribe(this);
        //finish();
    }

    @Override
    public void matched() {
        startActivity(new Intent(ConnectionActivity.this, GameActivity.class));
        inputManager.unsubscribe(this);
        finish();
    }

    @Override
    public void newAction(final boolean isLocalAction) {
        Log.e(TAG, "NewAction should not get called in this class!");
    }

    @Override
    public void setInitialOpponentBoard() {
        Log.e(TAG, "SetInitialOpponentBoard should not get called in this class!");
    }

    private void updateTheStatusText(final String text) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                TextView statusTextView = (TextView) findViewById(R.id.status);
                statusTextView.setText(text);
            }
        });
    }

}
