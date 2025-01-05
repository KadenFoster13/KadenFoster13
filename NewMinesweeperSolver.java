/*
This is an updated version of my old minesweeper solver.
When completed, it will run faster, work on all different difficulties, and work on more different screen resolutions.
For now, the project does nothing and this file is missing some code.
As progress is made, I will update this file.
*/

package code;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.MouseInfo;
import java.util.concurrent.TimeUnit;
import java.io.File;



public class Solver 
{
	public static void main(String[] args) throws Exception
	{
		Robot AI = new Robot();
		//File info = new File("info.txt"); //Add info saving later

		
		int screenHeight = 1440, screenLength = 2560; // set screen dimensions here
		String difficulty = "easy"; // set difficulty here
		int difficultyLength = 0, difficultyHeight = 0, totalBombs;
		boolean solved = false;
		Color UnknownDark = new Color(162, 209, 73), UnknownLight = new Color(170, 215, 81); //sets colors for tile types and numbers
		Color knownDark = new Color(215, 184, 153), knownLight = new Color(229, 194, 159);
		Color num1 = new Color(25, 118, 210), num2 = new Color(56, 142, 60), num3 = new Color(212, 53, 52); 
		/*while(!solved)
		{
			System.out.println(MouseInfo.getPointerInfo().getLocation());
			System.out.println(AI.getPixelColor(1214, 720));
		}*/
		
		if(difficulty.equals("easy")) // sets tile numbers based on difficulty setting
		{
			 difficultyLength = 10;
			 difficultyHeight = 8;
			 totalBombs = 10;
		}
		else if(difficulty.equals("medium"))
		{
			 difficultyLength = 18;
			 difficultyHeight = 14;
			 totalBombs = 10;
		}
		else if(difficulty.equals("hard"))
		{
			 difficultyLength = 24;
			 difficultyHeight = 20;
			 totalBombs = 10;
		}
		LinkedGrid board = new LinkedGrid(difficultyLength, difficultyHeight);
		
		int topLeftX = screenLength/2, topLeftY = screenHeight/2, bottomRightX = screenLength/2, bottomRightY = screenHeight/2;// finds top left and bottom right pixels of the board
		while(AI.getPixelColor(topLeftX-1, topLeftY).equals(UnknownLight) || AI.getPixelColor(topLeftX-1, topLeftY).equals(UnknownDark))
			topLeftX--;
		while(AI.getPixelColor(topLeftX, topLeftY-1).equals(UnknownLight) || AI.getPixelColor(topLeftX, topLeftY-1).equals(UnknownDark))
			topLeftY--;
		while(AI.getPixelColor(bottomRightX+1, bottomRightY).equals(UnknownLight) || AI.getPixelColor(bottomRightX+1, bottomRightY).equals(UnknownDark))
			bottomRightX++;
		while(AI.getPixelColor(bottomRightX, bottomRightY+1).equals(UnknownLight) || AI.getPixelColor(bottomRightX, bottomRightY+1).equals(UnknownDark))
			bottomRightY++;
		int boardLength = bottomRightX - topLeftX,  boardHeight = bottomRightY - topLeftY; // sets board dimensions
		int tileLength = boardLength/difficultyLength, tileHeight = boardHeight/difficultyHeight; // sets tile dimensions
		
		AI.mouseMove(topLeftX+tileLength*difficultyLength/2-tileLength/2, topLeftY+tileHeight*difficultyHeight/2-tileHeight/2); // Starts the game by pressing a center tile
		AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		Node currentTile = board.getTopLeft(), row = currentTile;
		for(int x = 0; x < difficultyHeight; x++) // sets colors for tiles !!!!!!!!!! 
		{
			for(int y = 0; y < difficultyLength; y++)
			{
				currentTile.setNodeColor(AI.getPixelColor(topLeftX+tileLength*x-tileLength/2, topLeftY+tileHeight*y-tileHeight/2));
				if(currentTile.getNodeColor().equals(num1))
					currentTile.setBombsAdjacent(1);
				else if(currentTile.getNodeColor().equals(num2))
					currentTile.setBombsAdjacent(2);
				else if(currentTile.getNodeColor().equals(num3))
					currentTile.setBombsAdjacent(3);
				currentTile = currentTile.getDown();
			}
			row = row.getRight();
			currentTile = row;
		}
		while(!solved)
		{
			currentTile = board.getTopLeft();
			row = currentTile;
			int unknownAdjacent = 0;
			for(int x = 0; x < difficultyHeight; x++) 
				for(int y = 0; y < difficultyLength; y++)
				{ // null pointer error for some reason, fix it !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(currentTile.getBombsFound() == currentTile.getBombsAdjacent()) // clicks tiles that aren't bombs if all of a tile's adjacent bombs are found
					{
						if(currentTile.getUp()!=null) // ensures the tile is not a top tile
						{
							if(currentTile.getLeft()!=null) // ensures the tile isn't a side tile
							{
								if(currentTile.getUp().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getUp().getLeft().getNodeColor().equals(UnknownLight))
									if(!currentTile.getUp().getLeft().isBomb())
									{
										AI.mouseMove(topLeftX+tileLength*(x-1)-tileLength/2, topLeftY+tileHeight*(y-1)-tileHeight/2);
										AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
										AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									}
							}
							if(currentTile.getUp().getNodeColor().equals(UnknownDark) || currentTile.getUp().getNodeColor().equals(UnknownLight))
								if(!currentTile.getUp().isBomb())
								{
									AI.mouseMove(topLeftX+tileLength*x-tileLength/2, topLeftY+tileHeight*(y-1)-tileHeight/2);
									AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
							if(currentTile.getRight()!=null) // ensures the tile isn't a side tile
							{
								if(currentTile.getUp().getRight().getNodeColor().equals(UnknownDark) || currentTile.getUp().getRight().getNodeColor().equals(UnknownLight))
									if(!currentTile.getUp().getRight().isBomb())
									{
										AI.mouseMove(topLeftX+tileLength*(x+1)-tileLength/2, topLeftY+tileHeight*(y-1)-tileHeight/2); 
										AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
										AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									}
							}
						}
						if(currentTile.getRight()!=null) // ensures the tile isn't a side tile
						{
							if(currentTile.getRight().getNodeColor().equals(UnknownDark) || currentTile.getRight().getNodeColor().equals(UnknownLight))
								if(!currentTile.getRight().isBomb())
								{
									AI.mouseMove(topLeftX+tileLength*(x+1)-tileLength/2, topLeftY+tileHeight*y-tileHeight/2);
									AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
						}
						if(currentTile.getDown()!=null) // ensures the tile isn't a side tile
						{
							if(currentTile.getRight()!=null) // ensures the tile isn't a side tile
							{
								if(currentTile.getDown().getRight().getNodeColor().equals(UnknownDark) || currentTile.getDown().getRight().getNodeColor().equals(UnknownLight))
									if(!currentTile.getDown().getRight().isBomb())
									{
										AI.mouseMove(topLeftX+tileLength*(x+1)-tileLength/2, topLeftY+tileHeight*(y+1)-tileHeight/2); 
										AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
										AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									}
							}
							if(currentTile.getDown().getNodeColor().equals(UnknownDark) || currentTile.getDown().getNodeColor().equals(UnknownLight))
								if(!currentTile.getDown().isBomb())
								{
									AI.mouseMove(topLeftX+tileLength*x-tileLength/2, topLeftY+tileHeight*(y+1)-tileHeight/2);
									AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
							if(currentTile.getLeft()!=null) // ensures the tile isn't a side tile
							{
								if(currentTile.getDown().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getDown().getLeft().getNodeColor().equals(UnknownLight))
									if(!currentTile.getDown().getLeft().isBomb())
									{
										AI.mouseMove(topLeftX+tileLength*(x-1)-tileLength/2, topLeftY+tileHeight*(y+1)-tileHeight/2);
										AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
										AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									}
							}
						}
						if(currentTile.getLeft()!=null) // ensures the tile isn't a side tile
						{
							if(currentTile.getLeft().getNodeColor().equals(UnknownDark) || currentTile.getLeft().getNodeColor().equals(UnknownLight))
								if(!currentTile.getLeft().isBomb())
								{
									AI.mouseMove(topLeftX+tileLength*(x-1)-tileLength/2, topLeftY+tileHeight*y-tileHeight/2); 
									AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
						}
					}
					else if(currentTile.getNodeColor()!=UnknownDark && currentTile.getNodeColor()!=UnknownLight)
					{
						if(currentTile.getUp().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getUp().getLeft().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getUp().getNodeColor().equals(UnknownDark) || currentTile.getUp().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getUp().getRight().getNodeColor().equals(UnknownDark) || currentTile.getUp().getRight().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getRight().getNodeColor().equals(UnknownDark) || currentTile.getRight().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getDown().getRight().getNodeColor().equals(UnknownDark) || currentTile.getDown().getRight().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getDown().getNodeColor().equals(UnknownDark) || currentTile.getDown().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getDown().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getDown().getLeft().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(currentTile.getLeft().getNodeColor().equals(UnknownDark) || currentTile.getLeft().getNodeColor().equals(UnknownLight))
							unknownAdjacent++;
						if(unknownAdjacent == currentTile.getBombsAdjacent())
						{
							if(currentTile.getUp().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getUp().getLeft().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getUp().getLeft());
							if(currentTile.getUp().getNodeColor().equals(UnknownDark) || currentTile.getUp().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getUp());
							if(currentTile.getUp().getRight().getNodeColor().equals(UnknownDark) || currentTile.getUp().getRight().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getUp().getRight());
							if(currentTile.getRight().getNodeColor().equals(UnknownDark) || currentTile.getRight().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getRight());
							if(currentTile.getDown().getRight().getNodeColor().equals(UnknownDark) || currentTile.getDown().getRight().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getDown().getRight());
							if(currentTile.getDown().getNodeColor().equals(UnknownDark) || currentTile.getDown().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getDown());
							if(currentTile.getDown().getLeft().getNodeColor().equals(UnknownDark) || currentTile.getDown().getLeft().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getDown().getLeft());
							if(currentTile.getLeft().getNodeColor().equals(UnknownDark) || currentTile.getLeft().getNodeColor().equals(UnknownLight))
								setBomb(currentTile.getLeft());
						}
					}
					else
						currentTile.setNodeColor(AI.getPixelColor(topLeftX+tileLength*x+tileLength/2, topLeftY+tileHeight*y+tileHeight/2));
					currentTile = currentTile.getDown();
				}
		}
	}
	public static void setBomb(Node bombTile) // sets the tile to a bomb and tells adjacent tiles
	{
		bombTile.setBomb(true);
		bombTile.getUp().getLeft().setBombsFound();
		bombTile.getUp().setBombsFound();
		bombTile.getUp().getRight().setBombsFound();
		bombTile.getRight().setBombsFound();
		bombTile.getDown().getRight().setBombsFound();
		bombTile.getDown().setBombsFound();
		bombTile.getDown().getLeft().setBombsFound();
		bombTile.getLeft().setBombsFound();
	}
}

/*
 * 
 * 	Colours:
 * 
 * Unknown Dark:  162, 209, 73
 * Unknown Light: 170, 215, 81
 * Known Light:   229, 194, 159
 * Known Dark:    215, 184, 153
 * 1:             25, 118, 210
 * 2:             56, 142, 60
 * 3              212, 53, 52
 * 
 * 		
		for(int x = 0; x < difficultyLength; x++) // topLeftX+tileLength*x+tileLength/2, topLeftY+tileHeight*y+tileHeight/2 // code that tests if all tiles are located
			for(int y = 0; y < difficultyHeight; y++)
			{
				AI.mouseMove(topLeftX+tileLength*x+tileLength/2, topLeftY+tileHeight*y+tileHeight/2);
				TimeUnit.MICROSECONDS.sleep(8000);
			}
 */
