package com.github.unldenis;

import com.github.unldenis.packet.Packet;
import lombok.Data;


@Data
public class MovePacket extends Packet {

    private final int x, y, z;

}

