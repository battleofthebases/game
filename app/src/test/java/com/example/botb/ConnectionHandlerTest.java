package com.example.botb;
import org.java_websocket.client.WebSocketClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionHandlerTest {

    // Empty test to showcase usage of Mockito mock-framework

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Mock
    ConnectionHandler connectionHandler;

    @Test
    public void sendMessage() {
        connectionHandler.sendMessage("Hello");

    }


    @Test
    public void getSocket() {
        WebSocketClient socket = mock(WebSocketClient.class);
    }
}