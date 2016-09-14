Author: Jan Vium Enghoff <jaen@itu.dk>

##Exercise 1.1

###1.
Executing TestLongCounterExpreriments.java without the synchonized keyword
will print the result as output.

```
Count is 10018359 and should be 20000000 
```

##2.
Executing the application will still sometimes yields the wrong answer. The change 
of there being overlap are however much lower when running a low amount of iterations.

```
Count is 180 and should be 200 
```

##3.
No `count = count + 1` is equivalent to `count++` and `count +=1`. The number of operations are the same (3).

##4.
The result is unknown and not 0 since both methods operates on the same field `count`. To get the expected result, the methods would have to use the same lock (i.e. by the synchronized keyword)

##5.
Experiments:
no synchronized:
```
Count is 19046 and should be 200000000
```

synchronized decrement():
```
Count is -1895 and should be 200000000
```

synchronized increment():
```
Count is -18966 and should be 200000000
```

synchronized decrement() and increment():
```
Count is 0 and should be 200000000
```

#Exercise 1.2
##1.
If the two threads finish their sleep step at the same time they both want the System.out.print lock. 
After the first thread have finished printing "|" it release the lock and the second picks it up intenting to also print "|".
The first thread will now have to wait for the second to finish before it can acquire it and print "-", but this will break the "|-|-" pattern and instead yield a "||--" pattern..

##2. 
By making print synchronized we lock the instance of Printer instead of java's System.out.print. This ensures that the thread running print will finish the methods before releasing the lock.

##3.
```
class Printer {
    public void print(){
        synchronized (this){
            System.out.print("-");
            try{
                Thread.sleep(50);
            } catch (InterruptedException exn) { }
            System.out.print("|");
        }
    }
}
``` 

##4.
```
class Printer {
    public static void print(){
        synchronized (Printer.class){
            System.out.print("-");
            try{
                Thread.sleep(50);
            } catch (InterruptedException exn) { }
            System.out.print("|");
        }
    }
}
```

#Exercise 1.3
##1.
Yes. The thread t does not know about the change done by the main thread.

##2.
Yes. The thread t is know synchronized with the update from the main thread.

##3.
No, it does not terminate. This is because the thread t is calling get, which is not synchronized across threads.
It is not needed to have synchronized on both methods in this example, but only on `get`.

##4.
Yes. Value is now saved in main memory and is therefore shared between threads. 
In the methods get and set we don't care about a "local" value when running them.

#Exercise 1.4
##1.
Sequential: 8 sec.

##2.
10parallel: 1.5 sec.

##3.
No. The application printed:
```
Parallel2 result:       663498
```

##4.
In this case it matters neither practically or theoretically. 
get() is only called from the main thread after all the other threads are finished.

#Exercise 1.5
##1.
Output:
```
Sum is 1524593.000000 and should be 2000000.000000

Sum is 1478140.000000 and should be 2000000.000000

Sum is 1502702.000000 and should be 2000000.000000
```
Mystery is obviously not thread-safe.

##2.
The method addInstance use the lock of the instance of Mystery created in main, 
while the static methods use the one created by the runtime Mystery.class.

##3.
 ```
 class Mystery {
  private static double sum = 0;
  private final static Object myLock = new Object(); 

  public static void addStatic(double x) {
      synchronized(myLock){
        sum += x;      
      }
  }

  public void addInstance(double x) {
    synchronized(myLock){
        sum += x;      
    }
  }

  public static double sum() {
    synchronized(myLock){ // This is not needed in this particular case.
        return sum;      
    }
  }
}
```

#Exercise 1.6
##1.
Make every method as synchronized.

##2.
DoubleArrayList doesn't scale well with this solution. Using a lot of threads will not perform much better than running it in sequencial.

##3.
Since the methods operate on the same fields, the solution of having one lock per method will not work. 
Calling different methods from different threads might bring the class in an unintented state and is therefore not thread-safe.
It won't give visibility either, since the get and tostring methods aren't using the same locks as the update methods set and add.

#Exercise 1.7
##1.
By introducing a final static field to lock on or to lock to DoubleArrayList.class.

##2.
Make then constructor private and add a static method which shares a lock with the rest of the methods and returns a new instance of DoubleArrayList.

```
private DoubleArrayList() {
    allLists.add(this);
}

public synchronized static DoubleArrayList getInstance(){
    return new DoubleArrayList();
}
``` 

#Exercise 1.8
##1.
Even though they seems to be the same lock, they actually are not. Methods inherited from MysteryA use the lock MysteryA.class, while the method from MysteryB use MysteryA.class.

##2.
This is solved by changing the methods to use the same lock. An easy solution is as follows.

```
class MysteryB extends MysteryA {
  public static void increment4() {
      synchronized(MysteryA.class){
        count += 4;      
      }
  }
}
```