Exercise 6.1
============

6.1.1
------------

We chose the first approach, that is to create a DownloadWorker for each url. The done() method of the DownloadWorker will update the textarea, but because done is always run on the Event Dispatch Thread, we can be sure that there are no concurrency issues when updating the Swing element.

We used an urlcount attribute to figure out if the done() method should print out "done" to the textarea to avoid having the string printed for each worker.

6.1.2
------------

At the moment each thread will print "Yrk" when clicking cancel. This can be handled in a simular manner as the done from 6.1.1.

6.1.3
------------

Because done is called on the Event Dispatch Thread, we can simply update the progressbar directly from there and can skip the event listeners. We already got the count from handling the "done" printout.

Exercise 6.1
============

6.2.1
------------

The canvas on which the lifts are drawn are updated through the methods of the Lift thread. These methods are all synchronized. The GUI components of the buttons are only updated through the event thread (and main thread during construction).

6.2.3
------------

See code.

6.2.4
------------

We removed the sleep from the run method, but there still exists a sleep method in the openAndCloseDoors() for the door animation.

6.2.5
------------

