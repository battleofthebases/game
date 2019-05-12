package com.example.botb.controller;

public interface InputSubscriber {

    void connectionClosed();

    void connectionOpen();

    void matched();

    void newAction(boolean isLocalAction);

    void gameStart(boolean localTurn);

    void gameEnd(boolean localWin);
}
