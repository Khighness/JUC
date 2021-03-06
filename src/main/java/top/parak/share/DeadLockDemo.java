package top.parak.share;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author KHighness
 * @since 2021-04-22
 */

@Slf4j(topic = "DeadLock")
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object A = new Object();
        final Object B = new Object();
        new Thread(() -> {
            synchronized (A) {
                log.debug("lock A");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    log.debug("lock B");
                }
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (B) {
                log.debug("lock B");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    log.debug("lock A");
                }
            }
        }, "t2").start();
    }
}
