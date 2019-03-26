package com.example.botb;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Created by tjabe on 02.02.2018.
 */

public class ConnectionHandler {
    public static final String TAG = "ConnectionHandler";
    private WebSocketClient socket;
    private boolean https;
    private String host = "10.22.1.209:8080";
    private InputManager inputManager;

    /**
     * This is the constructor.
     * On run it creates the socket object.
     */
    public ConnectionHandler(InputManager inputMan){
        this.socket = getSocket();
        inputManager = inputMan;
    }

    public void sendMessage(String message){
        try{
            socket.send(message);
        }catch (WebsocketNotConnectedException e){
            Log.e(TAG, "WebsocketNotConnectedException"+e);
            getSocket();
        }
    }

    /**
     * The socket create method.
     * This method request the https webSocket connection:
     * @return  The socket object on connection.
     */
    public synchronized WebSocketClient getSocket() {
        URI uri;
        String webSocketEndPointUrl;
        WebSocketClient mWebSocketClient = null;
        try {
            webSocketEndPointUrl="wss://"+host;

            uri = new URI(webSocketEndPointUrl);
            try {
                mWebSocketClient = new WebSocketClient(uri)
                {
                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        Log.e("Websocket", "Opened");
                    }

                    @Override
                    public void onMessage(String s) {
                        String[] dataAll = s.split(":");
                        String identifier = dataAll[0];
                        String data = dataAll[1];
                        switch (identifier){
                            case "Action" :
                                Log.e(TAG,"Action json: "+data);
                                try {
                                    inputManager.handleRemoteAction(data);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "InitialGameBoard":
                                try {
                                    inputManager.setRemoteBoard(data);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            default:
                                Log.e("Tag","Non valid syntax!");
                        }
                    }

                    @Override
                    public void onClose(int i, String s, boolean b) {
                        Log.i(TAG, "WebSocket Closed :"+s);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i(TAG, "Error " + e.getMessage());
                    }
                };

                if (webSocketEndPointUrl.indexOf("wss") == 0)
                {
                    try {
                        SSLContext sslContext = null;
                        sslContext = SSLContext.getInstance( "TLS" );
                        sslContext.init( null, trustAllCerts, null );
                        // sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates
                        SSLSocketFactory factory = sslContext.getSocketFactory();

                        mWebSocketClient.setSocketFactory( factory );
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


    //Helper Methods
    private TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }};
}