package cn.zz.testFileThread;

import java.io.*;
import java.text.DecimalFormat;
/**
 * 文件复制类
 * @author Administrator
 *
 */
public class FileCopy extends Thread {
    
    private File src;//待读取的源文件
    private File dest;//待写入的目标文件
    
    public FileCopy(String src,String dest){
        this.src = new File(src);
        this.dest = new File(dest);
    }

    @Override
    public void run() {
        FileInputStream is = null;
        FileOutputStream os = null;
        
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            
            byte[] b = new byte[1024];
            int length = 0;
            
            //获取源文件大小
            long len = src.length();
            //已复制文件的字节数
            double temp = 0 ; 
            //数字格式化，显示百分比
            DecimalFormat df = new DecimalFormat("##.00%");
            while((length = is.read(b))!=-1){
                //输出字节
                os.write(b, 0, length);
                //获取已下载的大小，并且转换成百分比
                temp += length;
                double d = temp/len;
                System.out.print(Thread.currentThread().getName()+" ");
                System.out.println(src.getName()+"已复制的进度："+df.format(d));
            }
            System.out.print(Thread.currentThread().getName()+" ");
            System.out.println(src.getName()+"复制完成！");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (is != null) {
                    is.close();
                } 
                if(os!=null){
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                
    }

}