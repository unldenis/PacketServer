# PacketServer
Packet server is an api that allows you to communicate fully encrypted (RSA / AES) via the <a href="https://github.com/unldenis/PacketServer/blob/3b9685a3d16fa8a3d7e96882f25ff92db3b1fe8e/src/main/java/com/github/unldenis/packet/Packet.java#L6">packets</a> in the network.<br>
Your packages will be converted into Json by the client to the server which will then be able to read them.<br>
Usage

'''java
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
            if(packet.get() instanceof MovePacket movePacket) {
                System.out.println(client.getAddress() + " request a move packet to " + movePacket);
            }
        }else {
            System.err.println("Packet from " + client.getAddress() + " not loaded");
        }
    }
});
'''java
