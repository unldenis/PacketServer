package com.github.unldenis;

import com.github.unldenis.packet.Packet;

import java.io.IOException;
import java.util.Optional;

public interface ServerHandler {

    void onConnectionFailure(IOException e);

    void onClientFailure(IOException e);

    void onClientConnected(ClientHandler client);

    <T extends Packet> void handlePacket(ClientHandler client, Optional<T> packet);

}
