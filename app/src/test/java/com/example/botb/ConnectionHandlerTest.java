package com.example.botb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.java_websocket.client.WebSocketClient;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionHandlerTest {

    // Example usage of Mockito mock-framework

    @Mock
    ConnectionHandler connectionHandler;

    @Test
    public void getSocket() {
        WebSocketClient socket = mock(WebSocketClient.class);
        assertEquals(socket.getSocket(), connectionHandler.getSocket());
    }

    @Test
    public void sendMessage() {
        connectionHandler.sendMessage("Hello");
    }
}