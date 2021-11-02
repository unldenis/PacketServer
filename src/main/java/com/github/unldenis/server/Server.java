package com.github.unldenis.server;

import com.github.unldenis.encryprion.RSA;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server class
public final class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public static final RSA RSA = new RSA();

    public void start(ServerHandler serverHandler) {
        ServerSocket server = null;
        try {
            // server is listening on port
            server = new ServerSocket(port);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client, serverHandler);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            serverHandler.onConnectionFailure(e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}