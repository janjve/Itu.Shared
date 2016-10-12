Exercise 6.1
============

6.1.1
------------

See code.

6.1.2
------------

The reason it's important to lock on each stripe is to ensure that the changes made to `sized` are visible to the thread running size().

6.1.5
------------

I choose implementation (2). This is to allow other threads to access stripes foreach() is not currently using and thereby reducing the time we are holding each stripe.

6.1.7
------------

For results see the file jve_6.1-6.2_results.txt.

This looks as expected. The performance increase in StripedMap is noticably better than for SynchronizedMap.

6.1.8
------------

The more locks there are, the more overhead there is when doing the reallocation and the foreach method. 
The probability of two items sharing a lock becomes smaller as we add more stripes and the performance gain is therefore less.

6.1.9
------------

Kinda the same argument as in 6.1.8. The probability that two buckets unneccessarily share a lock is smaller.

6.1.10
------------

This ensures that a bucket belonging to stripe s will continue to belong to stripe s after reallocation of the buckets.

Exercise 6.2
============

6.2.3
------------

We don't have to update the sizes because the number of elements in the hashmap hasn't changes.

6.2.6
------------

For results see the file jve_6.1-6.2_results.txt.

This is as expected. It is however quite surprising that our implementation is quicker than java's ConcurrentHashMap, even when using a high number of tasks.
We expect that this will not be the case when running on a system with more cores than my i7 laptop.

Exercise 6.3
============

6.3.1
------------
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-07T14:14:00+0200
AtomicLong                       953626.7 us    5287.99          2
LongAdder                         64659.0 us    1953.03          8
LongCounter                      597240.4 us  415037.62          2
NewLongAdder                     439888.5 us   35191.86          2
NewLongAdderPadded               109438.3 us    8365.24          4
```
Java 8's LongAdder seems to perform by far the best. This is as expected. What is somewhat surprising is that AtomicLong performs worse than our own LongCounter, which just locks on on every method. However, The standard deviation is huge, which could indicate that there is some anomaly in how it's run. The "useless" object padding seems to improve performance quite drastically.

6.3.2
-----------

See code.

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-07T14:21:34+0200
NewLongAdderPadded               115018.9 us   11161.13          4
NewLongAdderLessPadded           185132.3 us   25363.89          2
```

I left out the unrelated adders from the output.
Testing the implementation of newLongAdderPadded without the object padding actually shows that it has an large positive effect on the performance when running on my system.
