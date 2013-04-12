class Card
{
	//Private variables
	private String special;
	private int rank;
	private String pictureName;
	private boolean faceup;
	private boolean isSpecial;
	
	/** Constructor for special cards. Takes the type of card
	 * (draw2, swap, peek) as input.
	 * 
	 * @param special A String giving the type of card.
	 */
	public Card(String special)
	{
		this.isSpecial = true;
		this.special = special;
		rank = -1;
	}
	
	/** Constructor for number cards. Takes the rank of card
	 *  (1-9) as input.
	 * 
	 *  @param rank An int giving the rank of the card.
	 */
	public Card(int rank)
	{
		this.isSpecial = false;
		this.rank = rank;
		special = "";
	}
	
	/** Class method used to set the type of card (for special cards).
	 *  Takes the type of card as input.
	 * 
	 *  @param special A String giving the type of card.
	 */
	public void setSpecial(String special)
	{
		this.special = special;
	}
	
	/** Class method used to set number for number cards. Takes the rank of card
	 *  (1-9) as input.
	 * 
	 *  @param rank An int giving the rank of the card.
	 */
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	
	/** Class method used to set filename of the image associated with the card.
	 *  Takes filename (e.g. draw2.png) as input. 
	 *
	 *  @param pictureName An string giving the filename of the image.
	 */
	public void setPictureName(String pictureName)
	{
		this.pictureName = pictureName;
	}
	
	/** Class method used to set card face up (possibly unused). No input.
	 * 
	 * 
	 */
	public void setFaceUp()
	{
		faceup = true;
	}
	
	/** Class method used to get the type of card (for special cards).
	 *  Returns type of card.
	 * 
	 *  @return	A String containing the type of card (draw2, swap, peek)
	 */
	public String getSpecial()
	{
		return special;
	}
	
	/** Class method used to get number for number cards. Returns the rank of card
	 *  (1-9).
	 * 
	 *  @return  An int containing the rank of the card.
	 */
	public int getRank()
	{
		return rank;
	}
	
	/** Class method to get whether the card is face up or not. Returns a boolean.
	 *
	 * 
	 *  @return	 An boolean containing whether the card is face up or not.
	 */
	public boolean getFaceUp()
	{
		return faceup;
	}
	
	/** Class method used to set file path of the image associated with the card.
	 *  Takes filepath (e.g. pic/draw2.png) as input. 
	 *
	 *  @param newPictureName An string giving the filename of the image.
	 */
	public void setPictureFolder(String newPictureName)
	{
		pictureName = newPictureName;
		pictureName += "/";
	}
	
	/** Class method used to get filename of the image associated with the card.
	 *  Returns string.
	 *
	 *  @return  A string containing the filename of the image.
	 */
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
	
	
	/** Class method used to set filename of the image associated with the card.
	 *  Returns boolean. 
	 *
	 *  @return  A boolean containing whether or not the card is special or not.
	 */
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