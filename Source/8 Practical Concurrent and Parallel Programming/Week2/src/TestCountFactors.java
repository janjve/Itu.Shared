// For week 2
// sestoft@itu.dk * 2014-08-29
import java.util.concurrent.atomic.AtomicInteger;

class TestCountFactors {
  /*public static void main(String[] args) {
    final int range = 5_000_000;
    int count = 0;
    for (int p=0; p<range; p++)
      count += countFactors(p);
    System.out.printf("Total number of factors is %9d%n", count);
  }*/

  public static void main(String[] args) {
    final int range = 5_000_000;
    long count = countParallel(range, 10);

    System.out.printf("Total number of factors is %9d%n", count);
  }

  private static long countParallel(int range, int threadCount) {
    final int perThread = range / threadCount;
    final MyAtomicInteger total = new MyAtomicInteger(0);
    Thread[] threads = new Thread[threadCount];
    for (int t=0; t<threadCount; t++) {
      final int from = perThread * t;
      final int to = (t+1==threadCount) ? range : perThread * (t+1);

      threads[t] = new Thread(() -> {
        for (int i=from; i<to; i++)
          total.addAndGet(countFactors(i));
      });
    }

    for (int t=0; t<threadCount; t++)
      threads[t].start();
    try {
      for (int t=0; t<threadCount; t++)
        threads[t].join();
    } catch (InterruptedException exn) { }
    return total.get();
  }

  public static int countFactors(int p) {
    if (p < 2) 
      return 0;
    int factorCount = 1, k = 2;
    while (p >= k * k) {
      if (p % k == 0) {
	factorCount++;
	p /= k;
      } else 
	k++;
    }
    return factorCount;
  }

}

class MyAtomicInteger {
  private volatile int value;

  public MyAtomicInteger (int value){
    this.value = value;
  }

  public int addAndGet(int amount){
    synchronized (this) {
      value += amount;
      return value;
    }
  }

  public int get(){
    return value;
  }
}