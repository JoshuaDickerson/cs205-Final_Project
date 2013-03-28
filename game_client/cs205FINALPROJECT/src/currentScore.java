import javax.swing.*;
import java.util.*;
import java.awt.*;

public class currentScore
{
	private int numPlayers;
	private Player[] allPlayers;
	private int currentIndex = 0;
	private gameState state;
	private UUID uniqueID;
	private boolean gameOver;
	private boolean roundOver;
	private int roundCount;
	//REPLACE OBJECT WITH 
	public currentScore(int _numPlayers, gameState _state, UUID _uniqueID, boolean _gameOver, boolean _roundOver, int _roundCount, Deck mainDeck, Deck discard)
	{
		this.numPlayers = _numPlayers;
		this.allPlayers = new Player[_numPlayers];
		this.state = _state;
		this.uniqueID = _uniqueID;
		this.gameOver = _gameOver;
		this.roundOver = _roundOver;
		this.roundCount = _roundCount;		
		//MUST MANUALLY ADD PLAYER SCORES TO CURRENT SCORE ARRAY WITH ADD PLAYER SCORE
	}
	
	public void addPlayer(Player _player)
	{
		this.allPlayers[this.currentIndex] = _player;
		this.currentIndex++;
	}
	
	public int getPlayerCount()
	{
		return this.currentIndex;
	}
	
	public Player getPlayerAtIndex(int index)
	{
		return this.allPlayers[index];
	}
	
	public gameState getGameState()
	{
		return this.state;
	}
}