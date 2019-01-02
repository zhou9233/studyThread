package cn.zz.test;

public class TestFinally {

    public static final int a = 1;

    public final Test test = new Test();
    static {
        System.out.println(a);
    }

    TestFinally(){
    }

    public static void main(String[] args) {
        TestFinally testFinally = new TestFinally();
        System.out.println(testFinally.a);
        testFinally.test.m = 2;
        //不能修改引用但是可以修改引用里的值
        //testFinally.test = new Test();
        System.out.println(testFinally.test.m);

    }
}
