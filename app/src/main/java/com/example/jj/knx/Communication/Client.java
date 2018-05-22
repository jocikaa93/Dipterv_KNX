package com.example.jj.knx.Communication;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.widget.Toast;

import com.example.jj.knx.MainActivity;
import com.example.jj.knx.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Juhász József on 2018. 03. 24..
 */

public class Client {

    private static Client client = new Client();

    public static Client getInstance() {
        return client;
    }

    public void sendData(final Context context, String addr, String value) throws IOException {

        Socket AndroidClient;
        InputStream is = null;
        OutputStream os = null;

        SharedPreferences IpSettings = PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext());
        String ServerIp = IpSettings.getString("key_ip", "");
        
        SharedPreferences PortSettings = PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext());
        String ServerPortString = PortSettings.getString("key_port","00");

        Integer ServerPort = Integer.parseInt(ServerPortString);

        try {
            AndroidClient = new Socket(ServerIp,ServerPort);  //string host, int port 192.168.0.10
            //is = AndroidClient.getInputStream();
            os = AndroidClient.getOutputStream();

            os.write((addr + ", " + value).getBytes());
            os.close();

//            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//            StringBuilder resultBuffer = new StringBuilder();
//            int inChar;
//            while ((inChar = isr.read()) != -1) {
//                resultBuffer.append((char) inChar);
//            }
//            final String result = resultBuffer.toString();
//            new Runnable() {
//                public void run() {
//                    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//                }
//            };

        } finally {
            //if (os != null)
//                try {
//                    os.close();
//                    isSending = false;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            if (is != null)
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        }
    }
}
