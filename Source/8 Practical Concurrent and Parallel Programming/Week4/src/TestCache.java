// For week 2

// Code from Goetz et al 5.6, written by Brian Goetz and Tim Peierls.
// Modifications by sestoft@itu.dk * 2014-09-08

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;



public class TestCache {
  public static void main(String[] args) throws InterruptedException {
    SystemInfo();
    double dummy = 0;
    for(int c=1; c <= 16; c++) {
      final int threadCount = c;
      dummy += Mark7(String.format("%s %6d", "Memoizer1", threadCount), i ->start(threadCount, i));
    }
  }

  private static long start(long tc, int i){
    final Factorizer factorizer = new Factorizer();
    final Computable<Long, long[]> cachingFactorizer = new Memoizer1<Long,long[]>(factorizer);
    return exerciseFactorizer(cachingFactorizer, tc);
  }

  private static long exerciseFactorizer(Computable<Long, long[]> f, long tc) {
    final long threadCount = tc;
    final long start = 10_000_000_000L, range = 2_000L;

    final AtomicLong result = new AtomicLong();
    Thread[] threads = new Thread[(int)threadCount];
    for (int t=0; t<threadCount; t++) {
      final long from1 = start;
      final long to1 = from1 + range;
      final long from2 = start + range + t * range / 4;
      final long to2 = from2 + range;

      threads[t] = new Thread(() -> {
        long count = 0; 
        try{
          for (long i=from1; i<to1; i++)
            count = f.compute(i)[0];

          for (long i=from2; i<to2; i++)
            count = f.compute(i)[0];
        result.addAndGet(count);
        } catch (InterruptedException e) { System.out.println("InterruptedException thrown");/* Ignored */ }
      });
    }

    for (int t=0; t<threadCount; t++)
      threads[t].start();
    try {
      for (int t=0; t<threadCount; t++)
        threads[t].join();
    } catch (InterruptedException exn) { System.out.println("InterruptedException thrown");/* Ignored */ }
    return result.get();
  }

    public static double Mark7(String msg, IntToDoubleFunction f) {
    int n = 10, count = 1, totalCount = 0;
    double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
    do { 
      count *= 2;
      st = sst = 0.0;
      for (int j=0; j<n; j++) {
        Timer t = new Timer();
        for (int i=0; i<count; i++){ 
          dummy += f.applyAsDouble(i);
          }
        runningTime = t.check();
        double time = (runningTime * 1e6) / count; // nanoseconds
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f ms %10.2f %10d%n", msg, mean, sdev, count);
    return dummy / totalCount;
  }

  public static void SystemInfo() {
    System.out.printf("# OS:   %s; %s; %s%n", 
                      System.getProperty("os.name"), 
                      System.getProperty("os.version"), 
                      System.getProperty("os.arch"));
    System.out.printf("# JVM:  %s; %s%n", 
                      System.getProperty("java.vendor"), 
                      System.getProperty("java.version"));
    // The processor identifier works only on MS Windows:
    System.out.printf("# CPU:  %s; %d \"cores\"%n", 
                      System.getenv("PROCESSOR_IDENTIFIER"),
                      Runtime.getRuntime().availableProcessors());
    java.util.Date now = new java.util.Date();
    System.out.printf("# Date: %s%n", 
      new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(now));
  }
}




// Interface that represents a function from A to V
interface Computable <A, V> {
  V compute(A arg) throws InterruptedException;
}


// Prime factorization is a function from Long to long[]
class Factorizer implements Computable<Long, long[]> {
  // For statistics only, count number of calls to factorizer:
  private final AtomicLong count = new AtomicLong(0);
  public long getCount() { return count.longValue(); }

  public long[] compute(Long wrappedP) {
    count.getAndIncrement();
    long p = wrappedP;
    ArrayList<Long> factors = new ArrayList<Long>();
    long k = 2;
    while (p >= k * k) {
      if (p % k == 0) {
	factors.add(k);
	p /= k;
      } else
	k++;
    }
    // Now k * k > p and no number in 2..k divides p
    factors.add(p);
    long[] result = new long[factors.size()];
    for (int i=0; i<result.length; i++)
      result[i] = factors.get(i);
    return result;
  }
}


/**
 * Initial cache attempt using HashMap and synchronization;
 * suffers from lack of parallelism due to coarse locking.
 * From Goetz p. 103
 * @author Brian Goetz and Tim Peierls
 */
class Memoizer1 <A, V> implements Computable<A, V> {
  private final Map<A, V> cache = new HashMap<A, V>();
  private final Computable<A, V> c;

  public Memoizer1(Computable<A, V> c) { this.c = c; }

  public synchronized V compute(A arg) throws InterruptedException {
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }
}


/**
 * Memoizer2
 * Replacing HashMap with ConcurrentHashMap for better parallelism.
 * From Goetz p. 105
 * @author Brian Goetz and Tim Peierls
 */
class Memoizer2 <A, V> implements Computable<A, V> {
  private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
  private final Computable<A, V> c;

  public Memoizer2(Computable<A, V> c) { this.c = c; }

  public V compute(A arg) throws InterruptedException {
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }
}


/**
 * Memoizer3
 * Create a Future and register in cache immediately.
 * Calls: ft.run() -> eval.call() -> c.compute(arg)
 * From Goetz p. 106
 * @author Brian Goetz and Tim Peierls
 */
class Memoizer3<A, V> implements Computable<A, V> {
  private final Map<A, Future<V>> cache
    = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer3(Computable<A, V> c) { this.c = c; }

  public V compute(final A arg) throws InterruptedException {
    Future<V> f = cache.get(arg);
    if (f == null) {
      Callable<V> eval = new Callable<V>() {
	  public V call() throws InterruptedException {
	    return c.compute(arg);
      }};
      FutureTask<V> ft = new FutureTask<V>(eval);
      cache.put(arg, ft);
      f = ft;
      ft.run();
    }
    try { return f.get(); }
    catch (ExecutionException e) { throw launderThrowable(e.getCause()); }
  }

  public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}


/**
 * Memoizer4, hybrid of variant of Goetz's Memoizer3 and Memoizer.  If
 * arg not in cache, create Future, then atomically putIfAbsent in
 * cache, then run on calling thread.
 */

class Memoizer4<A, V> implements Computable<A, V> {
  private final Map<A, Future<V>> cache
    = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer4(Computable<A, V> c) { this.c = c; }

  public V compute(final A arg) throws InterruptedException {
    Future<V> f = cache.get(arg);
    if (f == null) {
      Callable<V> eval = new Callable<V>() {
	  public V call() throws InterruptedException {
	    return c.compute(arg);
      }};
      FutureTask<V> ft = new FutureTask<V>(eval);
      f = cache.putIfAbsent(arg, ft);
      if (f == null) {
        f = ft;
        ft.run();
      }
    }
    try { return f.get(); }
    catch (ExecutionException e) { throw launderThrowable(e.getCause()); }
  }

  public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}

/**
 * Memoizer5, modern variant of Memoizer4 using the new Java 8
 * computeIfAbsent.  Atomically test whether arg is in cache and if
 * not create Future and put it there, then run on calling thread.
 */

class Memoizer5<A, V> implements Computable<A, V> {
  private final Map<A, Future<V>> cache
    = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer5(Computable<A, V> c) { this.c = c; }

  public V compute(final A arg) throws InterruptedException {
    // AtomicReference is used as a simple assignable holder; no atomicity needed
    final AtomicReference<FutureTask<V>> ftr = new AtomicReference<FutureTask<V>>();
    Future<V> f = cache.computeIfAbsent(arg, (A argv) -> {
	  Callable<V> eval = new Callable<V>() {
	      public V call() throws InterruptedException {
		return c.compute(argv);
	      }};
	  ftr.set(new FutureTask<V>(eval));
	  return ftr.get();
      });
    // Important to run() the future outside the computeIfAbsent():
    if (ftr.get() != null)
      ftr.get().run();
    try { return f.get(); }
    catch (ExecutionException e) { throw launderThrowable(e.getCause()); }
  }

  public static RuntimeException launderThrowable(Throwable t) {
    System.out.println("Exception");
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}


/**
 * Final implementation of Memoizer using cheap get() followed by
 * atomic putIfAbsent.
 * From Goetz p. 108
 * @author Brian Goetz and Tim Peierls
 */
class Memoizer <A, V> implements Computable<A, V> {
  private final ConcurrentMap<A, Future<V>> cache
    = new ConcurrentHashMap<A, Future<V>>();
  private final Computable<A, V> c;

  public Memoizer(Computable<A, V> c) { this.c = c; }

  public V compute(final A arg) throws InterruptedException {
    while (true) {
      Future<V> f = cache.get(arg);
      if (f == null) {
	Callable<V> eval = new Callable<V>() {
	    public V call() throws InterruptedException {
	      return c.compute(arg);
	    }
	  };
	FutureTask<V> ft = new FutureTask<V>(eval);
	f = cache.putIfAbsent(arg, ft);
	if (f == null) {
	  f = ft;
	  ft.run();
	}
      }
      try {
	return f.get();
      } catch (CancellationException e) {
	cache.remove(arg, f);
      } catch (ExecutionException e) {
	throw launderThrowable(e.getCause());
      }
    }
  }

  /**
   * Coerce a checked Throwable to an unchecked RuntimeException.

   * sestoft@itu.dk 2014-09-07: This method converts a Throwable
   * (which is a checked exception) into a RuntimeException (which is
   * an unchecked exception) or an IllegalStateException (which is a
   * subclass of RuntimeException and hence unchecked).  It is unclear
   * why RuntimeException and Error are treated differently; both are
   * unchecked.  A simpler (but grosser) approach is to simply throw a
   * new RuntimeException(t), thus wrapping the Throwable, but that
   * may lead to a RuntimeException containing a RuntimeException
   * which is a little strange.  The original
   * java.util.concurrent.ExecutionException that wrapped the
   * Throwable is itself checked and therefore needs to be caught and
   * turned into something less obnoxious.

   * @author Brian Goetz and Tim Peierls
   */

  public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}

class Memoizer0<A, V> implements Computable<A, V>
{
  private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
  private final Computable<A, V> c;

  public Memoizer0(Computable<A, V> c)
  {
    this.c = c;
  }

  public V compute(final A arg) throws InterruptedException
  {
      return cache.computeIfAbsent(arg, (A argv) -> {
        V v = null;
        try{
          v = c.compute(argv);
        } catch(Exception e){
          launderThrowable(e.getCause());
        }
        return v;
      });
  }

  // Cast to unchecked exception to allow exceptions in lambda. 
  public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}
