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
        inputManager.connectToServer();
    }

    @Override
    public void connectionClosed() {
        inputManager.unsubscribe(this);
        TextView statusTextView = (TextView) findViewById(R.id.status);
        statusTextView.setText("Waiting for other players!");
        finish();
    }

    @Override
    public void connectionOpen() {
        TextView statusTextView = (TextView) findViewById(R.id.status);
        statusTextView.setText("Waiting for other players!");
    }
}
