package cn.zz.NIO.channels;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.SelectableChannel;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.util.Iterator;

/**
 * Test select return value.
 * Start this server, then connect to port 1234.  The incoming
 * connection will be registered with the selector but never read.
 * Type something on the conection, the selector will see the channel
 * ready but the channel is never serviced in the loop.  Select will
 * return 1 forever after.
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: SelectTest.java,v 1.1 2002/05/20 07:24:29 ron Exp $
 */
public class SelectTest
{
	public static int PORT_NUMBER = 1234;

	public static void main (String [] argv)
		throws Exception
	{
		ServerSocketChannel ssc = ServerSocketChannel.open();
		Selector selector = Selector.open();

		ssc.socket().bind (new InetSocketAddress (PORT_NUMBER));
		ssc.configureBlocking (false);
		// selector.keys() 返回已经注册过的键，不能修改已注册的键
		// register()方法的第二个参数是“interest集合”，表示选择器所关心的通道操作，
		// 它实际上是一个表示选择器在检查通道就绪状态时需要关心的操作的比特掩码
		// 需要注意并非所有的操作在所有的可选择通道上都能被支持，
		// 比如ServerSocketChannel支持Accept，而SocketChannel中不支持。
		// 我们可以通过通道上的validOps()方法来获取特定通道下所有支持的操作集合。
		SelectionKey keyTest = ssc.register (selector, SelectionKey.OP_ACCEPT);
		//interest集合是Selector感兴趣的集合，用于指示选择器对通道关心的操作
		int interestSet = keyTest.interestOps();
		//read集合是通道已经就绪的操作的集合，表示一个通道准备好要执行的操作了
		int readSet = keyTest.readyOps();
		while (true) {
			//在刚初始化的Selector对象中，这三个集合都是空的。
			// 通过Selector的select（）方法可以选择已经准备就绪的通道（这些通道包含你感兴趣的的事件）。
			// 比如你对读就绪的通道感兴趣，那么select（）方法就会返回读事件已经就绪的那些通道
			//select()方法返回的int值表示有多少通道已经就绪,是自上次调用select()方法后有多少通道变成就绪状态
			//例如：首次调用select()方法，如果有一个通道变成就绪状态，返回了1，若再次调用select()方法，如果另一个通道就绪了，它会再次返回1
			int n = selector.select (1000);

			System.out.println ("selector returns: " + n);

			Iterator it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();

				// Is a new connection coming in?
				if (key.isAcceptable()) {
					ServerSocketChannel server =
						(ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();

					// set the new channel non-blocking
					channel.configureBlocking (false);

					// register it with the selector
					channel.register (selector,
						SelectionKey.OP_READ);

					it.remove();
				}

				// is there data to read on this channel?
				if (key.isReadable()) {
					System.out.println ("Channel is readable");
					// don't actually do anything
				}

				// it.remove();
			}
		}
	}
}
