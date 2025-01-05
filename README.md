# Kaden Foster
👋 Hi, I’m Kaden.
😄 Pronouns: He/Him.
👀 I’m interested in AI, especially with regards to video game AI and the logic behind it.
📫 I can be reached at kadenfoster21@gmail.com

My repository contains 2 projects:
- An old minesweeper solver I made a few years ago in Java
- An improved version of my minesweeper solver which I'm currently working on

  ## `OldMinesweeperSolver.java`
  ```
  git clone repository url
  ```
  How the Program Runs:
  - First, the classes are initialized
  - Then, variables for the various colours and the board state are initialized
  - Then, the game is started by the AI clicking a central tile
  - Then, a loop begins that will continue until all bombs have been located
  - The loop starts by calculating how many bombs have been located
  - Then, the board state is read and stored in an array
  - Then, the AI checks how many bombs are adjacent to each tile
  - If 
  ## `NewMinesweeperSolver.java`
  ```
  
  ```
  How the Program Runs:
  - First, the classes are initialized
  - Then, the variables for the different colours associated with possible board states are initialized
  - Then, variables which change based on difficulty are initialized
  - Then, the top left and bottom right pixels of the game board are located in order to determine the size of each tile (without this step, the project will not work on most screens)
  - Then, the game is started by the AI clicking a central tile
  - Then, the initial board state is recorded
  - Then, A loop begins which will check if any tiles have a number of possible adjacent bomb tiles equal to that tile's number/remaining unfound bombs
  - Then, if any tile has located all of it's bombs, any adjcacent tiles not recorded as bombs will be clicked and the tile will not be checked again.
  - For now, this is all the project does. As more progress is made, this will be updated with more steps.
## Motivation Behind These Projects
My motivation for the old minesweeper solver was simple. I enjoy playing minesweeper and feel like I have a solid understand of the game and the strategy neccesary to beat it. This encouraged me to want to create an AI which could beat the game faster than I could.
The motivation behind my new minesweeper solver was because the old one stopped working. When I originally designed the AI, I created it with only the screen I was using in mind, but now I've lost access to that screen which is preventing the AI from working. The old AI was also only capable of beating easy mode, which I would like to change. 
Furthermore, the old AI had a glitch which I was unable to find the cause for.By developing a new AI, I could create an AI designed to work more universally across different screens, work to resolve any glitches, and make it work for any difficulty 
