Author 1: Jan Vium Enghoff <jaen@itu.dk>
Author 2: Jan Vium Enghoff <soeh@itu.dk>

Week 3
======
Exercise 1
----------

Exercise 2
----------

Exercise 3
----------
3.12
- Slapping .parallel() on the stream will make the query run much slower than the query from 3.11. 

Exercise 4
----------

4.1, 4.2 and 4.3
Runtime in ms:
- Runtime sequential = 10089
- Runtime parallel = 2725
- Runtime imperative = 7766

Calculated sum:
- Sum sequential =  21.3004815003479420
- Sum parallel =  21.3004815003479420
- Sum imperative =  21.3004815013485500

4.4 and 4.5
Runtime in ms:
- Runtime generator = 10748
- Runtime generator parallel = 3339

Calculated sum:
- Sum generator =  21.3004815013479420
- (1) Sum generator parallel =  55.3016649505279860
- (2) Sum generator parallel =  54.2421667745811300
- (3) Sum generator parallel =  50.6176906488736300

The mutable generator ruined the parallelism of the stream structure.