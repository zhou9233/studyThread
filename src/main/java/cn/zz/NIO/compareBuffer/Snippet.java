package cn.zz.NIO.compareBuffer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

public class Snippet {
    public void readResource() {
        long fileLength = 0;
        final int BUFFER_SIZE = 0x300000;// 3M的缓冲

        //for (String fileDirectory : this.readResourceDirectory())// 得到文件存放路径，我这里使用了一个方法从XML文件中读出文件的
        // 存放路径，当然也可以用绝对路径来代替这里的fileDriectory
        //{
        //C:\Users\Administrator\Desktop\蓝灰色IFC\b.ifc
        File file = new File("F:\\testIO\\1.mp4");
        fileLength = file.length();
        try {
            MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r")
                    .getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
                            fileLength);// 读取大文件

            byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

            for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {
                if (fileLength - offset >= BUFFER_SIZE) {
                    for (int i = 0; i < BUFFER_SIZE; i++)
                        dst[i] = inputBuffer.get(offset + i);
                } else {
                    for (int i = 0; i < fileLength - offset; i++)
                        dst[i] = inputBuffer.get(offset + i);
                }
                // 将得到的3M内容给Scanner，这里的XXX是指Scanner解析的分隔符
                Scanner scan = new Scanner(new ByteArrayInputStream(dst))
                        .useDelimiter(" ");
                while (scan.hasNext()) {
                    // 这里为对读取文本解析的方法
                    System.out.print(scan.next() + " ");
                }
                scan.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}
    }

    protected void head() {
        //512m
        long length = 1L << 29;
        //4g
        long _4G = 1L << 32;
        long cur = 0L;
        try {
            MappedByteBuffer mappedByteBuffer;
            Random random = new Random();
            File tempFile = new File("C:\\Users\\Administrator\\Desktop\\websocketBim\\tempFile");
            tempFile.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\test2", "rw");
            FileChannel fac = randomAccessFile.getChannel();
            mappedByteBuffer = fac.map(FileChannel.MapMode.READ_WRITE, cur, fac.size());

            dumpBuffer("test", mappedByteBuffer);

            RandomAccessFile randomAccessFile2 = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\tempFile", "rw");
            FileChannel fac2 = randomAccessFile2.getChannel();
            MappedByteBuffer mappedByteBuffer2 = fac2.map(FileChannel.MapMode.READ_WRITE, cur, fac.size());
            while (mappedByteBuffer.position() < mappedByteBuffer.limit()) {
                mappedByteBuffer2.put(mappedByteBuffer.get());
            }
				/*mappedByteBuffer = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\test2", "rw").getChannel()
						.map(FileChannel.MapMode.READ_WRITE, cur, length);*/



				/*IntBuffer intBuffer = mappedByteBuffer.asIntBuffer();
				while (intBuffer.position() < intBuffer.capacity()) {
					//intBuffer.put(random.nextInt());
					int i = intBuffer.get();
				}*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dumpBuffer(String prefix, ByteBuffer buffer)
            throws Exception {
        System.out.print(prefix + ": '");

        int nulls = 0;
        int limit = buffer.limit();

        for (int i = 0; i < limit; i++) {
            char c = (char) buffer.get(i);

            if (c == '\u0000') {
                nulls++;
                continue;
            }

            if (nulls != 0) {
                System.out.print("|[" + nulls
                        + " nulls]|");
                nulls = 0;
            }

            System.out.print(c);
        }

        System.out.println("'");
    }

    public static void main(String[] args) {
        Snippet sp = new Snippet();
        //sp.readResource();
        sp.head();
    }
}