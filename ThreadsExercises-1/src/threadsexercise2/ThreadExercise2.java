package threadsexercise2;

public class ThreadExercise2 {

    static class Next extends Thread {

        public void run() {
            for (int i = 0; i < 10000; i++) {
                int even = Even.next();
                    if (even % 2 != 0) {
                        System.out.println("Not even");
                    }
                        
       
            }
        }
    }

    public static void main(String[] args) {
        Next thread1 = new Next();
        Next thread2 = new Next();

        thread1.start();
        thread2.start();
    }

}

