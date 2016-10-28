Exercise 8.1
============

8.1.1
------------

Our implementation `StripedWriteMap` pass the tests in `testMap()` with output:
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_111
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-28T10:22:00+0200

class StripedWriteMap
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
        17 maps to B
```
As expected.

Because `NodeItem` is a local class in StripedWriteMap, we also have to test its methods.
- testMap() has method coverage, meaning it calls every method atleast once.
- testMap() does not have statement coverage. See testMapExtended() for missing coverage.

8.1.2
------------

The implementation runs as expected. It doesn't say anything about correct values of a particular key, but only about the size of the map.

8.1.3
------------

Works as expected.

8.1.4
------------

8.1.6
------------
Interruption in case of deadlocks.
Repetition of runs??.
Perform the test on a system with more than 8 logical cores.
Automatic increase in number of threads, to test for potential thread pool or concurrency issues, such as deadlocks with WorkStealingPool. Problably something akin to Mark7, just without increasing the number of runs for each time. 

