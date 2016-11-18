// For week 12
// sestoft@itu.dk * 2014-11-16

// Unbounded list-based lock-free queue by Michael and Scott 1996 (who
// call it non-blocking).

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntToDoubleFunction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class TestMSQueue extends Tests{
  public static void main(String[] args) throws Exception{
    SystemInfo();
    //sequentialTest(new MSQueue<Integer>());
    //parallelTest(new MSQueue<Integer>());
    for(int i = 1; i <= 4; i++){
      benchmark(new MSQueue<Integer>(), i, i);
    }
  }

  private static void sequentialTest(UnboundedQueue<Integer> ubq) throws Exception {
    System.out.printf("%nSequential test: %s", ubq.getClass());
    assertTrue(ubq.dequeue() == null);
    ubq.enqueue(7); ubq.enqueue(9); ubq.enqueue(13);
    assertEquals(ubq.dequeue(), 7);
    assertEquals(ubq.dequeue(), 9);
    assertEquals(ubq.dequeue(), 13);
    assertTrue(ubq.dequeue() == null);
    assertTrue(ubq.dequeue() == null);
    System.out.println("... passed");
  }

  private static void parallelTest(UnboundedQueue<Integer> ubq) throws Exception {
    System.out.printf("%nParallel test: %s", ubq.getClass()); 
    final ExecutorService pool = Executors.newCachedThreadPool();
    new EnqueueDequeueTest(ubq, 17, 1000000).test(pool); 
    pool.shutdown();
    System.out.println("... passed");
  }

  private static void benchmark(UnboundedQueue<Integer> ubq, int pc, int cc) throws Exception {
    final UnboundedQueue<Integer> queue = ubq;
    final int producerCount = pc;
    final int consumerCount = cc;
    final int iterations = 10_000;
    
    Mark7(String.format("%-21s [P:%d|C:%d]", ubq.getClass(), pc, cc),
      i -> testConcurrentQueue(ubq, producerCount, consumerCount, iterations));
  }

  private static double testConcurrentQueue(UnboundedQueue<Integer> ubq, int pc, int cc, int n){
    final ExecutorService pool = Executors.newCachedThreadPool();
    final UnboundedQueue<Integer> queue = ubq;
    final AtomicInteger nTrials = new AtomicInteger(n);
    final AtomicInteger producerCount = new AtomicInteger(pc);
    final AtomicInteger consumerCount = new AtomicInteger(cc);
    final CyclicBarrier startBarrier = new CyclicBarrier(pc + cc + 1);
    final CyclicBarrier stopBarrier = new CyclicBarrier(pc + cc + 1);
    try {
      for (int i = 0; i < pc; i++) {
        pool.execute(() -> {
          try {
            int primeCount = nTrials.get();
            startBarrier.await();
            int current = -1;
            for(int primes = 0; primes < primeCount; primes++){
              do{
                current++;
              } while(!isPrime(current));
              queue.enqueue(current);
            }

            stopBarrier.await();
          } catch(Exception e){
            throw new RuntimeException(e);
          }
        });
      }
      for (int i = 0; i < cc; i++) {
        pool.execute(() -> {
          try {
            int primeCount = nTrials.get();
            startBarrier.await();
            for(int primes = 0; primes < primeCount; primes++){
              Integer value;
              do{
                value = queue.dequeue();
              } while(value == null);
              isPrime(value);
            }
            stopBarrier.await();
          } catch(Exception e){
            throw new RuntimeException(e);
          }
        });
      }  
      startBarrier.await(); // wait for all threads to be ready
      stopBarrier.await();  // wait for all threads to finish
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    pool.shutdown();
    return 1.0;
  }

  private static boolean isPrime(int n) {
    int k = 2;
    while (k * k <= n && n % k != 0)
      k++;
    return n >= 2 && k * k > n;
  }
}

class Tests {
  public static double Mark7(String msg, IntToDoubleFunction f) {
    int n = 10, count = 1, totalCount = 0;
    double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
    do { 
      count *= 2;
      st = sst = 0.0;
      for (int j=0; j<n; j++) {
        Timer t = new Timer();
        for (int i=0; i<count; i++) 
          dummy += f.applyAsDouble(i);
        runningTime = t.check();
        double time = runningTime * 1e6 / count; // microseconds
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f us %10.2f %10d%n", msg, mean, sdev, count);
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

  public static void assertEquals(int x, int y) throws Exception {
    if (x != y) 
      throw new Exception(String.format("ERROR: %d not equal to %d%n", x, y));
  }

  public static void assertTrue(boolean b) throws Exception {
    if (!b) 
      throw new Exception(String.format("ERROR: assertTrue"));
  }
}

interface UnboundedQueue<T> {
  void enqueue(T item);
  T dequeue();
}
// ------------------------------------------------------------
// Unbounded lock-based queue with sentinel (dummy) node

class LockingQueue<T> implements UnboundedQueue<T> {  
  // Invariants:
  // The node referred by tail is reachable from head.
  // If non-empty then head != tail, 
  //    and tail points to last item, and head.next to first item.
  // If empty then head == tail.

  private static class Node<T> {
    final T item;
    Node<T> next;
    
    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
  }

  private Node<T> head, tail;

  public LockingQueue() {
    head = tail = new Node<T>(null, null);
  }
  
  public synchronized void enqueue(T item) { // at tail
    Node<T> node = new Node<T>(item, null);
    tail.next = node;
    tail = node;
  }

  public synchronized T dequeue() {     // from head
    if (head.next == null) 
      return null;
    Node<T> first = head;
    head = first.next;
    return head.item;
  }
}


// ------------------------------------------------------------
// Unbounded lock-free queue (non-blocking in M&S terminology), 
// using CAS and AtomicReference

// This creates one AtomicReference object for each Node object.  The
// next MSQueueRefl class further below uses one-time reflection to
// create an AtomicReferenceFieldUpdater, thereby avoiding this extra
// object.  In practice the overhead of the extra object apparently
// does not matter much.

class MSQueue<T> implements UnboundedQueue<T> {
  private final AtomicReference<Node<T>> head, tail;

  public MSQueue() {
    Node<T> dummy = new Node<T>(null, null);
    head = new AtomicReference<Node<T>>(dummy);
    tail = new AtomicReference<Node<T>>(dummy);
  }

  public void enqueue(T item) { // at tail
    Node<T> node = new Node<T>(item, null);
    while (true) {
      Node<T> last = tail.get(), next = last.next.get();
      //if (last == tail.get()) {         // E7
        if (next == null)  {
          // In quiescent state, try inserting new node
          if (last.next.compareAndSet(next, node)) { // E9
            // Insertion succeeded, try advancing tail
            tail.compareAndSet(last, node);
            return;
          }
        } else 
          // Queue in intermediate state, advance tail
          tail.compareAndSet(last, next);
      //}
    }
  }

  public T dequeue() { // from head
    while (true) {
      Node<T> first = head.get(), last = tail.get(), next = first.next.get(); // D3
      //if (first == head.get()) {        // D5
        if (first == last) {
          if (next == null)
            return null;
          else
            tail.compareAndSet(last, next);
        } else {
          T result = next.item;
          if (head.compareAndSet(first, next)) // D13
            return result;
        }
      //}
    }
  }

  private static class Node<T> {
    final T item;
    final AtomicReference<Node<T>> next;

    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = new AtomicReference<Node<T>>(next);
    }
  }
}

class Timer {
  private long start, spent = 0;
  public Timer() { play(); }
  public double check() { return (System.nanoTime()-start+spent)/1e9; }
  public void pause() { spent += System.nanoTime()-start; }
  public void play() { start = System.nanoTime(); }
}

// --------------------------------------------------
// Lock-free queue, using CAS and reflection on field Node.next

class MSQueueRefl<T> implements UnboundedQueue<T> {
  private final AtomicReference<Node<T>> head, tail;

  public MSQueueRefl() {
    // Essential to NOT make dummy a field as in Goetz p. 334, that
    // would cause a memory management disaster, huge space leak:
    Node<T> dummy = new Node<T>(null, null);
    head = new AtomicReference<Node<T>>(dummy);
    tail = new AtomicReference<Node<T>>(dummy);
  }

  @SuppressWarnings("unchecked") 
  // Java's @$#@?!! generics type system: abominable unsafe double type cast
  private final AtomicReferenceFieldUpdater<Node<T>, Node<T>> nextUpdater 
    = AtomicReferenceFieldUpdater.newUpdater((Class<Node<T>>)(Class<?>)(Node.class), 
                                             (Class<Node<T>>)(Class<?>)(Node.class), 
                                             "next");    

  public void enqueue(T item) { // at tail
    Node<T> node = new Node<T>(item, null);
    while (true) {
      Node<T> last = tail.get(), next = last.next;
      if (last == tail.get()) {         // E7
        if (next == null)  {
          // In quiescent state, try inserting new node
          if (nextUpdater.compareAndSet(last, next, node)) {
            // Insertion succeeded, try advancing tail
            tail.compareAndSet(last, node);
            return;
          }
        } else {
          // Queue in intermediate state, advance tail
          tail.compareAndSet(last, next);
        }
      }
    }
  }
  
  public T dequeue() { // from head
    while (true) {
      Node<T> first = head.get(), last = tail.get(), next = first.next;
      if (first == head.get()) {        // D5
        if (first == last) {
          if (next == null)
            return null;
          else
            tail.compareAndSet(last, next);
        } else {
          T result = next.item;
          if (head.compareAndSet(first, next)) {
            return result;
          }
        }
      }
    }
  }

  private static class Node<T> {
    final T item;
    volatile Node<T> next;

    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
  }
}

class EnqueueDequeueTest extends Tests {
  // We could use one CyclicBarrier for both starting and stopping,
  // precisely because it is cyclic, but the code becomes clearer by
  // separating them:
  protected CyclicBarrier startBarrier, stopBarrier;
  protected final UnboundedQueue<Integer> ubq;
  protected final int nTrials, nPairs;
  protected final AtomicInteger enqueueSum = new AtomicInteger(0);
  protected final AtomicInteger dequeueSum = new AtomicInteger(0);

  public EnqueueDequeueTest(UnboundedQueue<Integer> ubq, int npairs, int ntrials) {
    this.ubq = ubq;
    this.nTrials = ntrials;
    this.nPairs = npairs;
    this.startBarrier = new CyclicBarrier(npairs * 2 + 1);
    this.stopBarrier = new CyclicBarrier(npairs * 2 + 1);
  }
  
  void test(ExecutorService pool) {
    try {
      for (int i = 0; i < nPairs; i++) {
        pool.execute(new Producer());
        pool.execute(new Consumer());
      }      
      startBarrier.await(); // wait for all threads to be ready

      Timer t = new Timer();
      stopBarrier.await();  // wait for all threads to finish
      double runningTime = t.check();
      System.out.printf("Runningtime: %15.8f s", runningTime);

      assertTrue(ubq.dequeue() == null); // Check empty
      assertEquals(enqueueSum.get(), dequeueSum.get());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void testSilent(ExecutorService pool) {
    try {
      for (int i = 0; i < nPairs; i++) {
        pool.execute(new Producer());
        pool.execute(new Consumer());
      }      
      startBarrier.await(); // wait for all threads to be ready
      stopBarrier.await();  // wait for all threads to finish
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  class Producer implements Runnable {
    public void run() {
      try {
        Random random = new Random();
        int sum = 0;
        startBarrier.await();
        for (int i = nTrials; i > 0; --i) {
          int item = random.nextInt();
          ubq.enqueue(item);
          sum += item;
        }
        enqueueSum.getAndAdd(sum);
        stopBarrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
  
  class Consumer implements Runnable {
    public void run() {
      try {
        startBarrier.await();
        int sum = 0;
        for (int i = nTrials; i > 0; --i) {
          Integer v;
          do {
            v = ubq.dequeue();
          } while(v == null);
          sum += v;
        }
        dequeueSum.getAndAdd(sum);
        stopBarrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
