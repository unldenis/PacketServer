package com.github.unldenis.packet;


import com.github.unldenis.utils.JsonUtils;

public class Packet {

    private final String packet;

    protected Packet() {
        packet = getClass().getName();
    }

    @Override
    public String toString() {
        return "Packet{" + "packet='" + packet + '\'' + '}';
    }

    public String serialize() {
        return JsonUtils.serializeObject(this);
    }

    public static <T extends Packet> T deserialize(String json) throws ClassNotFoundException {
        Packet packet = JsonUtils.deserializeObject(json, Packet.class);
        Class<T> clazz = (Class<T>) Class.forName(packet.packet);
        return (T) JsonUtils.deserializeObject(json, clazz);
    }

}
