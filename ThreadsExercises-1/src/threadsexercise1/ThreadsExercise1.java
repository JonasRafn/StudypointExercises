package threadsexercise1;

public class ThreadsExercise1 {

    static class Counter extends Thread {

        public void run() {
            long count = 0;
            for (int i = 0; i <= 1000000000; i++) {
                count += i;
            }
            System.out.println("Thread1 Sum: " + count);
        }
    }

    static class Print5 extends Thread {

        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread2 Sum: " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Error " + e);
                }

            }
        }
    }

    static class Print10 extends Thread {

        public static volatile boolean condition = true;

        public void run() {
            int count = 10;

            while (condition) {
                System.out.println("Thread3 Sum: " + count);
                count++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Error " + e);
                }
            }
        }
    }

    public static void main(String[] args) {

        Counter counter = new Counter();
        counter.start();

        Print5 print5 = new Print5();
        print5.start();

        Print10 print10 = new Print10();
        print10.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
        
        Print10.condition = false;
    }
}
