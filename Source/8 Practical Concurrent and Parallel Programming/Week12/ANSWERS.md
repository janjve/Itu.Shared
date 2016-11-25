Exercise 11
===========

a
-----------

See ABC.java.

b
-----------

We are now back to the case where we have concurrency issues with updates to balance, because of a lack of atomicity. An actor B might inspect a balance from actor A twice before A has set the new balance. This is because B will continue to read new messages from its port even though the balance of A haven't yet been updated from the first transaction. Only the latest of the two transaction will go through.