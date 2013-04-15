import javax.swing.*;
import java.util.*;
import java.awt.*;

public class gameState
{
	private int status;
	private int winCon;
	private int player;
	private int mode;
	private int numPlayers;	
	private int roundNum;
	private int playerWhoKnocked;
	private int difficulty;
			
	//CONSTRUCTOR
	public gameState(int _numPlayers)
	{
		this.status = 0;
		this.winCon = -1;
		this.player = -1;
		this.mode = -1;
		this.numPlayers = _numPlayers;
		this.roundNum = 1;
		this.playerWhoKnocked = -1;
	}

	//SETTER
	public void updateGameState(int _status, int _winCon, int _player, int _mode, int _roundNum)
	{
		this.status = _status;
		this.winCon = _winCon;
		this.player = _player;
		this.mode = _mode;
		this.roundNum = _roundNum;
	}
	
	public void setDifficulty(int diff)
	{
		this.difficulty = diff;
	}
	
	public int getDifficulty()
	{
		return this.difficulty;
	}
	
	public int getPlayerWhoKnocked()
	{
		return this.playerWhoKnocked;
	}
	
	public void setPlayerWhoKnocked(int _knockedPlayer)
	{
		this.playerWhoKnocked = _knockedPlayer;
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
	public int getRoundNum()
	{
		return this.roundNum;
	}
	
	public void setRoundNum(int _roundNum)
	{
		this.roundNum = _roundNum;
	}
	
	public void setPlayer(int _curPlayer)
	{
		this.player = _curPlayer;
	}
	
	public int numPlayers()
	{
		return this.numPlayers;
	}
	
	//PRINTING
	public void print()
	{
		System.out.println("Game Status: " + this.status);
		System.out.println("Win Con: " + this.winCon);
		System.out.println("Cur Player: " + this.player);
		System.out.println("Cur Round #: " + this.roundNum);
		System.out.println("Game Mode: " + this.mode);
		System.out.println("Number of Players: " + this.numPlayers);
		System.out.println("Player Who Knocked: " + this.playerWhoKnocked);
	}	
	
	//PRINTING
	public String returnGameState()
	{
		String status = "Game Status: " + this.status + "\n";
		status = status + "Win Con: " + this.winCon + "\n";
		status = status + "Game Mode: " + this.mode + "\n";
		status = status + "Cur Player: " + this.player + "\n";
		status = status + "Cur Round #: " + this.roundNum + "\n";
		status = status + "Number of Players: " + this.numPlayers + "\n";
		status = status + "Player Who Knocked: " + this.playerWhoKnocked + "\n";
		return status;
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
