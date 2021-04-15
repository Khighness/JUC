package top.parak.basic;

/**
 * @author KHighness
 * @since 2021-04-06
 */

public class FrameDemo {
    public static void main(String[] args) {
        new Thread(() -> method1(20), "t1").start();
        method1(10);
    }

    private static void method1(int x) {
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    private static Object method2() {
        Object obj = new Object();
        return obj;
    }
}
