

Exercise X.2
============

X.2.2
------------

```
...
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

X.2.6
------------

First reading is `histogram`, while the second is `total`.
```
   0:         0
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

X.2.7
------------

We expect there will occur a slight performance decrease, since the operations sometimes will have to roll back. The transactions done not by the main thread is however much shorter than that done by the main threads, so the likelyhood that they have to retry is also a lot less.


Exercise X.3
============

X.3.3
------------

Our implementation is inside the atomic function of multiverse, which means that updates to the buckets are done atomicly. This means we don't have to lock on the stripes to ensure concurrency.

X.3.5
------------

We'd have to insert a check on newBuckets if it's null, in every method performing transactions on the buckets. If the check fails, the method calls `retry`, thereby blocking untill newBuckets is null again, i.e. the reallocation has finished reallocating, at which point they can proceed.