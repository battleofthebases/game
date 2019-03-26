package com.example.botb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.ExampleWeapon;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GameActivity.class));
            }
        });
    }
}



/*
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private InputManager inputManager = InputManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        try {
            inputManager.handleLocalAction(new Action(new Location(2,3), new ExampleWeapon()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
