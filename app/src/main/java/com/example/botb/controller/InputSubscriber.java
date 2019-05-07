package com.example.botb.controller;

import com.example.botb.model.Board;

public interface InputSubscriber {

    void connectionClosed();

    void connectionOpen();

    void matched();

    void newAction(boolean isLocalAction);

    void setInitialOpponentBoard();

    void gameEnd(boolean localWin);
}
