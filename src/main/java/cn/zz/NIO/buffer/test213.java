package cn.zz.NIO.buffer;

import java.nio.ByteBuffer;

public class test213 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        byteBuffer.put(0,(byte)'M').put((byte)'w');
        byteBuffer.flip();
        byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');
        byteBuffer.rewind();
        byteBuffer.clear();
        /*byteBuffer.put((byte)'t');
        byteBuffer.put((byte)'t');*/
        System.out.println(byteBuffer);
        //[pos=5 lim=10 cap=10]
    }
}
