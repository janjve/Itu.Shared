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

See code. Works as expected.

8.1.4
------------

See code. Works as expected.

8.1.5
------------

See code. Works as expected.

8.1.6
------------
Interruption in case of deadlocks.
Repetition of runs??.
Perform the test on a system with more than 8 logical cores.
Automatic increase in number of threads, to test for potential thread pool or concurrency issues, such as deadlocks with WorkStealingPool. Problably something akin to Mark7, just without increasing the number of runs for each time. 

TODO:

Exercise 8.2
============
8.2.1
------------

We update our code to:
```
public V put(K k, V v) {
	...
	//synchronized (locks[stripe]) {
		...
	//}
```
Commenting out the lock from put gives the following expected output:
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_111
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-10-28T14:30:28+0200
Exception in thread "main" java.lang.AssertionError
        at TestStripedMap.testMapConcurrent(TestStripedMap.java:237)
        at TestStripedMap.testAllMaps(TestStripedMap.java:259)
        at TestStripedMap.main(TestStripedMap.java:25)
```

8.2.2
------------

Updating the code to both `synchronized (this)` and `synchronized (locks[0])` gives the same assertion error as above. Which is to be expected.

8.2.3
------------

This is also correctly detected by the functional test.

8.2.4
------------

For us this does not seem to be the case. The test catch the error even not using reads.

8.2.5
------------

We tried retreiving the node from the wrong bucket by doing

```
hash = h % bs.length+1;
```

in the get() method. The test found this.

What our tests doesn't find is that it never checks if the map is actually reallocated. This means that if we somehow ended up writing:
```
if (false && afterSize * lockCount > bs.length){
      reallocateBuckets(bs);
}
```

in put and putIfAbsent, then the tests would never tell us it was wrong. This would require a benchmark test to check if the datastructure performs as expected.
