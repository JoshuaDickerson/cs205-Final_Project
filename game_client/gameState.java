import javax.swing.*;
import java.util.*;
import java.awt.*;

public class gameState
{
	private int status;
	private int winCon;
	private int player;
	private int mode;	
			
	//CONSTRUCTOR
	public gameState()
	{
		this.status = 0;
		this.winCon = -1;
		this.player = -1;
		this.mode = -1;
	}

	//SETTER
	public void updateGameState(int _status, int _winCon, int _player, int _mode)
	{
		this.status = _status;
		this.winCon = _winCon;
		this.player = _player;
		this.mode = _mode;
	}
	
	//GETTERS
	public int getStatus()
	{
		return this.status;
	}
	public int getWinCon()
	{
		return this.winCon;
	}
	public int getPlayer()
	{
		return this.player;
	}
	public int getMode()
	{
		return this.mode;
	}
	
	//PRINTING
	public void print()
	{
		System.out.println("PRINT GAME SATE");
		System.out.println("Game Status: " + this.status);
		System.out.println("Win Con: " + this.winCon);
		System.out.println("Cur Player: " + this.player);
		System.out.println("Game Mode: " + this.mode);
	}	
	
	/*
	//TEST MAIN
	public static void main(String [ ] args)
	{
		gameState newGameState = new gameState();
		newGameState.print();
		//UPDATE THE PARAMETERS YOU WANT, TO KEEP THEM THE SAME CALL THE GETTER FOR THAT PARAMETER LIKE FOLLOWS!
		newGameState.updateGameState(newGameState.getStatus(),TIMED_PLAY,PLAYER_THREE,NORMAL_PLAY); 
		newGameState.print();
	}
	*/
}
