/*PENSABENE*/
package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    static String hostName = "127.0.0.1";
    static int portNumber = 1234;
    static PrintWriter out = null;
    static BufferedReader in = null;
    static Socket echoSocket = null;
    static BufferedReader stgIn = null;

    public static void main( String[] args )
    {
        Connection();
        while (stgIn != null) {
            try {
                Message(stgIn.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Connection() {
        try {
            echoSocket = new Socket(hostName, portNumber);
        } catch (IOException e){
            System.out.println("Non è stato possibile raggiungere il server" + e);
        }

        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stgIn = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            in = new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Message (String s) {
        String received = "";
        String deliver = s;
        out.println(deliver.toLowerCase());
        System.out.println("sent: " + deliver);

        try {
            received = in.readLine();
            System.out.println("received: " + received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}