package cn.zz.NIO.compareBuffer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Snippet2 {

    protected void useMap() {
        //512m
        long length = 1L << 29;
        //4g
        long _4G = 1L << 32;
        long cur = 0L;
        try {
            MappedByteBuffer mappedByteBuffer;
            Date date1 = new Date();
            long start = date1.getTime();
            System.out.println(start);
            File tempFile = new File("C:\\Users\\Administrator\\Desktop\\websocketBim\\tempFile");
            tempFile.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\1.mp4", "rw");
            FileChannel fac = randomAccessFile.getChannel();
            mappedByteBuffer = fac.map(FileChannel.MapMode.READ_WRITE, cur, fac.size());


            RandomAccessFile randomAccessFile2 = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\tempFile", "rw");
            FileChannel fac2 = randomAccessFile2.getChannel();
            MappedByteBuffer mappedByteBuffer2 = fac2.map(FileChannel.MapMode.READ_WRITE, cur, fac.size());
            while (mappedByteBuffer.position() < mappedByteBuffer.limit()) {
                mappedByteBuffer2.put(mappedByteBuffer.get());
            }
            Date date2 = new Date();
            long stop = date2.getTime();
            System.out.println(stop);
            System.out.println(stop-start);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void useDirect() {
        //512m
        long length = 1L << 29;
        //4g
        long _4G = 1L << 32;
        long cur = 0L;
        try {
            MappedByteBuffer mappedByteBuffer;
            Date date1 = new Date();
            long start = date1.getTime();
            System.out.println(start);
            File tempFile = new File("C:\\Users\\Administrator\\Desktop\\websocketBim\\tempFile.mp4");
            tempFile.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\websocketBim\\1.mp4", "rw");
            FileChannel fac = randomAccessFile.getChannel();
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(tempFile,"rw");
            FileChannel fac2 = randomAccessFile2.getChannel();
            //测试一使用非直接缓冲区耗时 46.957s /40960 31s /40960*2
            //测试二使用直接缓冲区耗时   38.619s /40960 30s /40960*2
            ByteBuffer bbuf = ByteBuffer.allocateDirect(40960*2);
            int offset = 0;
            while ((offset = fac.read(bbuf))!=-1){
                bbuf.flip();
                while (bbuf.hasRemaining()) {
                    fac2.write(bbuf);
                }
                bbuf.clear();
            }




            fac2.close();
            randomAccessFile2.close();



            Date date2 = new Date();
            long stop = date2.getTime();
            System.out.println(stop);
            System.out.println(stop-start);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Snippet2 sp = new Snippet2();

        //sp.useMap();
        sp.useDirect();
    }
}