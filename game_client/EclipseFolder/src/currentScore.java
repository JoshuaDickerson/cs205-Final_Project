/*
 * Author: Anders Melen
 * Description: This is the wrapper object that allows us to combine all the the scores,
 * total scores, players, deck and discard pile for analytical purposes ont he server,
 * the more information the better!
 */

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
	private Deck mainDeck;
	private Deck discard;
	//REPLACE OBJECT WITH 
	public currentScore(int _numPlayers, gameState _state, UUID _uniqueID, boolean _gameOver, boolean _roundOver, int _roundCount, Deck _mainDeck, Deck _discard)
	{
		this.numPlayers = _numPlayers;
		this.allPlayers = new Player[_numPlayers];
		this.state = _state;
		this.uniqueID = _uniqueID;
		this.gameOver = _gameOver;
		this.roundOver = _roundOver;
		this.roundCount = _roundCount;		
		this.mainDeck = _mainDeck;
		this.discard = _discard;
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