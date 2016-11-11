import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.function.IntToDoubleFunction;
import java.util.Random;

// Timing a standard Java Class Library ReentrantLock
import java.util.concurrent.locks.ReentrantLock;

public class TestSimpleRWTryLock {
  public static void main(String[] args) {
    testLocksSequential();
    //testLocksConcurrent();
  }

  private static void testLocksSequential() {
      final SimpleRWTryLock lock = new SimpleRWTryLock();
      
      assert lock.writerTryLock();
      assert !lock.readerTryLock();
      lock.writerUnlock();
      try { lock.readerUnlock(); assert false; } catch (RuntimeException exn) { }

      assert lock.readerTryLock();
      assert !lock.writerTryLock();
      lock.readerUnlock();
      try { lock.writerUnlock(); assert false; } catch (RuntimeException exn) { }
  }

  private static void testLocksConcurrent(){
    final Random r = new Random();
    final int threadCount = 10000;
    final SimpleRWTryLock lock = new SimpleRWTryLock();
    for(int t = 0; t < threadCount; t++){
      final int thread = t;
      new Thread(() -> {
        boolean iswriter = r.nextBoolean();
        boolean success = false;
        try {
          sleepWrapper(r.nextInt(1000));
          if(iswriter){
              success = lock.writerTryLock();
            } else {
              success = lock.readerTryLock();
            }
        } finally {
            sleepWrapper(r.nextInt(1000));
            if(iswriter){
              if(success){
                lock.writerUnlock();
              } else {
                try { lock.writerUnlock(); assert false; } catch (RuntimeException exn) { }
              }
            } else {
              if(success){
                lock.readerUnlock();
              } else {
                try { lock.readerUnlock(); assert false; } catch (RuntimeException exn) { }
              }
            }
            System.out.println("finished thread: " + thread);
          }
      }).start();
    }
  }

  private static void sleepWrapper(int time){
    try{ Thread.sleep(time); } catch (InterruptedException e){ }
  }
}

class SimpleRWTryLock {
  private final AtomicReference<Holders> holder = new AtomicReference<Holders>();

  public boolean readerTryLock() { 
    Thread t = Thread.currentThread();
    Holders oldHolder;
    ReaderList newHolder;
    do{
      oldHolder = holder.get();
      if(oldHolder != null 
         && !(oldHolder instanceof ReaderList))
        return false;
      newHolder = new ReaderList(t, (ReaderList)oldHolder);
    } while(!holder.compareAndSet(oldHolder, newHolder));
    return true;
  }

  public void readerUnlock() {  
    Thread t = Thread.currentThread();
    Holders oldHolder;
    ReaderList newHolder;
    do{
      oldHolder = holder.get();
      if(oldHolder == null 
         || !(oldHolder instanceof ReaderList)
         || !((ReaderList)oldHolder).contains(t)){
        throw new RuntimeException("Readlock not held by current thread");
      }
      newHolder = ((ReaderList)oldHolder).remove(t);
    } while(!holder.compareAndSet(oldHolder, newHolder));
  }

  public boolean writerTryLock() {
    Writer writer = new Writer(Thread.currentThread());
    return holder.compareAndSet(null, writer);
  }

  public void writerUnlock() { 
    Holders writer = holder.get();
    if( writer != null
      && (writer instanceof Writer
        && ((Writer) writer).getThread().equals(Thread.currentThread()))){
      holder.compareAndSet(writer, null);
    } else {
      throw new RuntimeException("WriteLock not held by current thread");  
    }
  }

  private static abstract class Holders { }

  private static class ReaderList extends Holders {
    private final ReaderList next;
    private final Thread thread;

    public ReaderList(Thread t, ReaderList n){
      thread = t;
      next = n;
    }

    public ReaderList getNext(){
      return next;
    }

    public Thread getThread(){
      return thread;
    }

    public boolean contains(Thread t) { 
      return thread.equals(t) || (next != null && next.contains(t));
    }

    public ReaderList remove(Thread t) {
      if(t.equals(thread))
        return next;
      else if(next != null)
        return this;
      else
        return new ReaderList(thread, next.remove(t));
    }
  }

  private static class Writer extends Holders {
    private final Thread thread;
    public Writer(Thread t){
      thread = t;
    }

    public Thread getThread(){
      return thread;
    }
  }
}