package cn.zz.NIO.compareBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class TestIONIO {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\testIO\\1.mp4");
        //3929685000
        /*long fileLength1 = readByFis(file);
        System.out.println(fileLength1);*/
        /*long fileLength2 = readByBos(file);
        System.out.println(fileLength2);*/
        /*long fileLength3 = readByChannel(file);
        System.out.println(fileLength3);*/
        /*long fileLength4 = readByDirectChannel(file);
        System.out.println(fileLength4);*/
        /*long fileLength5 = readByAssChannel(file);
        System.out.println(fileLength5);*/
        long fileLength6 = readByMapChannel(file);
        System.out.println(fileLength6);
    }

    // IO 字节io
    private static long readByFis(File file) throws FileNotFoundException, IOException {
        //读取3G文件在 4m内存下耗时4.5s
        //            8m内存    2.9s
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        InputStream is = new FileInputStream(file);

        byte[] buff = new byte[4096];
        long counts = 0;
        int offset = 0;
        while ((offset = is.read(buff)) != -1) {
            counts = counts + offset;
        }
        is.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

    // IO 字节缓冲io
    private static long readByBos(File file) throws FileNotFoundException, IOException {
        //BufferedInputStream 字符处理流，自带缓冲区
        // 增大字节数组 4M - 8M 性能提升不大， 3s - 2.9s
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        long counts = 0;
        int offset = 0;
        BufferedInputStream bos = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[4096];
        while((offset= bos.read(buff)) != -1) {
            counts = counts + offset;
        }
        bos.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

    private static long readByChannel(File file) throws FileNotFoundException, IOException {
        //不使用直接内存，和fileInputStream差不多
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        long counts = 0;
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bbuf = ByteBuffer.allocate(8192);
        int offset = 0;
        while((offset = fc.read(bbuf)) != -1) {
            counts = counts + offset;
            bbuf.clear();
        }
        fc.close();
        fis.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

    private static long readByDirectChannel(File file) throws FileNotFoundException, IOException {
        //使用直接内存，和fileInputStream差不多
        //4m提升0.2s,8m提升0.5s
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        long counts = 0;
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bbuf = ByteBuffer.allocateDirect(8192);
        int offset = 0;
        while((offset = fc.read(bbuf)) != -1) {
            counts = counts + offset;
            bbuf.clear();
        }
        fc.close();
        fis.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

    private static long readByAssChannel(File file) throws FileNotFoundException, IOException {
        //和 NIO fileInputStream差不多
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        long counts = 0;

        RandomAccessFile fis = new RandomAccessFile(file,"r");
        FileChannel fc = fis.getChannel();
        ByteBuffer bbuf = ByteBuffer.allocateDirect(8192);

        int offset = 0;
        while((offset = fc.read(bbuf)) != -1) {
            counts = counts + offset;
            bbuf.clear();
        }
        fc.close();
        fis.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

    private static long readByMapChannel(File file) throws FileNotFoundException, IOException {
        //使用直接内存，和fileInputStream差不多
        //4m提升0.2s,8m提升0.5s
        Date date1 = new Date();
        long start = date1.getTime();
        System.out.println(start);
        long counts = 0;
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bbuf = ByteBuffer.allocateDirect(4096);
        fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        int offset = 0;
        while((offset = fc.read(bbuf)) != -1) {
            counts = counts + offset;
            bbuf.clear();
        }
        fc.close();
        fis.close();
        Date date2 = new Date();
        long stop = date2.getTime();
        System.out.println(stop);
        System.out.println(stop-start);
        return counts;
    }

}
