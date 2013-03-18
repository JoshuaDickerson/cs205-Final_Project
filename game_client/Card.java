class Card
{
	//Private variables
	private String special;
	private int rank;
	private String pictureName;
	private boolean faceup;
	
	//constructor for power cards
	public Card(String special)
	{
		this.special = special;
		rank = -1;
	}
	//constructor for number cards
	public Card(int rank)
	{
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
	public void setPictureFolder(String newPictureName)
	{
		pictureName = newPictureName;
		pictureName += "/";
	}
	public String getPictureName()
	{
		pictureName = "cards/";
		if(this.rank >= 0)
		{
			pictureName += this.rank+".png";
		}
		else if(this.special == "swap")
		{
			pictureName += "swap.png";
		}
		
		else if(this.special == "draw2")
		{
			pictureName += "draw2.png";
		}
		else if(this.special == "peek")
		{
			pictureName += "peek.png";
		}
		return pictureName;
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