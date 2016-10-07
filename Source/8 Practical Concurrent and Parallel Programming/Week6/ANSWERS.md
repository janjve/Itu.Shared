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
TODO: Run with timeAllMaps().

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-07T11:02:37+0200

SynchronizedMap       16         570328.4 us   41794.99          2
99992.0
StripedMap            16         147284.2 us   32563.01          2
99992.0
StripedWriteMap       16          49013.2 us     722.15          8
0.0
WrapConcHashMap       16          75387.9 us    4704.55          4
```

This looks as expected. The performance increase in StripedMap is noticably better than for SynchronizedMap.

This StripedWriteMap is commented out, so results from that can't be used.

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

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-07T13:57:32+0200

SynchronizedMap       16         557295.1 us   27905.07          2
99992.0
StripedMap            16         145919.8 us   40761.11          2
99992.0
StripedWriteMap       16          52411.2 us    1690.19          8
99992.0
WrapConcHashMap       16          73135.9 us    6561.34          4
99992.0
```

This is as expected. It is however quite weird that our implementation is so much quicker than java's ConcurrentHashMap.

Exercise 6.2
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

TODO: Discuss

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
TODO: Discuss.