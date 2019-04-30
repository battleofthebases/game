package com.example.botb;

public interface InputSubscriber {

    void connectionClosed();

    void connectionOpen();

    void matched();

    void newAction(boolean isLocalAction);
}
