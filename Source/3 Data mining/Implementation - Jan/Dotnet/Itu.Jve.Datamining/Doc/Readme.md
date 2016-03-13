Questions
---------

(2.	Er der en sammenhæng i mellem hvor mange timer en person spiller og hvilke typer af emner han prefererer i datamining?)
3.	Er der en sammenhæng i mellem hvor langt tilbage en person sad i lokalet til første forelæsning kontra hvor intereseret han er i kurset.
(4.	Er der en sammenhæng i mellem alder og om der er givet seriøse svar til spørgsmål 14 (Write four random numbers between 0 and 15))

Spilanalyse:
	1. Er der en sammenhæng i mellem alder og hvor mange timer en person bruger på spil?
	2. Er der en sammenhæng i mellem om en studerende læser GAMES-T og hvor mange timer de spiller computer sammenlignet med de andre uddannelser?
	3. Er der en sammenhæng i mellem om en studerende læser GAMES-T og hvor mange forskellige spil de spiller?
	4. Clusters i de spilrelaterede attributter (+Age)?


Data mining plan:
----------------
- Problem specification
	- See above
- Data collection
	- Descripe data (pdf bla bla)

- Data and tool preparation
	- Preprocessing.
		1. Select attributes (domain knowledge)
			- 1.  "Age"
			- 5.  "What degree are you studying?"
			- 10. "How often do you play video games?"
			- 11. "Which of these games have you played?"
			- 19. "Favorite game?"
		2. Clean all attributes.
			- Age: Numeric (/Nominal?)
			- Degree: Nominal
			- GameFrequency: Ordinal
			- PlayedGames: Set<Nominal>
				- Not binary since we assume that each tuple only contains a small subset of possible PlayedGames.
			- FavoriteGame: Nominal
				- Since question is started as a single favorite game, we choose to remove all but the first choise from each tuple.
		3. Parse to objects
			- Age: Int
			- Degree: Nominal
				- Since the "other" choise is only picked once we choose to add it to the set of valid nominal values for degree.
				- GamesA, GamesT, StdSe, Swu, GuestStudent, KuPolitEchonomy
			- ...
			- FavoriteGame
				- Choose allowed games and map the text before the first , to one of them.
		4. Handle noise
			- Test for noise.
			- If noise: HANDLE!
				- Ignore tuple
				- Fill in missing
				- Global constant like "unknown"
				- Use central tendency
			- Outliers
		5. Normalization
- Pattern discovery
	- Frequent itemset
		- Apriori
	- Classification
		- ID3
	- Clustering
		- k-mean



- Pattern evaluation
	- Visualize data
		- **Results.**
		- Graph
		- Tables
- Solution
	- Rapporten.