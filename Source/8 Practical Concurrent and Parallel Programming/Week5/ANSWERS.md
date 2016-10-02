Exercise 5.1
============

TODO: Run on full task count.
5.1.1
----------
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-09-30T10:23:15+0200
countSequential                   11894.3 us     161.81         32
9592.0
countParTask1     32               2741.7 us       8.25        128
9592.0
countParTask2     32               2748.8 us      35.09        128
9592.0
countParTask3     32               2741.2 us      15.89        128
9592.0
countParTask1      1              10216.7 us      42.86         32
countParTask1      2               6218.5 us      14.75         64
countParTask1      3               4528.9 us     140.91         64
countParTask1      4               3683.9 us      87.10        128
countParTask1      5               4001.6 us      32.48        128
countParTask1      6               3359.1 us      32.61        128
countParTask1      7               2931.0 us      17.42        128
countParTask1      8               2727.9 us      14.76        128
countParTask2      1              12311.3 us     171.22         32
countParTask2      2               7576.4 us      34.24         64
countParTask2      3               5306.4 us      34.75         64
countParTask2      4               4452.9 us     297.09         64
countParTask2      5               4644.2 us      89.31         64
countParTask2      6               3917.4 us      17.27         64
countParTask2      7               3403.5 us      56.77        128
countParTask2      8               3204.6 us      75.75        128
```
5.1.2
----------
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-09-30T10:28:58+0200
countSequential                   11970.2 us      70.35         32
9592.0
countParTask1     32               3072.7 us      10.00        128
9592.0
countParTask2     32               2695.3 us      19.51        128
9592.0
countParTask3     32               2955.0 us      15.58        128
9592.0
countParTask1      1              10222.5 us      42.96         32
countParTask1      2               6443.0 us      19.41         64
countParTask1      3               4619.8 us      13.51         64
countParTask1      4               3844.6 us     147.91         64
countParTask1      5               3711.7 us      54.22        128
countParTask1      6               3285.0 us      15.72        128
countParTask1      7               2854.7 us      40.49        128
countParTask1      8               2557.4 us      38.50        128
countParTask2      1              11878.7 us      47.62         32
countParTask2      2               7388.6 us      30.67         64
countParTask2      3               5400.4 us     179.40         64
countParTask2      4               4552.2 us     243.25         64
countParTask2      5               4589.7 us      56.93         64
countParTask2      6               4046.1 us      59.45         64
countParTask2      7               3423.8 us      13.83        128
countParTask2      8               2979.8 us      15.28        128
```
5.1.3
----------

TOOD: Excel doc (Jve_1.1-1.2)

5.1.4
----------

As expected. 

5.1.5
----------

__Cached:__
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-09-30T11:05:26+0200
countSequential                   11916.4 us     133.31         32
9592.0
countParTask1     32               2636.3 us       5.81        128
9592.0
countParTask2     32               2755.4 us       8.16        128
9592.0
countParTask3     32               2741.6 us       7.82        128
9592.0
countParTask1      1              12062.8 us      77.50         32
countParTask1      2               6620.2 us      20.04         64
countParTask1      3               4756.3 us      17.51         64
countParTask1      4               3906.2 us      95.77        128
countParTask1      5               4316.5 us      68.23         64
countParTask1      6               3794.7 us      35.90        128
countParTask1      7               3198.8 us      16.92        128
countParTask1      8               3044.3 us      23.76        128
```
__Work stealing:__
```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 1.8.0_74
# CPU:  Intel64 Family 6 Model 60 Stepping 3, GenuineIntel; 8 "cores"
# Date: 2016-09-30T11:02:30+0200
countSequential                   12336.0 us     323.95         32
9592.0
countParTask1     32               2743.0 us       7.02        128
9592.0
countParTask2     32               2573.3 us      14.89        128
9592.0
countParTask3     32               2941.9 us       6.84        128
9592.0
countParTask1      1               9433.9 us      36.83         32
countParTask1      2               5815.7 us      13.72         64
countParTask1      3               4207.0 us      36.20         64
countParTask1      4               3582.1 us     358.54         64
countParTask1      5               4306.9 us      47.73         64
countParTask1      6               3826.7 us      42.50        128
countParTask1      7               3237.2 us      11.77        128
countParTask1      8               2792.7 us      23.32        128
```
Exercise 5.3
============

5.3.1
----------

See Code

5.3.3
----------

Measured runtime in sec.:
```
Retrieved in: 6.212030259
Retrieved in: 7.220270193
Retrieved in: 8.300353963
Retrieved in: 10.605866976
Retrieved in: 10.333536822
```

5.3.4
----------

Measured runtime in sec.:
```
Retrieved in: 1.954890768
Retrieved in: 2.003220112
Retrieved in: 2.552612414
Retrieved in: 9.264647135
Retrieved in: 2.203419494
```

No matter how we parallelize the problem, it will never be faster than the time it takes to fetch the slowest responding web site. This is particularly noticable in the fourth run, where the runtime was as slow as some of the sequential.

Exercise 5.4
============

