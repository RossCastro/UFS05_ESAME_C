/*PENSABENE*/

package org.example;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static int numero_porta = 1234;
    static public Socket client_Socket = null;
    static String nome_host = "127.0.0.1";

    public static void main(String[] args) {

        ServerSocket s_Socket = openServer();
        System.out.println("Server socket startato alla porta num: " + s_Socket.getLocalPort());
        while (true) {
            try {
                client_Socket = s_Socket.accept();
                System.out.println("Il client è connesso!");
            } catch (IOException e) {
                System.out.println("ERRORE nell'accettare la richiesta" + e);
            }

            System.out.println(client_Socket.getLocalAddress());

            ClientHandler clientHandler = new ClientHandler(client_Socket);

            new Thread(clientHandler).start();
        }

    }

    static private ServerSocket openServer() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(numero_porta);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverSocket;
    }
}
