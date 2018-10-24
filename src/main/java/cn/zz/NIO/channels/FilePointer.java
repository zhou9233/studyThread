package cn.zz.NIO.channels;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * Test file pointer manipulation between FileChannel and RandomAccessFile
 * objects.
 * <p>
 * Created Feb 2002
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: FilePointer.java,v 1.1 2002/02/20 03:16:33 ron Exp $
 */
public class FilePointer {

    private static final String DEMOGRAPHIC = "C:\\Users\\Administrator\\Desktop\\websocketBim\\1441795\\tree.json";


    public static void main(String[] argv)
            throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(DEMOGRAPHIC, "r");

        randomAccessFile.seek(1000);

        FileChannel fileChannel = randomAccessFile.getChannel();

        // This will print "1000"
        System.out.println("file pos: " + fileChannel.position());

        randomAccessFile.seek(500);

        // This will print "500"
        System.out.println("file pos: " + fileChannel.position());

        fileChannel.position(0);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1000)/*.order (ByteOrder.BIG_ENDIAN)*/;
        fileChannel.read(byteBuffer);
        byteBuffer.position(202);
        //CharBuffer charBuffer = byteBuffer.asCharBuffer();
        CharBuffer charBuffer=Charset.forName("UTF-16").decode(byteBuffer);
        byteBuffer.position(0);
                // This will print "200"
        System.out.println("file pos: " + randomAccessFile.getFilePointer());
    }
}