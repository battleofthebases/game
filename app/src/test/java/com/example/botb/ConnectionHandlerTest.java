package com.example.botb;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
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
    public void getSocket() throws URISyntaxException {
        WebSocketClient socket = mock(WebSocketClient.class);
        String webSocketEndPointUrl = "wss://" + ServerValues.SERVER_ADDRESS + ServerValues.SERVER_PORT;
        URI uri = new URI(webSocketEndPointUrl);

        socket = new WebSocketClient(uri) {
            @Override
            public void onClose(final int code, final String reason, final boolean remote) {

            }

            @Override
            public void onError(final Exception ex) {

            }

            @Override
            public void onMessage(final String message) {

            }

            @Override
            public void onOpen(final ServerHandshake handshakedata) {

            }
        };

        assertNotNull(socket);
    }

    @Test
    public void sendMessage() {
        connectionHandler.sendMessage("Hello");
    }
}