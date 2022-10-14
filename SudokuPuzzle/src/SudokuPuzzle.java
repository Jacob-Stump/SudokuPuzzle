/* Jacob Stump
 * COSC 2430.501
 * 11/17/21
 * Sudoku Puzzle Project
 */

import java.util.Arrays;
import java.util.Scanner;

public class SudokuPuzzle 
{

	public static int[][]Board = new int[9][9];
	public static boolean[][]BoardSpaces = new boolean [9][9];
	
	public static void setInitalValues()
	{
		for (int row = 0; row < Board.length; row++)
		{
			for (int col = 0; col < Board[row].length; col++)
			{
				if (Board[row][col] > 0)
				BoardSpaces[row][col] = false;
				
				else 
					BoardSpaces[row][col] = true;
			}
		}
	}

	public static void addInitialValues(int[][]Board) 
	{
		for (int row = 0; row < Board.length; row++)
		
		Board[0] = new int[] {1,2,3,4,9,7,8,6,5};
		Board[1] = new int[] {4,5,9,1,6,8,2,3,7};
		Board[2] = new int[] {6,7,8,2,5,3,1,4,9};
		Board[3] = new int[] {3,4,5,6,1,2,7,9,8};
		Board[4] = new int[] {2,1,6,7,8,9,3,5,4};
		Board[5] = new int[] {9,8,7,3,4,5,6,1,2};
		Board[6] = new int[] {8,3,1,5,2,4,9,7,6};
		Board[7] = new int[] {7,9,4,8,3,6,5,2,1};
		Board[8] = new int[] {5,6,2,9,7,1,4,8,0};
	}
	
	public static void addGuess() 
	{
		boolean checkedPuzzle;
		boolean checkedGuess;
		boolean checkedInitial;
	do
	{
			Scanner value = new Scanner(System.in);
			System.out.println("Enter two integers between 0 and 8, inclusive, seperated by a space for desired coordinate on puzzle. Example: 6 6 (row column)");
			System.out.println("The coordinate 0 0 is the top left corner of your puzzle while the bottom right is 8 8");
			do
			{	
				int row;
				int col;
				do
				{
					String input = value.nextLine();
					row = Integer.parseInt(input.substring(0,1));
					col = Integer.parseInt(input.substring(2,3));
					checkedInitial = checkInitial(row, col);
				}
				while (checkedInitial == false);
			
				do
				{
					System.out.println("What number (1-9) would you like to place at ["+row+"]["+col+"]?");
					String inputnum = value.nextLine();
					int number = Integer.parseInt(inputnum.substring(0,1));
					checkedGuess = checkGuess (row, col, number); 	
					Board[row][col] = number;
					checkedPuzzle = checkPuzzle();
					for (int i = 0; i < Board.length; i++) 
					{
						System.out.println(Arrays.toString(Board[i]));
					}	
				}
				while (checkedGuess == false);
			}
			while (checkedGuess == false);
	}
	while (checkedPuzzle == false);
	
	System.out.println("Well done! You've finished this puzzle!");
	System.exit(0);
	}
	
	public static void printArray() 
	{
		for (int i = 0; i < Board.length; i++) 
		{
			System.out.println(Arrays.toString(Board[i]));
			
		}
	}
	
	public static boolean checkGuess(int row, int col, int number)
	{
		for (int i = 0; i < Board[row].length; i++)
		{	
			if (number == Board[row][i])
			{	
				System.out.println("Conflict with row, try again!");
				return false;
			}
		
		}
		
		for (int j = 0; j < Board[col].length; j++)
		{
			if (number == Board[j][col])
			{	
				System.out.println("Conflict with column, try again!");
				return false;
			}	
		}
		
		int topRow = row - (row % 3);
		int leftCol = col - (col % 3);
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{	
				if (Board[topRow + i][j + leftCol] == number)
				{
					System.out.println("Conflict with 3x3, try again!");
				return false; 
				}
			}
		}		
		return true;
	}
	
	public static boolean checkPuzzle()
	{
		for (int row = 0; row < Board.length; row++)
		{
			for (int col = 0; col < Board[row].length; col++)
			if (Board[row][col] == 0)
				return false;
		}
			return true;
	}
	
	public static boolean checkInitial(int row, int col)
	{	
		if (BoardSpaces[row][col] == false)
		{	System.out.println("This is a predetermined puzzle value! Try another coordinate.");
			return false;
		}	
		else
			return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println("This is your Sudoku puzzle to solve, good luck!");
		
		addInitialValues(Board);
		
		setInitalValues();
		
		printArray();
		 
		addGuess();	
	
	}

}
