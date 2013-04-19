/*
 * Author: Dana Desautes
 * Description: This is the player class.
 * Other Contributors: Anders Melen made updates to the class that were required for the game to work
 */

class Player
{
	private String playername;
	private boolean isHuman;
	private int id;
	private int score;
	private int roundsWon;
	private int totalScore;
	public Hand myHand;
	
	//constructor
	public Player(boolean isHuman, int id, String name, Hand myHand)
	{
		this.isHuman = isHuman;
		this.id = id;
		this.playername = name;
		this.myHand = myHand;
		this.roundsWon = 0;
		this.totalScore = 0;
	}
	
	public void setHand(Hand myHand)
	{
		this.myHand = myHand;
	}
	
	public Hand getHand()
	{
		return this.myHand;
	}	
	
	public void setHuman(boolean isHuman)
	{
		this.isHuman = isHuman;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public void setID(int id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		playername = name;
	}
	public void setRoundsWon(int _roundsWon)
	{
		this.roundsWon = _roundsWon;
	}
	public int getRoundsWon()
	{
		return this.roundsWon;
	}
	public void setTotalScore(int _score)
	{
		this.totalScore = _score;
	}
	public int getTotalScore()
	{
		return this.totalScore;
	}
	public int getScore()
	{
		return score;
	}
	public boolean getHumanity()
	{
		return isHuman;
	}
	public int getID()
	{
		return id;
	}
	public String getName()
	{
		return playername;
	}
}