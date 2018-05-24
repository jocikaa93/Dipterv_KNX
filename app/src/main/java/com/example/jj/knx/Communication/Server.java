package com.example.jj.knx.Communication;

import android.widget.SeekBar;

import com.example.jj.knx.Model.TcpKnxData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Server server = new Server();
    public static Server getInstance() {
        return server;
    }

    private ServerSocket serverSocket;
    private boolean listen = true;
    private Gson gson = new Gson();

    public void listen(int port, SeekBar s1) throws IOException {
        serverSocket = new ServerSocket(port);
        while (listen) {
            Socket socketFromClient = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socketFromClient.getInputStream()));

            String message;
            StringBuilder sb = new StringBuilder();
            while ((message = br.readLine()) != null) {
                sb.append(message);
            }
            message = sb.toString();
            TcpKnxData knxData = gson.fromJson(message, TcpKnxData.class);

            System.out.println(knxData.getValue() + " " + knxData.getDestinationAddress());
            //s1.setProgress(knxData.getValue());
            //TODO felület frissítése
        }
    }

    public void stopListening() throws IOException {
        listen = false;
        serverSocket.close();
    }
}
