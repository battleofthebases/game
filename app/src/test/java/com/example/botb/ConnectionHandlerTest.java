package com.example.botb;

import static org.mockito.Mockito.*;

import org.java_websocket.client.WebSocketClient;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionHandlerTest {

    // Empty test to showcase usage of Mockito mock-framework

    @Mock
    ConnectionHandler connectionHandler;

    @Test
    public void getSocket() {
        WebSocketClient socket = mock(WebSocketClient.class);
    }

    @Test
    public void sendMessage() {
        connectionHandler.sendMessage("Hello");

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}