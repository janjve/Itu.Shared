// For week 10
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CyclicBarrier;

class TestCasHistogram {
  public static void main(String[] args) {
    countPrimeFactorsWithCasHistogram();
  }

  private static void countPrimeFactorsWithCasHistogram() {
    final Histogram total = new CasHistogram(30);
    final Histogram histogram = new CasHistogram(30);
    final int range = 4_000_000;
    final int threadCount = 10, perThread = range / threadCount;
    final CyclicBarrier startBarrier = new CyclicBarrier(threadCount + 1), 
      stopBarrier = startBarrier;
    final Thread[] threads = new Thread[threadCount];
    for (int t=0; t<threadCount; t++) {
      final int from = perThread * t, 
                  to = (t+1 == threadCount) ? range : perThread * (t+1); 
        threads[t] = 
          new Thread(() -> { 
	         try { startBarrier.await(); } catch (Exception exn) { }
	         for (int p=from; p<to; p++) 
		          histogram.increment(countFactors(p));
	         System.out.print("*");
	         try { stopBarrier.await(); } catch (Exception exn) { }
	       });
        threads[t].start();
    }
    Timer t = new Timer();
    try { startBarrier.await(); } catch (Exception exn) { }

    try { 
      /*for(int i = 0; i < 200; i++){
        Thread.sleep(30);
        total.transferBins(histogram);
      }*/
      stopBarrier.await(); 
    } catch (Exception exn) { }
    double runningTime = t.check();
    dump(histogram);
    System.out.printf("Runningtime: %15.8f s", runningTime);
    //dump(total);
  }

  public static void dump(Histogram histogram) {
    int totalCount = 0;
    for (int bin=0; bin<histogram.getSpan(); bin++) {
      System.out.printf("%4d: %9d%n", bin, histogram.getCount(bin));
      totalCount += histogram.getCount(bin);
    }
    System.out.printf("      %9d%n", totalCount);
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

class Timer {
  private long start, spent = 0;
  public Timer() { play(); }
  public double check() { return (System.nanoTime()-start+spent)/1e9; }
  public void pause() { spent += System.nanoTime()-start; }
  public void play() { start = System.nanoTime(); }
}

interface Histogram {
  void increment(int bin);
  int getCount(int bin);
  int getSpan();
  int[] getBins();
  int getAndClear(int bin);
  void transferBins(Histogram hist);
}

class CasHistogram implements Histogram {
  private final AtomicInteger[] counts;

  public CasHistogram(int span) {
    counts = new AtomicInteger[span];
    for(int i = 0; i < span; i++){
      counts[i] = new AtomicInteger(0);
    }
  }

  public void increment(int bin) {
    int oldValue;
    int newValue;
    do{
      oldValue = counts[bin].get();
      newValue = oldValue + 1;
    } while(!counts[bin].compareAndSet(oldValue, newValue));
  }

  public int getCount(int bin) {
    return counts[bin].get();
  }

  public int getSpan() {
    return counts.length;
  }

  public int[] getBins() {
    int[] result = new int[counts.length];
    for(int i = 0; i < counts.length; i++){
      result[i] = counts[i].get();
    }
    return result;    
  }

  public int getAndClear(int bin) {
    int oldValue;
    do{
      oldValue = counts[bin].get();
    } while(!counts[bin].compareAndSet(oldValue, 0));
    return oldValue;
  }

  public void transferBins(Histogram hist) {
    for(int i = 0; i < hist.getSpan(); i++){
      final int bin = i;
      int transfer = 0;
      int newValue, oldValue;
      do{
        transfer += hist.getAndClear(bin);
        oldValue = counts[bin].get();
        newValue = oldValue + transfer;
      } while(!counts[bin].compareAndSet(oldValue, newValue));
    }
  }
}

