import javax.swing.*;
import java.util.*;
import java.awt.*;

public class currentScore
{
	private int numPlayers;
	private playerScore[] allScores;
	private int currentIndex = 0;
	private gameState state;
	//REPLACE OBJECT WITH 
	public currentScore(int _numPlayers, gameState _state)
	{
		this.numPlayers = _numPlayers;
		this.allScores = new playerScore[_numPlayers];
		this.state = _state;
		//MUST MANUALLY ADD PLAYER SCORES TO CURRENT SCORE ARRAY WITH ADD PLAYER SCORE
	}
	
	public void addPlayerScore(playerScore score)
	{
		this.allScores[this.currentIndex] = score;
		this.currentIndex++;
	}
	
	public int getPlayerCount()
	{
		return this.currentIndex;
	}
	
	public playerScore getPlayerScoreAtIndex(int index)
	{
		return this.allScores[index];
	}
	
	public gameState getGameState()
	{
		return this.state;
	}
}