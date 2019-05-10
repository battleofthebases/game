package com.example.botb.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.view.SpriteLoader;

public class LocalBoardFragment extends BoardFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(() -> {
            height = v.getHeight();
            width = v.getWidth();
            spriteLoader = new SpriteLoader(getActivity(), width, height);
            createBoard(true);
        });
        return view;
    }

    public void updateBoard(boolean isLocalAction) {
        // Update local board if remote player did something
        if (!isLocalAction) {
            Board board = inputManager.getLocalBoard();
            updateBoard(board);
        }
    }
}

