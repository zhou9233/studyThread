package cn.zz.testFileThread;

public class FileCopyTest {

    public static void main(String[] args) {

        FileCopy cf = new FileCopy("E:\\testFile\\1.txt","E:\\testFile\\test\\1.txt");
        /*FileCopy cf2 = new FileCopy("E:\\testFile\\2.txt","E:\\testFile\\test\\2.txt");
        FileCopy cf3 = new FileCopy("E:\\testFile\\3.txt","E:\\testFile\\test\\3.txt");*/
        cf.start();
        /*cf2.start();
        cf3.start();*/
        
    }

}