package cn.zz.NIO.channels;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

/**
 * Demonstrate asynchronous connection of a SocketChannel.
 *
 * Created: April 2002
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: ConnectAsync.java,v 1.2 2002/04/28 01:47:58 ron Exp $
 */
public class ConnectAsync
{
    public static void main (String [] argv)
		throws Exception
	{
		String host = "localhost";
		int port = 1234;

		if (argv.length == 2) {
			host = argv [0];
			port = Integer.parseInt (argv [1]);
		}

		InetSocketAddress addr = new InetSocketAddress (host, port);
		//socketChannel虽然已经打开，但是还未连接，直接进行IO操作会抛异常
		SocketChannel sc = SocketChannel.open();

		sc.configureBlocking (false);

		System.out.println ("initiating connection");

		System.out.println(sc.connect (addr));
		//调用 finishConnect( )方法来完成连接过程，该方法任何时候都可以安全地进行调用
		while ( ! sc.finishConnect()) {
			doSomethingUseful();
		}
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
		sc.read(byteBuffer);
		System.out.println ("connection established");

		// Do something with the connected socket
		// The SocketChannel is still non-blocking

		sc.close();
	}

	private static void doSomethingUseful()
	{
		System.out.println ("doing something useless");
	}
}