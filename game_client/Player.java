class Player
{
	private String playername;
	private boolean isHuman;
	private int id;
	private int score;
	
	//constructor
	public Player(boolean isHuman, int id, String name)
	{
		this.isHuman = isHuman;
		this.id = id;
		playername = name;
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