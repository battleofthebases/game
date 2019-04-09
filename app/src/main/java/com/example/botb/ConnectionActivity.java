package com.example.botb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ConnectionActivity extends AppCompatActivity implements InputSubscriber {

    private InputManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        //Trying to connect
        inputManager = InputManager.getInstance();
        inputManager.subscribe(this);
    }

    @Override
    public void wsOpen() {
        TextView statusTextView = (TextView) findViewById(R.id.status);
        statusTextView.setText("Connected to server!");
    }

    @Override
    public void wsClosed() {
        inputManager.unsubscribe(this);
    }
}
