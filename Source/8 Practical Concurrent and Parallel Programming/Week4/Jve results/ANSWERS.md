Exercise 4.1
============

4.1.1
------------
System info:
- Processor: Intel(R) Core(TM) i7-4712HQ CPU @ 2.30GHz
- Installed memory (RAM): 16.0 GB
- System type: 64-bit Operating System, x64 based Processor

System info from benchmark.java:
- OS:   Windows 10; 10.0; amd64
- JVM:  Oracle Corporation; 1.8.0_74
- CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
- Date: 2016-09-23T10:31:31+0200

Mark0 output:
```
4462.0 ns
```

Mark1 output:
```
0.0 ns
```

Mark2 output:
```
33.0 ns
```

Mark3 output:
```
33.2 ns
33.3 ns
33.4 ns
32.6 ns
32.2 ns
32.2 ns
32.3 ns
31.7 ns
32.1 ns
31.9 ns
```

Mark4 output:
```
32.8 ns +/-  0.552
```

Mark5 output:
```
 468.6 ns +/-   776.13          2
 145.1 ns +/-   105.83          4
 189.7 ns +/-    95.53          8
 175.7 ns +/-   106.85         16
  55.8 ns +/-     6.57         32
  64.1 ns +/-    41.29         64
  86.5 ns +/-    97.33        128
  51.6 ns +/-     5.34        256
 206.2 ns +/-   491.64        512
  42.8 ns +/-     1.00       1024
  45.8 ns +/-     0.65       2048
  43.5 ns +/-     3.91       4096
  39.9 ns +/-     5.68       8192
  35.3 ns +/-     1.46      16384
  36.2 ns +/-     2.97      32768
  33.2 ns +/-     2.18      65536
  32.7 ns +/-     0.85     131072
  32.6 ns +/-     0.36     262144
  32.8 ns +/-     0.55     524288
  34.0 ns +/-     0.31    1048576
  33.6 ns +/-     0.33    2097152
  33.7 ns +/-     0.42    4194304
  33.6 ns +/-     0.64    8388608
```

The run with count 512 has a high runtime, aswell as a very high standard deviation.
This could be the result of another program interfering, but running the program multiple times always yields a spike in inaccuracy at that size.
Changing the starting count to e.g. 128 will remove this inconsistency, which makes it seem like the JIT compiler is doing some optimization in vanilla Mark5.

Mark6 output:
```
multiply                            758.7 ns    1773.26          2
multiply                            189.6 ns     139.63          4
multiply                            217.6 ns     124.61          8
multiply                           5198.8 ns   15744.80         16
multiply                             78.1 ns      70.56         32
multiply                             70.4 ns      24.48         64
multiply                             94.1 ns      90.78        128
multiply                             67.8 ns      15.60        256
multiply                             63.2 ns       5.12        512
multiply                             59.3 ns       0.66       1024
multiply                             58.5 ns       1.94       2048
multiply                             53.1 ns       6.96       4096
multiply                             39.1 ns       4.00       8192
multiply                             35.7 ns       1.07      16384
multiply                             35.9 ns       4.58      32768
multiply                             33.6 ns       2.43      65536
multiply                             32.9 ns       1.21     131072
multiply                             32.3 ns       0.49     262144
multiply                             33.0 ns       0.60     524288
multiply                             33.8 ns       0.53    1048576
multiply                             33.4 ns       0.31    2097152
multiply                             33.7 ns       0.49    4194304
multiply                             33.5 ns       0.61    8388608
```

The same phenomenom as with Mark5, but this time it occurs at count 16.

4.1.2
------------

See jve-4.2.2-results.txt
