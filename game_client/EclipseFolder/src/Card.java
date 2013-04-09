class Card
{
	//Private variables
	private String special;
	private int rank;
	private String pictureName;
	private boolean faceup;
	private boolean isSpecial;
	
	//constructor for power cards
	public Card(String special)
	{
		this.isSpecial = true;
		this.special = special;
		rank = -1;
	}
	//constructor for number cards
	public Card(int rank)
	{
		this.isSpecial = false;
		this.rank = rank;
		special = "";
	}
	
	public void setSpecial(String special)
	{
		this.special = special;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public void setPictureName(String pictureName)
	{
		this.pictureName = pictureName;
	}
	public void setFaceUp()
	{
		faceup = true;
	}
	public String getSpecial()
	{
		return special;
	}
	public int getRank()
	{
		return rank;
	}
	public boolean getFaceUp()
	{
		return faceup;
	}
	
	public String getPictureName()
	{
		return this.pictureName;
	}
	
	public boolean isSpecial()
	{
		if(this.isSpecial == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String toString()
	{
		String returnString;
		if(this.rank >= 0)
		{
			returnString = (this.rank + "");
		}
		else {
			returnString = (this.special);
		}
		return returnString;
	}	
}