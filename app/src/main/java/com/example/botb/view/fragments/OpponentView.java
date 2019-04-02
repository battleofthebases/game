package com.example.botb.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.botb.R;

public class OpponentView  extends BoardAdapter {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(new Runnable() {
            @Override
            public void run() {
                Height=v.getMeasuredHeight();
                Width=v.getMeasuredWidth();
            }
        });

        // Create the board
        this.createBoard(false);
        return view;
    }

}



