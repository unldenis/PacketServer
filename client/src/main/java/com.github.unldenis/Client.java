package com.github.unldenis;

import com.github.unldenis.encryprion.AES;
import com.github.unldenis.encryprion.RSA;
import com.github.unldenis.packet.Packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Client {

    private final String host;
    private final int port;

    private AES aes;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) throws NoSuchAlgorithmException {
        this.host = host;
        this.port = port;
        aes = new AES();
        aes.encryptOld("test");
    }
    public void connect() throws IOException {

        Socket socket = new Socket(host, port);
        out = new PrintWriter(
                socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        /*
            Handshake
         */
        //Get RSA public key
        String rsa_public_key = in.readLine();
        RSA rsa = new RSA(rsa_public_key);
        //encrypting client AES key and send to server
        String encryptedAES = rsa.encrypt(aes.getKeyString());
        out.println(encryptedAES);
        out.flush();
        //encrypting client AES IV and send to server
        String encryptedIV = rsa.encrypt(aes.getIVString());
        out.println(encryptedIV);
        out.flush();
    }

    public <T extends Packet> void sendPacket(T packet) {
        String line = packet.serialize();
        //encrypting message
        line = aes.encrypt(line);
        // sending the packet to server
        out.println(line);
        out.flush();
    }

}
