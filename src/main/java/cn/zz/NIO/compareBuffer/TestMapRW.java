package cn.zz.NIO.compareBuffer;


import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TestMapRW {
    public static void main(String[] args) throws Exception{
        //C:\Users\Administrator\Desktop\websocketBim\test2
        //F:\testIO\1.mp4
        RandomAccessFile acf = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\test2","rw");
        FileChannel fc = acf.getChannel();
        Long size = fc.size();
        byte[] bs = new byte[4096];
        long len = bs.length;
        if (len>size) {
            len = size;
        }
        long offset = 0;
        while ((offset+len) <= size) {
            MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, len);
            mbuf.get(bs);
            offset = offset + len;
            if ((offset + len) > size) {
                len = size - offset;
            }
        }
    }
}
