package threadsexercise2;

import java.util.ArrayList;
import java.util.List;

public class FibonacciTask implements Runnable {

    private long tal;

    private List<FibonacciObserver> observers = new ArrayList();

    public void registerFibonacciObserver(FibonacciObserver o) {
        observers.add(o);
    }

    public FibonacciTask(long n) {
        this.tal = n;
    }

    @Override
    public void run() {
        //Call the Fibonacci method from here
        //long tal = ......
        System.out.println(tal);
        long res = calcFib(tal);
        System.out.println(res);
        for (FibonacciObserver observer : observers) {
            observer.dataReady(res);
        }
    }

    private long calcFib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return calcFib(n - 1) + calcFib(n - 2);
        }

    }

}
