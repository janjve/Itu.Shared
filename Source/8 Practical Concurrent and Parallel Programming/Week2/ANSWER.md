Author: Jan Vium Enghoff

#Exercise 2.1
##1
Runs in around 6 seconds

##2
```
class MyAtomicInteger {
  private volatile int value;

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
```

##3
The result is as expected and the runtime is just below 1 second

##4
No. Updating the value of value of MyAtomicInteger will still have to be synchronized to ensure two threads doesn't change the state at the same time with addAndGet().

##5
The runtime and result are about the same. Perhaps the java.util implementation is slightly faster.

In this case the AtomicInteger object doesn't have to be final. This is because no thread is updating the AtomicInteger object reference, but only the value of it.

#Exercise 2.2
##1
Volatile is needed for the other threads to access the cache from thread B when thread A changes it.

##2
The fields needs to be updated atomically. By making them final the two fields are visible from different threads.

However in the case of the usage from VolatileCachingFactorizer, where the cache is defined as volatile, it won't matter that lastNumber is final, since this is a primitive value.

#Exercise 2.3
##1
counts needs to be made final and synchronized needs to be added to increment and getCount. We can leave out getSpan as this is never changed after the constructor.

##2
Answered in code.

##3
Yes. It is now possible to remove synchronized from the methods. The synchronization now happens on the AtomicInteger objects instead of in the methods manipulating the array.

##4
See code.

##5
Clone in histogram2 will create a new array with no references.
AtomicInteger and AtomicIntegerArray doesn't seem to have a way to get a reference to the underlying datastructure other than through the get method, so we have to create a new array.

#Exercise 2.4
##1
See code.

##2
Yes. The number of calls was as followed for the different factorizers
```
class Memoizer
calls: 115000
runtime: 8919 ms.

class Memoizer1
calls: 115000
runtime: 17431 ms.

class Memoizer2
calls: 232521
runtime: 9374 ms.

class Memoizer3
calls: 118177
runtime: 8182 ms.

class Memoizer4
calls: 115000
runtime: 8765 ms.

class Memoizer5
calls: 115000
runtime: 8829 ms.

class Memoizer0
calls: 115000
runtime: 8518 ms.
```

##7
```
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
    return cache.computeIfAbsent(arg, (A argv) -> computeWrapper(argv));
  }

  // Silly java checked exception wrapper.
  private V computeWrapper(A a) {
    V v = null;
    try {
        v = c.compute(a);
    } catch(InterruptedException e) { }
    return v;
  }
}
```

output:
```
class Memoizer0
calls: 115000
runtime: 8225 ms.
```