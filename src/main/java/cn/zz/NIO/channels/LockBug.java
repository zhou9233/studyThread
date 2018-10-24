package cn.zz.NIO.channels;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Tickle bug in 1.4.0 with lock() method.
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 */
public class LockBug
{
    public static void main (String [] argv)
		throws Exception
	{

		String filename = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\websocketBim\\\\1441795\\\\testlo";

		RandomAccessFile raf = new RandomAccessFile (filename, "rw");
		FileChannel fc = raf.getChannel();

		FileLock lock = fc.lock();

		System.out.println ("Exiting");
	}
}
