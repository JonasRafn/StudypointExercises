package threadsexercise1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise1 {

    private static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());

        GetBytesFromUrl GBFU1 = new GetBytesFromUrl("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png");

        GetBytesFromUrl GBFU2 = new GetBytesFromUrl("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png");

        GetBytesFromUrl GBFU3 = new GetBytesFromUrl("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png");

        ExecutorService exe = Executors.newFixedThreadPool(3);
        long start1 = System.nanoTime();

        exe.execute(GBFU1);
        exe.execute(GBFU2);
        exe.execute(GBFU3);

        exe.shutdown();
        exe.awaitTermination(10, TimeUnit.SECONDS);
        long end1 = System.nanoTime();
        System.out.println("Time Threads: " + (end1 - start1));

        System.out.println("Sum: " + sum);

        long start2 = System.nanoTime();
        GBFU1.run();
        GBFU2.run();
        GBFU3.run();

        long end2 = System.nanoTime();
        System.out.println("Time Sequental: " + (end2 - start2));

 
    }

    public static void incSum(int i) {
        sum += i;
    }

    static class GetBytesFromUrl extends Thread {

        private String url;
        private byte[] bytes;
        private int sum = 0;

        public void run() {

            bytes = getBytesFromUrl(url);

            for (byte a : bytes) {
                sum += a;
            }
//            System.out.println("Url byte sum: " + sum);
            incSum(sum);
        }

        public GetBytesFromUrl(String url) {
            this.url = url;
        }

        public int getSum() {
            return sum;
        }

        protected byte[] getBytesFromUrl(String url) {
            ByteArrayOutputStream bis = new ByteArrayOutputStream();
            try {
                InputStream is = new URL(url).openStream();
                byte[] bytebuff = new byte[4096];
                int read;
                while ((read = is.read(bytebuff)) > 0) {
                    bis.write(bytebuff, 0, read);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
            return bis.toByteArray();
        }
    }

}
