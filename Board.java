/**
 * 
 */
/**
 * @author srihari.kadali
 *
 */


import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Board{
	private char board[][];
	private int boardSize;
	
	public Board(int boardSize)
	{   if(boardSize<4)
			throw new RuntimeException("Board size must be 4 or greater.");
	    this.boardSize=boardSize;
	    board= new char[this.boardSize][this.boardSize];
	}
	
	
	public void createBoard()
	{

		try {
			List<String> listOfStrings
					= new ArrayList<String>();
			BufferedReader bf = new BufferedReader(
					new FileReader("test.txt"));
			String line = bf.readLine();
			while (line != null) {
				listOfStrings.add(line);
				line = bf.readLine();
			}
			bf.close();
			String[] array
					= listOfStrings.toArray(new String[0]);
	
			for(int row=0;row<this.boardSize;row++)
			 {

				for(int col=0,i=0;col< this.boardSize;col++,i=i+2) {
					this.board[row][col] = array[row].charAt(i);
				}
			}

		} catch (IOException e) {
			System.out.println("exception caught"+e);
		}
	}
	
	public void printBoard()
	{
		for(int row=0;row<this.boardSize;row++)
	    {
	        for(int col=0;col<this.boardSize;col++)
	        {
	            System.out.print("  "+this.board[row][col]);
	        }
	        System.out.println();
	    }
	}
	
	
	
	public static void main(String[] args)
	{
//		Scanner sc = new Scanner()
		Scanner sc=new Scanner(System.in);
		System.out.println("please enter the size of the board");
		int size=sc.nextInt();
		Board b = new Board(size);
		b.createBoard();
		b.printBoard();
	}
}
