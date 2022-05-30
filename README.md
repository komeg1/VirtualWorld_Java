# VirtualWorld_Java
College assignment - Create simulation of virtual world that contains various animals and plants with different behavior using Java.

The assignment had the same rules as my other project [VirtualWorld_CPP](https://github.com/komeg1/VirtualWorld_CPP). I had to add a GUI using Swing library and possibility to add an animal by clicking on a field.

The game works pretty fine with 50x50 boards. It becomes laggy with the bigger ones.


## Description

The simulator is a turn-based game. It contains various animals and plants.  Each organism has defined strength and initiative.
Initiative tells which organism moves in what order. 

Animals can move in each round. Plants can spread in each round.
If there's collision animal with bigger strength win (unless it has a default action method [TABLE BELOW] ). If they have the same strength, the one with longer lifetime wins.

The Simulator contains such organisms:

![table](https://user-images.githubusercontent.com/61662701/171057510-67440917-8e87-489e-9c4b-a2aa59f81381.png)

## General information

You can play the simulation by downloading the ```javavirtualworld.jar``` file or by downloading all the files and compiling them.



The game starts by choosing an option from main menu.

1. New game
2. Load game
3. Exit

### New game

User is able to choose
 - Board size ( **1x1** - **20x20** ) 
 - Percetange fulfill of random organisms on the board (**25%** - **50%**)
 - Human starting position

![j2](https://user-images.githubusercontent.com/61662701/171059828-76338b05-7a39-4511-9a5d-d98150ad931c.png)

### Load game

User can choose a txt file with a saved game. 

![image](https://user-images.githubusercontent.com/61662701/171061430-1d16a5cc-32bb-4e2d-963d-1984c76dcb33.png)


## Game

You can proceed to next turn simply by clicking **Next turn** button or ussing `ARROWS` to control human.

You can save the game by clicking **Save** button. Then you need to input save name. The file will be saved in game's directory in Saves folder.

You can come back to main menu using **Main menu** button.

You can add a new organism by clicking **RMB** on an empty field.

### Human
Human can be controlled by `ARROWS`.

Human has special ability that can be turned on by `F`. It lets them move 2 fields instaed of 1 for 5 turns (100% chance in first 3 turns, 50% chance in 2 last turns). Then the skill gets 5 turns cooldown.


### GAME GUI EXAMPLE:

![j3](https://user-images.githubusercontent.com/61662701/171061170-b32081e0-9205-410a-a479-c1e7bb20b101.png)





There's a logs window on the left side of the board that shows the last 20 events that happeend in the game. Under the logs window you can see player stats and a legend of organisms.

For boards smaller than 21x21 you can also see the first letter of an organism on the board.






