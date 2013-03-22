import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Hand
{
	private ArrayList<Card> hand;
	private int id;
	
	//default constructor	
	public Hand(int id)
	{		
		hand = new ArrayList<Card>();
		this.id = id;
	}
	//size function for hand using arraylist size
	public int size()
	{
		return hand.size();
	}
	
	public void set(int index, Card _card)
	{
		this.hand.set(index, _card);
	}
	public void addCard(Card tempCard)
	{
		hand.add(tempCard);
	}
	//removes a card from the hand at a given index
	public void remove(int i)
	{
		hand.remove(i);
	}
	//gets a card from the hand at a given index
	public Card getCard(int i)
	{
		return hand.get(i);
	}
	public void setCard(int i, Card tempCard)
	{
		hand.set(i, tempCard);
	}
	//returns true or false is hand is empty
	public boolean isEmpty()
	{
		if(this.size() == 0)
			return true;
		else
			return false;
	}
	//clear function for hand using arraylist clear
	public void clear()
	{
		hand.clear();
	}
	//removes all cards from the hand and puts them at the end of the deck
	public void clearHand(Deck myDeck)
	{
		for(int i = 0; i < this.size(); i++)
		{
			Card tempCard = this.getCard(i);
			myDeck.addCard(tempCard);
		}
		this.clear();
	}
	/*public String toString()
	{
		for(int i = 0; i < this.size(); i++)
		{
			return this.getCard(i).toString();
		}
	}*/
	//system.out.print THE ENTIRE HAND
	public void showHand()
	{
		for(int i = 0; i < this.size(); i++)
		{
			System.out.println(this.getCard(i));
		}
	}
	
	public int getScore()
	{
		int score = 0;
		
		for(int i = 0; i < this.size(); i++)
		{
			Card tempcard = this.getCard(i);
			score = score + tempcard.getRank();
		}
	
		return score;
	}
}