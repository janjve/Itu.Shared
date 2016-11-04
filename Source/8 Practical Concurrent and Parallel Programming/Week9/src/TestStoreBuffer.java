// For week 9

// C# example from Ostrovsky: The C# Memory Model in Theory and
// Practice, in MSDN Magazine, part 2 (January 2013); test harness by
// Jacob Thamsborg; Java version by sestoft@itu.dk 2015-02-27

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestStoreBuffer {
  public static void main(String[] args) throws Exception {
    final ExecutorService executor = Executors.newWorkStealingPool();
    final int numberOfAttempts = 1000000;    
    final int[][] wins = { { 0, 0 }, { 0, 0 } };

    for (int count = 0; count < numberOfAttempts; count++) {
      StoreBufferExample sharedState = new StoreBufferExample();
      Future f1 = executor.submit(() -> { sharedState.ThreadA(); });
      Future f2 = executor.submit(() -> { sharedState.ThreadB(); });
      f1.get(); 
      f2.get();
      wins[sharedState.A_Won][sharedState.B_Won]++;
    }

    System.out.printf("Whew, that took some effort, (%d tries, sum is %d) let us recap:%n", 
		      numberOfAttempts, wins[0][0]+wins[1][0]+wins[0][1]+wins[1][1]);
    System.out.printf("No one   won: %8d%n", wins[0][0]);
    System.out.printf("A        won: %8d%n", wins[1][0]);
    System.out.printf("B        won: %8d%n", wins[0][1]);
    System.out.printf("Both (!) won: %8d%n", wins[1][1]);
  }
}

class StoreBufferExample {
  // On x86 .NET Framework 4.5, it makes no difference whether these
  // fields are volatile or not.  Moreover Volatile.{Read,Write} does
  // not fix the write order, but two uses of Thread.MemoryBarrier()
  // do.

  // In Java, making A and B volatile has the desired effect: no cases
  // where both A_Won and B_Won are 1!

  public /* volatile */ boolean A = false, B = false;
  public int A_Won = 0, B_Won = 0;
  
  public void ThreadA() {
    A = true;
    if (!B) 
      A_Won = 1;
  }
  
  public void ThreadB() {
    B = true;
    if (!A) 
      B_Won = 1;
  }
}

// Results from Java 1.8.0_31 on AMD Opteron 6386 SE, without volatile:

// sestoft@mtlab:~$ time java TestStoreBuffer
// Whew, that took some effort, (1000000 tries, sum is 1000000) let us recap:
// No one   won:        0
// A        won:   128836
// B        won:   852828
// Both (!) won:    18336

// real	0m36.953s
// user	0m36.762s
// sys	0m31.462s

// Results from Java 1.8.0_31 on AMD Opteron 6386 SE, with volatile:

// sestoft@mtlab:~$ time java TestStoreBuffer
// Whew, that took some effort, (1000000 tries, sum is 1000000) let us recap:
// No one   won:    44088
// A        won:   572656
// B        won:   383256
// Both (!) won:        0

// real	0m35.796s
// user	0m39.182s
// sys	0m32.474s


// Results from Java 1.8.0_11 on Intel i7, with volatile:

// sestoft@mac ~/java/pcpp $ time java TestStoreBuffer
// Whew, that took some effort, (1000000 tries, sum is 1000000) let us recap:
// No one   won:     2668
// A        won:   438518
// B        won:   558814
// Both (!) won:        0

// real	0m14.521s
// user	0m10.276s
// sys	0m19.890s


// Results from Java 1.8.0_11 on Intel i7, without volatile:
// sestoft@mac ~/java/pcpp $ time java TestStoreBuffer
// Whew, that took some effort, (1000000 tries, sum is 1000000) let us recap:
// No one   won:        0
// A        won:   436649
// B        won:   550463
// Both (!) won:    12888

// real	0m14.438s
// user	0m10.102s
// sys	0m19.695s


// Results from Java 1.7.0_40 on (Raspberry Pi) ARMv6-compatible processor rev 7 (v6l)
// without volatile:
// Whew, that took some effort, (1000000 tries, sum is 1000000) let us recap:
// No one   won:        0
// A        won:   999998
// B        won:        2
// Both (!) won:        0
