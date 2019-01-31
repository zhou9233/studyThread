package cn.zz.threadConcurrent.chapter05;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UNSAObjectTest {
    private static class Node<E> {
        volatile E item;
        volatile UNSAObjectTest.Node<E> next;

        /**
         * Constructs a new node.  Uses relaxed write because item can
         * only be seen after publication via casNext.
         */
        Node(E item) {
            UNSAFE.putObject(this, itemOffset, item);
        }

        boolean casItem(E cmp, E val) {
            return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
        }

        void lazySetNext(UNSAObjectTest.Node<E> val) {
            UNSAFE.putOrderedObject(this, nextOffset, val);
        }

        boolean casNext(UNSAObjectTest.Node<E> cmp, UNSAObjectTest.Node<E> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        // Unsafe mechanics

        private static final sun.misc.Unsafe UNSAFE;
        private static final long itemOffset;
        private static final long nextOffset;

        static {
            try {
                UNSAFE = getUnsafe();
                Class<?> k = UNSAObjectTest.Node.class;
                itemOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("item"));
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        private static Unsafe getUnsafe(){

            Class<?> unsafeClass = Unsafe.class;

            for (Field f : unsafeClass.getDeclaredFields()) {

                if ("theUnsafe".equals(f.getName())) {

                    f.setAccessible(true);

                    try {
                        return (Unsafe) f.get(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
            return null;
            //throw new IllegalAccessException("no declared field: theUnsafe");

        }
    }
    public static void main(String[] args) {
        Node head1 = new Node<String>("123");
        Node head2 = new Node<String>("234");
        Node head3 = new Node<String>("345");
        Node head4 = new Node<String>("456");
        head1.casNext(null,head2);
        head2.casNext(null,head3);
        head3.casNext(null,head4);
        Node h = head1;
        Node p = h;
        Object head1Item = p.item;
        p.casItem(head1Item,null);

    }
}
