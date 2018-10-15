package cn.zz.NIO.buffer;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Test asCharBuffer view.
 * <p>
 * Created May 2002
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: BufferCharView.java,v 1.2 2002/05/20 07:24:24 ron Exp $
 */
public class TestCopy {
    public static void main(String[] argv)
            throws Exception {
        String string =
                "A random string value The product of an infinite number of monkeys Hey hey we're the Monkees"
        ;
        CharBuffer buffer = CharBuffer.allocate(100);
        char[] chars = new char[100];
        //将字符拷贝到缓冲区
        buffer.put(string);
        buffer.position(0);
        //将缓冲区内容拷贝到字符数组
        buffer.get(chars);
        buffer.position(0);
        System.out.println(buffer);
        //buffer2 buffer3里的chars引用的是一个对象
        CharBuffer buffer2 = CharBuffer.wrap(chars);
        CharBuffer buffer3 = CharBuffer.wrap(chars);
    }

}
