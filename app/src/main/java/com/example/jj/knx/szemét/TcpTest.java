package com.example.jj.knx.szemét;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Juhász József on 2018. 03. 24..
 */

public class TcpTest {

    public static void main(String args[]) {
        Socket socket = null;
        try {
            socket = new Socket("192.168.0.12", 9090);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pw.println("asdasdasf");
    }
}
