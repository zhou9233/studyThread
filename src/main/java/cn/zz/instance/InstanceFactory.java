package cn.zz.instance;

public class InstanceFactory {
    private static class InstanceHolder {
        public static Instance instance = new Instance();
        //如果a是final类型则使用a不会导致InstanceHolder发生类初始化
        public final static int a = 1;
    }
    public static Instance getInstance(){
        return InstanceHolder.instance;
    }
    public static int getA(){
        return InstanceHolder.a;
    }
    public static void test(){
        System.out.println("test");
    }
    public static void main(String[] args) {
        InstanceFactory.getInstance();
        //InstanceFactory.test();
        //InstanceFactory.getA();
    }
    static class Instance {
        Instance (){
            System.out.println("Instance 初始化");
        }
    }
}

