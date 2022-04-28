# RaccoonGame
This is a project created by me and 2 other team members. I was responsible, along with one other teammate to create the base source code, while the remaining team memeber worked mostly on tests. However, there was lots of help between each of these repsonibilities. I also had a friend help with music development. We used the agile development process model, and held sprint meetings each week. There were 4 phases to this process model, as well as a related assignment:

Phase 1: Plan the game using drawings and UML diagrams (1 week)

Phase 2: Create the inital version of the game in Java (3 weeks)

Phase 3: Create test cases and refactor our code (2 weeks)

Phase 4: Make a tutorial and finish up the game (3 weeks)

Assignment 3: More refactors, this time with a focus on making the code more Object Oriented. (1 week)

This is a game created by scratch called RaccoonGame, where a raccoon must collect garbage aroound Simon Fraser University's campus. There is a timer, which will kill the player when it runs out. This timer also acts as the score counter for the player. The garbage adds time/points, the lady raccoon is a bonus and adds reward at the end of the game, the humans will instantly kill the player, ending the game, and the trap will take away time/score. Once all of the garbage is collected, a door will open to the right of the map, which will allow the player to win the game.

Everything in this game is original and made from scratch, with help from JUnit. I am most proud of my enemy movement systems which implement a breadth first search algorithm to track the player around blocks. This algorithm was worked on intensely to ensure the performace was optimal enough to run on modern hardware.

This was cloned from a school gitlab repository, and as such the repository is a bit of a mess. However, I decided to not clean this up as I feel the history of the repository shows my ability to work with my team using git effectively. As such the game is contained in master/phase2-3/RaccoonGame, to compile use maven compile, and find the jar file in the targets folder. I have also created a jar file and put it right on this page(RaccoonGame.jar). To run the game, simply download this jar, as well as the latest versions of both Java and JDK(Java Development Kit). Then run the jar file after a system restart. You control the menu with the W and A keys, then enter to select. The game is then controlled with simply WASD, as well as escape to pause :)
