import java.util.Arrays;

/**
 * Created by Tempe on 16/09/2016.
 */
public class ParallelArray {
    int[] array;

    public ParallelArray() {
        array = new int[10_000_001];
        Arrays.parallelSetAll(array, i -> isPrime(i)? 1 : 0);
        Arrays.parallelPrefix(array, (x,y) -> x + y);
    }

    private static boolean isPrime(int n) {
        int k = 2;
        while (k * k <= n && n % k != 0)
            k++;
        return n >= 2 && k * k > n;
    }

}

class Program {
    public static void main(String[] args) {
        ParallelArray a = new ParallelArray();
        System.out.println(a.array[10_000_000]);
        int n = 10_000_001;

        for(int i = n/10; i < n; i+=n/10) {
            System.out.println(a.array[i] / (i/Math.log(i)));
        }
    }
}
