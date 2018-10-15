package cn.zz.NIO.buffer;

import java.nio.CharBuffer;

/**
 * Test asCharBuffer view.
 * <p>
 * Created May 2002
 *
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: BufferCharView.java,v 1.2 2002/05/20 07:24:24 ron Exp $
 */
public class TestCopyBuffer {
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
        CharBuffer dupeBuffer = buffer.duplicate();
        buffer.clear();
        buffer.position(3).limit(5);
        //这里的分割buffer是对 buffer内的hb[3],[4]进行操作，
        //实际上没有复制，只是将hb给sliceBuffer进行引用，将offset设置为3，limit cap设置为2
        CharBuffer sliceBuffer = buffer.slice();
        System.out.println(sliceBuffer.toString());
    }

}
