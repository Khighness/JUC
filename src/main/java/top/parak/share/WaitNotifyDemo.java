package top.parak.share;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author KHighness
 * @since 2021-04-10
 */

@Slf4j(topic = "WaitNotify")
public class WaitNotifyDemo {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行...");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码...");
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行...");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其它代码...");
            }
        }, "t2").start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("唤醒obj上其他线程");
        synchronized (obj) {
//            obj.notify();     // (1)
            obj.notifyAll();  // (2)
        }
    }
}
