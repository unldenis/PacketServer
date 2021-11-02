package com.github.unldenis;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ClientTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        Client client = new Client("localhost", 6000);
        client.connect();
        MovePacket movePacket = new MovePacket(5, 6, 8);
        client.sendPacket(movePacket);
        Thread.sleep(1000);
    }
}
