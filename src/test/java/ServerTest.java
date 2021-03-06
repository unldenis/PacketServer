import com.github.unldenis.packet.Packet;
import com.github.unldenis.server.ClientHandler;
import com.github.unldenis.server.Server;
import com.github.unldenis.server.ServerHandler;

import java.io.IOException;
import java.util.Optional;

public class ServerTest {

    public static void main(String[] args) {
        Server serverTest = new Server(6000);
        serverTest.start(new ServerHandler() {
            @Override
            public void onConnectionFailure(IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onClientFailure(IOException e) {
                if(e.getMessage().equals("Connection reset"))
                    System.err.println("A client disconnected");
                else
                    e.printStackTrace();
            }

            @Override
            public void onClientConnected(ClientHandler client) {
                System.out.println("Client connected from " + client.getAddress());
            }

            @Override
            public <T extends Packet> void handlePacket(ClientHandler client, Optional<T> packet) {
                if(packet.isPresent()) {
                    if(packet.get() instanceof MovePacket) {
                        MovePacket movePacket = (MovePacket) packet.get();
                        System.out.println(client.getAddress() + " request a move packet to " + movePacket);
                    }
                }else {
                    System.err.println("Packet from " + client.getAddress() + " not loaded");
                }
            }
        });
    }
}
