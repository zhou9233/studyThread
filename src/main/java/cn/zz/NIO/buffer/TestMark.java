package cn.zz.NIO.buffer;

import java.nio.ByteBuffer;

public class TestMark {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        byteBuffer.position(2).mark();
        byteBuffer.position(2);
        //remaining 函数告知 从当前位置到上界还剩余的元素数目
        System.out.println(byteBuffer.remaining());
    }
}
