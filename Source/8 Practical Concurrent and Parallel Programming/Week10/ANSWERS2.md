Exercise 10.1
=============

10.1.1
-------------

increment - It's fairly obvious why increment works: we only increment the value, if it wasn't changed by another thread, while the current thread was operating on it.

getBins - This is fairly obvious as well. The javadoc specifies that using AtomicInteger.get() gives visibility.

getAndClear - Here we continue getting the latest value, untill we succeed with a compareAndSet, after which we return the latest value.

transferBins - The reason that this works, is because we don't need to care about what happens to the histogram we're transferring from. That value might be incremented while we're performing the rest of the operations, which is fine. The value in the current histogram is updated correctly because we're using compareAndSet.

10.1.2
-------------
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


10.1.3
-------------

CasHistogram - 
Running time: 1.174499289
Running time: 1.180684887
Running time: 1.204780251

StmHistogram - 
Running time: 1.210567368
Running time: 1.257879922
Running time: 1.159531943

It seems that, for the average case, the CasHistogram is slightly faster, even if only by less than a tenth of a second. 
This might be because the operations from CasHistogram directly uses a hardware implementation for its compareAndSet, whereas StmHistogram might use a less optimized version to perform its atomic operations. 
