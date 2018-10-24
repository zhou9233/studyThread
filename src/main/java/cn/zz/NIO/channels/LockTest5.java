package cn.zz.NIO.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 * Test locking with FileChannel.
 * Run one copy of this code with arguments "-w /tmp/locktest.dat"
 * and one or more copies with "-r /tmp/locktest.dat" to see the
 * interactions of exclusive and shared locks.  Note how too many
 * readers can starve out the writer.
 * Note: The filename you provide will be overwritten.  Substitute
 * an appropriate temp filename for your favorite operating system.
 *
 * Created April, 2002
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: LockTest.java,v 1.2 2002/05/19 04:55:45 ron Exp $
 */
/*
	开两个进程请求锁
	通过测试锁只和文件相关
 */
public class LockTest5
{
    private static final int SIZEOF_INT = 4;
	private static final int INDEX_START = 0;
	private int WRITE_START = 0;
	private static final int INDEX_COUNT = 10;
	private static final int INDEX_SIZE = INDEX_COUNT * SIZEOF_INT;

	private ByteBuffer buffer = ByteBuffer.allocate (INDEX_SIZE);
	private IntBuffer indexBuffer = buffer.asIntBuffer();
	private Random rand = new Random();

	public static void main (String [] argv)
		throws Exception
	{
		boolean writer = false;
		String filename;

		/*if (argv.length != 2) {
			System.out.println ("Usage: [ -r | -w ] filename");
			return;
		}*/

		//writer = argv [0].equals ("-w");
		//writer设置为可写
		writer = true;
		//filename = argv [1];
		filename = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\websocketBim\\\\1441795\\\\testlo";

		RandomAccessFile raf = new RandomAccessFile (filename,
			(writer) ? "rw" : "r");
		FileChannel fc = raf.getChannel();
		FileLock lock = fc.lock (10,
				10, false);
		System.out.println("LockTest5获取");
		while (true) {

		}
	}


}