# Ballz-Replica

This program is a replica of the popular mobile game Ballz programmed in Java. No external libraries were used, only the defualt Swing and AWT core libraries were implemented for the graphics. 

This game was made in a rather short amount of time with minimal documenation as a first attempt to make a game without any external game engine. This goal was achieved the main game engine and render engines were created using only the defulat classes provdied in Java. 

The game uses input from the keyboard to control the direction in which balls are launched to try to get rid of oncoming squares. If the square reach the bottom of the screen game over. A screenshot from the main part of the game is shown below.

The program was structured into three differnt packages, one that handled the display, one for the main game loops and updates, and one for all of the game objects. 

The game class intitalizes all of the parameters and starts the main loop while the Tick class holds the methods used to update the game each frame. The window class creates the main JFrame window for the game and the Render class updates the main canvas for the game.

There are four instantiated game objects, squares, balls, board-balls, and the player. The Player class holds the score, the number of current balls the player has along with other user data. The Ball class represents a single ball that can be launched by the player to try to hit the oncoming squares. The Square class represents one square that can be genereted on the main game board from the top of the screen and move down to the bottom. The BoardBall class represents a single new ball that is generated randomly on the board that the user can get access to if one of the other balls contacts it. 

The included jar file, ballz.jar can be used to run the game. 

![alt text](https://github.com/BenDaMan88/Ballz-Replica/blob/master/Game_Screen.PNG)
