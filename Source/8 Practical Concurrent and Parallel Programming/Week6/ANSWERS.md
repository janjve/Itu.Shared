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

class SynchronizedMap
        17 maps to B
       117 maps to C
        34 maps to F
       217 maps to E
        17 maps to B
        17 maps to B
       217 maps to E
        34 maps to F
       217 maps to E
        17 maps to B
        34 maps to F

class StripedMap
        17 maps to B
       117 maps to C
       217 maps to E
        17 maps to B
        34 maps to F
        17 maps to B
       217 maps to E
        34 maps to F
       217 maps to E
        17 maps to B
        34 maps to F

class WrapConcurrentHashMap
        17 maps to B
       117 maps to C
        17 maps to B
        34 maps to F
       217 maps to E
        17 maps to B
        34 maps to F
       217 maps to E
        17 maps to B
        34 maps to F
       217 maps to E
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

