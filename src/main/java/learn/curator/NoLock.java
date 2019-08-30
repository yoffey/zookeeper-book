package learn.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * description: 无锁根据时间戳生成订单并发问题演示
 *
 * @author 宗永飞 (yongfei.zong@ucarinc.com)
 * @version 1.0
 * @date 2019-05-28 14:49
 */
public class NoLock {
    public static void main(String[] args) {
        final CountDownLatch count = new CountDownLatch(1);
        int length = 10;
        for (int i = 0; i < length; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        count.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.err.println("订单号是：" + orderNo);
                }
            }).start();
        }
        count.countDown();
    }
}
