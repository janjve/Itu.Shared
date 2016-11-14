// For week 2
// sestoft@itu.dk * 2014-09-04
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.LongAdder;


class SimpleHistogram {
  public static void main(String[] args) {
    final int range = 5_000_000;
    final int threadCount = 10;

    Histogram histogram = countParallel(range, threadCount);
    
    //dump(histogram);
  }

  public static void dump(Histogram histogram)
  {
    int totalCount = 0;
    for (int bin = 0; bin < histogram.getSpan(); bin++)
    {
      System.out.printf("%4d: %9d%n", bin, histogram.getCount(bin));
      totalCount += histogram.getCount(bin);
    }
    System.out.printf("      %9d%n", totalCount);
  }

  private static Histogram countParallel(int range, int threadCount) {
    final int perThread = range / threadCount;
    final Histogram histogram = new Histogram2(30);
    Thread[] threads = new Thread[threadCount];
    for (int t=0; t<threadCount; t++) {
      final int from = perThread * t;
      final int to = (t+1==threadCount) ? range : perThread * (t+1);

      threads[t] = new Thread(() -> {
        for (int i=from; i<to; i++)
          histogram.increment(TestCountFactors.countFactors(i));
      });
    }
    Timer timer = new Timer();
    for (int t=0; t<threadCount; t++)
      threads[t].start();
    try {
      for (int t=0; t<threadCount; t++)
        threads[t].join();
    } catch (InterruptedException exn) { }
    double runningTime = timer.check();
    dump(histogram);
    System.out.printf("Runningtime: %15.8f s", runningTime);
    return histogram;
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
  public void increment(int bin);
  public int getCount(int bin);
  public int getSpan();
  public int[] getBins();
}

class Histogram1 implements Histogram {
  private int[] counts;
  public Histogram1(int span) {
    this.counts = new int[span];
  }
  public void increment(int bin) {
    counts[bin] = counts[bin] + 1;
  }
  public int getCount(int bin) {
    return counts[bin];
  }
  public int getSpan() {
    return counts.length;
  }
  public int[] getBins(){
    return null; // Making the compiler happy.
  }
}

class Histogram2 implements Histogram {
  private final int[] counts;
  public Histogram2 (int span){this.counts = new int[span];}
  public void increment(int bin){synchronized(this) {counts[bin] = counts[bin] + 1;}}
  public int getCount(int bin){synchronized (this) {return counts[bin];}}
  public int getSpan() {return counts.length;}
  public int[] getBins(){
    synchronized(this){
      return counts.clone();
    }
  }
}

class Histogram3 implements Histogram {
  private final AtomicInteger[] counts;

  public Histogram3(int span){
    this.counts = new AtomicInteger[span];
    for(int i = 0; i < span; i++){
      this.counts[i] = new AtomicInteger();
    }
  }

  public void increment(int bin){
    counts[bin].incrementAndGet();
  }

  public int getCount(int bin){
    return counts[bin].get();
  }

  public int getSpan() {return counts.length;}

  public int[] getBins(){
    int[] result = new int[counts.length];
    for(int i = 0; i < counts.length; i++){
      result[i] += counts[i].get();
    }
    return result;  }
}

class Histogram4 implements Histogram {
  private AtomicIntegerArray counts;

  public Histogram4(int span){
    this.counts = new AtomicIntegerArray(span);
  }

  public void increment(int bin){
    counts.incrementAndGet(bin);
  }

  public int getCount(int bin){
    return counts.get(bin);
  }

  public int getSpan() {return counts.length();}

  public int[] getBins(){
    int[] result = new int[counts.length()];
    for(int i = 0; i < counts.length(); i++){
      result[i] += counts.get(i);
    }
    return result;
  }
}

class Histogram5 implements Histogram {
  private LongAdder[] counts;

  public Histogram5(int span){
    this.counts = new LongAdder[span];
    for(int i = 0; i < span; i++){
      this.counts[i] = new LongAdder();
    }
  }

  public void increment(int bin){
    counts[bin].increment();
  }

  public int getCount(int bin){
    return (int)counts[bin].sum();
  }

  public int getSpan() {return counts.length;}

  public int[] getBins(){
    int[] result = new int[counts.length];
    for(int i = 0; i < counts.length; i++){
      result[i] += counts[i].sum();
    }
    return result;
  }
}