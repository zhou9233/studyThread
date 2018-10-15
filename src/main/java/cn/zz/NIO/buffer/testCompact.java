package cn.zz.NIO.buffer;

import java.nio.ByteBuffer;

public class testCompact {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        //byteBuffer.position(2);
        byteBuffer.get();
        byteBuffer.compact();
        System.out.println();
    }
}
