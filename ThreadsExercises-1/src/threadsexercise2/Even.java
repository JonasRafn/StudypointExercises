package threadsexercise2;

public class Even {
    private static int n = 0;

        public static synchronized int next() {
            n++;
            n++;
            return n;
        }
}
