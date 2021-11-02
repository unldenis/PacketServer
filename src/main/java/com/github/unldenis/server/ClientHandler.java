package com.github.unldenis.server;

import com.github.unldenis.encryprion.AES;
import com.github.unldenis.packet.Packet;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

// ClientHandler class
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final ServerHandler serverHandler;
    private String address;
    //aes encryption with client
    private AES AES;

    // Constructor
    public ClientHandler(Socket socket, ServerHandler serverHandler) {
        this.clientSocket = socket;
        this.serverHandler = serverHandler;
    }

    @Override
    public void run() {
        // get client address
        address = clientSocket.getInetAddress().getHostAddress();

        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            /*
                load AES from client
             */
            out.println(Server.RSA.getPublicKeyString());
            out.flush();
            AES = new AES(Server.RSA.decrypt(in.readLine()), Server.RSA.decrypt(in.readLine()));


            // client is connected
            serverHandler.onClientConnected(ClientHandler.this);

            String line;
            while ((line = in.readLine()) != null) {

                //decrypting message
                line = AES.decrypt(line);

                /*
                // writing the received message from client
                System.out.printf(" Sent clear from the client '%s': %s\n", address, line);

                */
                serverHandler.handlePacket(ClientHandler.this, handlePacket(line));
            }
        } catch (IOException e) {
            serverHandler.onClientFailure(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private <T extends Packet> Optional<T> handlePacket(String json)  {
        try {
            T pck = Packet.deserialize(json);
            return Optional.ofNullable(pck);
        } catch (ClassNotFoundException | JsonSyntaxException ignored) { }
        return Optional.empty();
    }


    public String getAddress() {
        return address;
    }
}