package com.example.botb;

import android.util.Log;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;


/**
 * Created by tjabe on 02.02.2018.
 */

class ConnectionHandler {

    private static final String TAG = "ConnectionHandler";

    private InputManager inputManager;

    private WebSocketClient socket;

    private TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    }};

    /**
     * This is the constructor.
     * On run it creates the socket object.
     */
    ConnectionHandler(InputManager inputMan) {
        inputManager = inputMan;
    }

    void connect() {
        this.socket = getSocket();
    }

    /**
     * The socket create method.
     * This method request the https webSocket connection:
     *
     * @return The socket object on connection.
     */
    synchronized WebSocketClient getSocket() {
        URI uri;
        String webSocketEndPointUrl;
        WebSocketClient mWebSocketClient = null;
        try {
            webSocketEndPointUrl = "wss://" + ServerValues.SERVER_ADDRESS + ServerValues.SERVER_PORT;

            uri = new URI(webSocketEndPointUrl);
            try {
                mWebSocketClient = new WebSocketClient(uri) {
                    @Override
                    public void onClose(int i, String s, boolean b) {
                        Log.i(TAG, "WebSocket Closed :" + s);
                        inputManager.subscriptionEvent(ConnectionEvent.DISCONNECTED);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i(TAG, "Error " + e.getMessage());
                    }

                    @Override
                    public void onMessage(String s) {
                        Log.e(TAG, s);
                        String[] dataAll = s.split(":");
                        String identifier = dataAll[0];
                        String data = dataAll[1];
                        switch (identifier) {
                            case "Action":
                                Log.e(TAG, "Action json: " + data);
                                try {
                                    inputManager.handleRemoteAction(Parser.stringToAction(data));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "AddedToRoom":
                                inputManager.subscriptionEvent(ConnectionEvent.MATCHED);
                                break;
                            case "InitialGameBoard":
                                try {
                                    inputManager.setInitialRemoteBoard(Parser.stringToBoard(data));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                Log.e("Tag", "Non valid syntax!" + " id: " + identifier);
                        }
                    }

                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        Log.e("Websocket", "Opened");
                        inputManager.subscriptionEvent(ConnectionEvent.CONNECTED);
                    }
                };

                if (webSocketEndPointUrl.indexOf("wss") == 0) {
                    try {
                        SSLContext sslContext = null;
                        sslContext = SSLContext.getInstance("TLS");
                        sslContext.init(null, trustAllCerts, null);
                        // sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates
                        SSLSocketFactory factory = sslContext.getSocketFactory();

                        mWebSocketClient.setSocketFactory(factory);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
                mWebSocketClient.connect();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            Log.e(TAG, e.getMessage());
        }

        return mWebSocketClient;
    }

    void sendMessage(String message) {
        try {
            socket.send(message);
        } catch (WebsocketNotConnectedException e) {
            Log.e(TAG, "WebsocketNotConnectedException" + e);
            getSocket();
        }
    }
}