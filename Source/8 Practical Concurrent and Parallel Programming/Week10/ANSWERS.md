Exercise 10.1
=============

10.1.1
-------------

- `increment`: This is the default CAS implementation. We only increment the value if it was not changed by another thread during the operation.

- `getBins`: The documentation for AtomicInteger ensures us that we have visibility. We never update in this method.

- `getAndClear`: The reason this works is the same as for `increment`.

- `transferBins`: This works because we don't care if we have the newest value from `hist` as long as it is in a consistent state. We use `getAndClear` we wrote earlier for this. We do however have to be sure that there were no changes to the bin in `this`. By using compareAndSet we achieve this.

10.1.2
-------------

We run the test from last week with transfering bins. This produce the following output:

```
mming\Week10\src> java TestCasHistogram
**********   0:         0
   1:         0
   2:         0
   3:         0
   4:         0
   5:         0
   6:         0
   7:         0
   8:         0
   9:         0
  10:         0
  11:         0
  12:         0
  13:         0
  14:         0
  15:         0
  16:         0
  17:         0
  18:         0
  19:         0
  20:         0
  21:         0
  22:         0
  23:         0
  24:         0
  25:         0
  26:         0
  27:         0
  28:         0
  29:         0
              0
   0:         2
   1:    283146
   2:    790986
   3:    988651
   4:    810386
   5:    524171
   6:    296702
   7:    155475
   8:     78002
   9:     38069
  10:     18232
  11:      8656
  12:      4055
  13:      1886
  14:       865
  15:       400
  16:       179
  17:        79
  18:        35
  19:        14
  20:         7
  21:         2
  22:         0
  23:         0
  24:         0
  25:         0
  26:         0
  27:         0
  28:         0
  29:         0
        4000000
```

This is as expected. All the values has been transfered and it gives the correct result.

10.1.3
-------------

To do this measurement we will remove the transfer part of the test.

STM:
```
Runningtime:      1.21116177 s
```

CAS:
```
Runningtime:      1.20353944 s
```

We ran this multiple times and they seem to be similar in runtime.


10.1.4
------------

Lock based:
```
Runningtime:      1.97263442 s
```

There is a signicant performance gain by using the CAS implementation.

Exercise 10.2
=============

See file TestSimpleRWTryLock.java.

Exercise 10.3
=============

