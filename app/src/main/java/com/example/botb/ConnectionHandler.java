package com.example.botb;

import android.content.SharedPreferences;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

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


/**
 * Created by tjabe on 02.02.2018.
 *
 */

public class ConnectionHandler {
    public static final String TAG = "ConnectionHandler";
    private WebSocketClient socket;
    private boolean https;
    private String host = "10.22.14.75:8080";
    private SharedPreferences sharedpreferences;

    /**
     * This is the constructor.
     * On run it creates the socket object.
     */
    public ConnectionHandler(){
        this.socket = getSocket();
    }

    public void sendAction(String action){
        socket.send(action);
    }

    /**
     * The socket create method.
     * This method request the https webSocket connection, and tries to login with given parameters:
     * @return      The socket object on connection.
     */
    public synchronized WebSocketClient getSocket() {
        URI uri;
        String websocketEndPointUrl = "";
        //sharedpreferences = PreferenceManager.getDefaultSharedPreferences(startCtx);
        WebSocketClient mWebSocketClient = null;
        try {


            websocketEndPointUrl="wss://"+host;
            Log.i(TAG, " WSURL: " + websocketEndPointUrl);

            uri = new URI(websocketEndPointUrl);

            try {
                mWebSocketClient = new WebSocketClient(uri)
                {
                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        Log.e("Websocket", "Opened");
                        //this.send("BLALAL");
                    }

                    @Override
                    public void onMessage(String s) {
                        String data[] = s.split(":");
                        String identifier = data[0];
                        Log.e(TAG,"Message: "+identifier);
                        Log.e(TAG,"Length: "+data.length);
                        switch (identifier){
                            case "in_a_room" :
                                if (data.length > 1){
                                    Log.e(TAG, "onMessage: locate");
                                }else{
                                    Log.e(TAG,"Wrong syntax!");
                                }
                                break;
                            case "room" :
                                Log.e(TAG, "onMessage: Room");
                                break;
                            default:
                                Log.e("Tag","Given syntax not allowed !!!"+data.toString());
                        }
                    }

                    @Override
                    public void onClose(int i, String s, boolean b) {
                        Log.i("Websocket", "Closed " + s);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i("Websocket", "Error " + e.getMessage());
                    }
                };

                if (websocketEndPointUrl.indexOf("wss") == 0)
                {
                    try {
                        SSLContext sslContext = null;
                        sslContext = SSLContext.getInstance( "TLS" );
                        sslContext.init( null, trustAllCerts, null );
                        // sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates
                        SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

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