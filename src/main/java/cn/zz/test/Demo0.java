package cn.zz.test;

/**
当类被引用的加载，类只会加载一次

        类的主动引用（一定会发生类的初始化）
        new一个类的对象
        调用类的静态成员（除了final常量）和静态方法
        使用java.lang.reflect包的方法对类进行反射调用
        当虚拟机启动，java Demo01,则一定会初始化Demo01类，说白了就是先启动main方法所在的类
        当初始化一个类，如果其父类没有被初始化，则先初始化它父类
        类的被动引用（不会发生类的初始化）
        当访问一个静态域时，只有真正声名这个域的类才会被初始化
        通过子类引用父类的静态变量，不会导致子类初始化
        通过数组定义类的引用，不会触发此类初始化
        引用常量不会触发此类的初始化（常量在编译阶段就存入调用类的常量池中了）*/


public class Demo0 {
    public static void main(String[] args) {
        /*A a = new A();
        System.out.println(a.width);*/
        System.out.println(AB.height);
    }
}

class AB{
    public static int width=100; //静态变量，静态域 field
    public static final int height=200; //静态常量
    static{
        System.out.println("静态初始化类A"+width);
        width = 300 ;
        System.out.println("静态初始化类A"+width);
    }
    public AB() {
        System.out.println("创建A类的对象");
    }
}