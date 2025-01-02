/* 
This is a minesweeper solver I developed. 
To us it, you will need to copy the code in this file into a program that can run java code.
After you click run, you will need to quickly swap over to a google chrome tab open to Google's minesweeper on easy mode.
The project will only run if your screen resolution is 1920x1080.
Other resolutions will be supported in my updated minesweeper solver
*/

package code;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FinalProject 
{
	public static void main(String [] args) throws Exception
	{
		
		Robot AI = new Robot();
		int tileNumber = 0, minesAdjacent = 0, possibleTiles = 0, minesFound = 0, mineNumber = 10;
		boolean mines[][] = new boolean[10][8], firstTimeDone = false;
		Color tileColours[][] = new Color[10][8];
		Color UnknownDark = new Color(162, 209, 73), UnknownLight = new Color(170, f215, 81), KnownDark = new Color(215, 184, 153), KnownLight = new Color(229, 194, 159); // tiles colours
		Color num1 = new Color(25, 118, 210), num2 = new Color(56, 142, 60), num3 = new Color(211, 47, 47), num4 = new Color(123, 31, 162), num5 = new Color(245, 154, 41), num6 = new Color(8, 152, 167), num7 = new Color(166, 145, 124); //Number Colours
		
		TimeUnit.SECONDS.sleep(3);
		//AI.mouseMove(757, 428); top left 1 location
		//AI.mouseMove(759, 420); top left 2 location
		
		AI.mouseMove(931, 572); // starts the game by pressing the middle most tile
		AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		TimeUnit.SECONDS.sleep(2);
		
		
		
		while(minesFound < mineNumber) // game will run until it has found all mines
		{
			minesFound = 0;
			for(int x = 0; x < 10; x++)  // calculates the ammount of mines that have been found
				for(int y = 0; y < 8; y++)
					if(mines[x][y])
						minesFound++;
			
			for(int x = 0; x < 10; x++) // reads the color of each tile
				for(int y = 0; y < 8; y++)
				{
					tileColours[x][y] = AI.getPixelColor(759+(45*x), 420+(45*y));
					if(!tileColours[x][y].equals(num2))
						tileColours[x][y] = AI.getPixelColor(757+(45*x), 428+(45*y));
				}
			for(int horizontal = 0; horizontal < 10; horizontal++)
				for(int vertical = 0; vertical < 8; vertical++)
				{
					if(!tileColours[horizontal][vertical].equals(UnknownDark) || !tileColours[horizontal][vertical].equals(UnknownLight))
					{
						if(tileColours[horizontal][vertical].equals(num1))
							tileNumber = 1;
						if(tileColours[horizontal][vertical].equals(num2))
							tileNumber = 2;
						if(tileColours[horizontal][vertical].equals(num3))
							tileNumber = 3;
						if(tileColours[horizontal][vertical].equals(num4))
							tileNumber = 4;
						if(tileColours[horizontal][vertical].equals(num5))
							tileNumber = 5;
						if(tileColours[horizontal][vertical].equals(num6))
							tileNumber = 6;
						if(tileColours[horizontal][vertical].equals(num7))
							tileNumber = 7;
						
						int xchange = -1, ychange = -1, ymax = 0, xmax = 0;
						if(horizontal == 0 && vertical == 0)
						{
							ychange = 0;
							xchange = 0;
						}
						else if(horizontal == 0 && vertical == 7)
						{
							xchange = 0;
							ymax = -1;
						}
						else if(horizontal == 9 && vertical == 7)
						{
							ymax = -1;
							xmax = -1;
						}
						else if(horizontal == 9 && vertical == 0)
						{
							ychange = 0;
							xmax = -1;
						}
						else if(horizontal != 0 && vertical == 0)
						{
							ychange = 0;
							xchange = -1;
						}
						else if(horizontal == 0 && vertical != 0)
						{
							ychange = -1;
							xchange = 0;
						}
						else if(horizontal == 9 && vertical != 7)
							xmax = -1;
						else if(horizontal != 9 && vertical == 7)
							ymax = -1;
						
						minesAdjacent = 0;
						possibleTiles = 0;
						boolean possible[][] = new boolean[3][3];
						if(!tileColours[horizontal][vertical].equals(UnknownDark) && !tileColours[horizontal][vertical].equals(UnknownLight) && !tileColours[horizontal][vertical].equals(KnownDark) && !tileColours[horizontal][vertical].equals(KnownLight))
						{
							for(int x = xchange; x < 2+xmax; x++)
								for(int y = ychange; y < 2+ymax; y++)
								{
									if(x != 0 || y != 0)
										if(mines[x+horizontal][y+vertical] == false) // checks which tiles could be anything
											if(tileColours[x+horizontal][y+vertical].equals(UnknownDark) || tileColours[x+horizontal][y+vertical].equals(UnknownLight))
											{
												possible[x+1][y+1] = true;
												possibleTiles++;
											}
									if(mines[x+horizontal][y+vertical]) // checks how many mines are next to the tile
										minesAdjacent++;
								}
							if(minesAdjacent == tileNumber && firstTimeDone) // clicks all non bomb tiles if all bombs next to a tile have been found
							{
								for(int x = xchange; x < 2+xmax; x++)
									for(int y = ychange; y < 2+ymax; y++)
										if(tileColours[x+horizontal][y+vertical].equals(UnknownDark) || tileColours[x+horizontal][y+vertical].equals(UnknownLight))
											if(!mines[x+horizontal][y+vertical])
											{
												AI.mouseMove(757+(45*(x+horizontal)), 428+(45*(y+vertical)));
												TimeUnit.MILLISECONDS.sleep(10);
												AI.mousePress(InputEvent.BUTTON1_DOWN_MASK);
												AI.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
											}
							}	
							else if(possibleTiles == tileNumber - minesAdjacent) // turns all possible tiles to mines when the tile number and ammount of possible tiles are the same
							{
								for(int x = xchange; x < 2+xmax; x++)
									for(int y = ychange; y < 2+ymax; y++)
										if(possible[x+1][y+1])
											if(x != 0 || y != 0)
												mines[x+horizontal][y+vertical] = true;
							}
							firstTimeDone = true;
						}
					}
				}
		}
	}
}

/*
Colours(RGB):
 - Unknown Tiles:
 	- Dark: 162, 209, 73;
 	- Light: 170, 215, 81;
 - Known Tiles
 	- Dark: 215, 184, 153;
 	- Light: 229, 194, 159;
 - Numbers:
 	-1: 25, 118, 210;
 	-2: 56, 142, 60;
 	-3: 211, 47, 47;
 	-4: 123, 31, 162;
 	-5: 245, 154, 41;
 	-6: 8, 152, 167;
 	-7: 166, 145, 124
 	
Pixels Between Tiles
Easy:   45
Medium: 
Hard:   
 	
System.out.println(MouseInfo.getPointerInfo().getLocation());
System.out.println(AI.getPixelColor(533, 169));

for(int x = 0; x < 10; x++)
				for(int y = 0; y < 8; y++)
				{
					if(tileColours[x][y].equals(num1))
						System.out.println("1");
					if(tileColours[x][y].equals(num2))
						System.out.println("2");
					if(tileColours[x][y].equals(num3))
						System.out.println("3");
					if(tileColours[x][y].equals(num4))
						System.out.println("4");
					if(tileColours[x][y].equals(num5))
						System.out.println("5");
					if(tileColours[x][y].equals(num6))
						System.out.println("6");
					if(tileColours[x][y].equals(num7))
						System.out.println("7");
					if(tileColours[x][y].equals(KnownDark) || tileColours[x][y].equals(KnownLight))
						System.out.println("Known");
					if(tileColours[x][y].equals(UnknownDark) || tileColours[x][y].equals(UnknownLight))
						System.out.println("Unknown");
				}
				
				
				//System.out.println("Horizontal " + horizontal); 
				//System.out.println("Vertical " + vertical);
				//System.out.println(mines[horizontal][vertical]);
				//System.out.println("Possible " + possibleTiles);
				//System.out.println("Adjacent " + minesAdjacent);
				//System.out.println("TilesNumber " + tileNumber);
*/
