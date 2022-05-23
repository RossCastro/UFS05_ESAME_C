package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import com.google.gson.Gson;



public class ClientHandler implements Runnable {
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    static ArrayList<Piatti> plates = new ArrayList<Piatti>();

    ClientHandler (Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run () {
        this.buildPlates();
        this.ClientToClientHandler();
        try {
            this.ClientHandlerToClient();
        } catch (SocketException e) {
            System.out.println("error");
        }
    }

    void ClientToClientHandler () {
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("reader failed" + e);
        }

        out = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ClientHandlerToClient() throws SocketException {
        Gson gson = new Gson();
        String s;
        while (true) {
            s = receive();

            try {
                switch (s) {
                    default:
                        out.println(s + " is an unavailable command");
                        break;
                    case "more_expensive":
                        out.println(gson.toJson(maxPrice()));
                        break;

                    case "all":
                        out.println(plates);
                        break;

                    case "all_sorted":
                        sort_by_name();
                        out.println(plates);
                        break;

                }

            } catch (NullPointerException e) {
                System.out.println("Client: " + clientSocket.getLocalAddress() + " disconnected from the server");
                break;
            }

            if (s == "") break;
        }
    }




    String receive() {
        String s = "";
        try {
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }



    public void buildPlates() {
        plates.add(new Piatti("Pasta con sugo e melanzane", 1, "Norma", 15.30));
        plates.add(new Piatti("Wurstel e Patatine", 15, "Pizza Americana", 12.00));
        plates.add(new Piatti("Dolce al cioccolato", 20, "Profitterol", 10.70));
    }



    Piatti maxPrice() {
        sort_by_price();
        return plates.get(0);
    }

    void sort_by_price() {
        plates.sort((o1, o2) -> {
            if (o1.getPrice() < o2.getPrice())
                return 1;
            if (o1.getPrice() > o2.getPrice())
                return -1;
            return 0;
        });
    }

    void sort_by_name() {
        plates.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
    }


}
