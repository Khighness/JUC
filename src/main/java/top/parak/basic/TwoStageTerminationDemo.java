package top.parak.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author KHighness
 * @since 2021-04-06
 */

public class TwoStageTerminationDemo {
    public static void main(String[] args) throws InterruptedException {
        TwoStageTermination tst = new TwoStageTermination();
        tst.start();
        Thread.sleep(3500);
        tst.stop();
    }
}

@Slf4j(topic = "TwoStageTermination")
class TwoStageTermination {
     Thread monitor;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                // 正在运行的线程被打断，直接设置打断标记
                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("执行监控...");
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                    // 正在运行的线程被打断，会被清除打断标记，需要重新设置设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    // 停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
