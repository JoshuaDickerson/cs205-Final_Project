import javax.swing.*;
import java.util.*;
import java.awt.*;

public class currentScore
{
	private int numPlayers;
	private playerScore[] allScores;
	private int currentIndex = 0;
	//REPLACE OBJECT WITH 
	public currentScore(int _numPlayers)
	{
		this.numPlayers = _numPlayers;
		this.allScores = new playerScore[_numPlayers];
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
}