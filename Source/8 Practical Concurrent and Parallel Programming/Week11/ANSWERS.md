Week 11
-------------
Authors: Jan Vium Enghoff, Søren Harisson

Exercise 11.1
=============

11.1.1
-------------
See code

11.1.2
-------------

Yes it passes both the sequential and concurrent test.

11.1.3
-------------

Trying to do a test-then-act approach instead of CAS shows up in our test. The implementation went from

```
tail.compareAndSet(last, node);
```
To
```
if(tail.get().equals(last)){
  tail.set(node); // WOOPS
}
```
in `enqueue(T item)`. This is because another thread might have updated the tail in between the check and the set method above.

Removing `tail.compareAndSet(last, node);` and thereby not advancing the tail in the inner-most loop of of `enqueue(T item)` does not actually have an effect on the correctness of the datastructure, but will reduce the performance on the it. If the next thread is doing an enqueue, it will have to loop atleast twice to in the while-loop to add therefore doing twice the checks.

Exercise 11.2
=============

11.2.1
-------------

The check has no effect on the correctness. This if-statement is much like our test from 11.1.3 that does test-then-act. It doesn't ensure anything as the check might be false before doing the act. If it's for performance reasons then compareAndSet should be much slower than getting the value of tail and comparing it inline.

11.2.2 & 11.2.3
-------------

Yes.

11.2.4
-------------

See 11.2.1. For the test we could simply run it a large number of times and see which implementation finishes the fastest.

11.2.5
-------------

By measuring the runtime of the parallel test using our mark7 benchmark it showed that there was a slight increase in performance using E7 and D5. However one should remember is that this is implemented in Java and that the compareAndSwap implementation might work differently in other languages and on other hardware.

Running mark7 on the parallel test gave the following output:
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_111
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-11-18T12:39:55+0100

UnboundedQueue1                    42963.6 us    1537.95          8
UnboundedQueue2                    47602.6 us    1445.91          8
UnboundedQueue3                    56158.4 us   10765.54          4
UnboundedQueue4                    52059.2 us    1006.11          8
```

Where:
- `UnboundedQueue1` is with E7 and D5
- `UnboundedQueue2` is without E7 and with D5
- `UnboundedQueue3` is with E7 and without D5
- `UnboundedQueue4` is without E7 and D5

It should be noted that `UnboundedQueue3` has a large standard deviation and therefore it might be the case that `UnboundedQueue4` would perform worse, which is what we hoped for.

Exercise 11.3
=============

11.3.1
-------------

```
class MSQueue         [P:1|C:1]         11473.2 us     170.88         32
class MSQueue         [P:2|C:2]         14542.6 us     181.37         32
class MSQueue         [P:3|C:3]         18630.2 us     148.34         16
class MSQueue         [P:4|C:4]         20166.8 us     155.75         16
```

11.3.2
-------------

```
class MSQueueRefl     [P:1|C:1]         12085.4 us      72.81         32
class MSQueueRefl     [P:2|C:2]         14473.2 us     361.57         32
class MSQueueRefl     [P:3|C:3]         19291.8 us     142.71         16
class MSQueueRefl     [P:4|C:4]         20463.3 us      80.77         16
```
This does not seem to have a large influent. This is most likely due to the amount of work on the queue is much less than the overall work of each thread.

11.3.3
-------------
```
class LockingQueue    [P:1|C:1]         40018.1 us    7802.97          8
class LockingQueue    [P:2|C:2]         25334.9 us    1477.99         16
class LockingQueue    [P:3|C:3]         61760.2 us    2122.70          8
class LockingQueue    [P:4|C:4]         77595.0 us    3189.55          4
```

11.3.4
-------------

With E7 and D5
```
class MSQueue         [P:1|C:1]         12262.8 us     118.78         32
class MSQueue         [P:2|C:2]         14425.2 us     689.28         32
class MSQueue         [P:3|C:3]         22116.2 us     393.86         16
class MSQueue         [P:4|C:4]         23842.1 us     101.74         16
```

Without E7 and with D5
```
class MSQueue         [P:1|C:1]         11483.7 us     154.68         32
class MSQueue         [P:2|C:2]         14684.0 us     297.67         32
class MSQueue         [P:3|C:3]         18906.8 us     130.68         16
class MSQueue         [P:4|C:4]         19912.7 us     103.63         16
```

With E7 and without D5
```
class MSQueue         [P:1|C:1]         14046.1 us     110.84         32
class MSQueue         [P:2|C:2]         14160.8 us     396.93         32
class MSQueue         [P:3|C:3]         21747.1 us     398.89         16
class MSQueue         [P:4|C:4]         23130.4 us     217.47         16
```

Without E7 and D5
```
class MSQueue         [P:1|C:1]         11345.1 us     198.33         32
class MSQueue         [P:2|C:2]         14608.0 us     301.26         32
class MSQueue         [P:3|C:3]         18977.6 us     238.96         16
class MSQueue         [P:4|C:4]         20203.5 us     135.19         16
```

Exercise 11.4
=============

11.4.1
-------------
See code.

11.4.2
-------------
We have a single push/pop thread, and a variable amount of stealing threads. For each we generate a baseline 200.000 values for the push/pop thread, and 100.000 values for each stealing thread. The size of the queue needs to match the total amount of values pushed. 

We have the following asserts, to make certain that everything went well:
```
assertEquals(popStealSum.get(), pushSum.get());
assertTrue(popStealSum.get() > 0);
assertTrue(queue.pop() == null);
assertTrue(queue.steal() == null);
```
And it passes.


11.4.3
------------

11.4.4
------------
It seems as though this scenario should never be reached unless our push, pop and steal operations all run concurrently, since this should only happen if the queue becomes empty, and they both manage to take the last element. 