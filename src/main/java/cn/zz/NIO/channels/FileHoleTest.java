package cn.zz.NIO.channels;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileHoleTest {
    public static void main(String[] args) throws Exception{
        File temp = new File("C:\\\\Users\\\\Administrator\\\\Desktop\\\\websocketBim\\\\1441795\\\\test");
        RandomAccessFile file = new RandomAccessFile(temp, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        channel.read(byteBuffer);
    }
}
