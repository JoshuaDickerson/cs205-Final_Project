import java.util.ArrayList;
import java.util.Collections;

class Deck
{
	private ArrayList<Card> mainDeck;
	private Card tempCard;
	//constructor
	public Deck()
	{

		//FOR USE OF THE NORMAL DECK!
		mainDeck = new ArrayList<Card>();

		for(int i = 0; i < 9; i++)
		{
			tempCard = new Card(i);
			mainDeck.add(tempCard);
			mainDeck.add(tempCard);
			mainDeck.add(tempCard);
			mainDeck.add(tempCard);
		}
		for(int i = 0; i < 9; i++)
		{
			tempCard = new Card(9);
			mainDeck.add(tempCard);
		}
		
		for(int i = 0; i < 3; i++)
		{
			tempCard = new Card("peek");
			mainDeck.add(tempCard);
			tempCard = new Card("swap");
			mainDeck.add(tempCard);
			tempCard = new Card("draw2");
			mainDeck.add(tempCard);
		}
	}
	
	//FOR USE WITH THE DISCARD PILE.. ADDS CARDS TO THE TOP OF THE STACK NOT TO BOTTOM!
	public void addTopCard(Card tempCard)
	{
		if(this.mainDeck.size() == 0)
		{
			this.addCard(tempCard);
		}
		else
		{
			ArrayList<Card> oldDeck = this.mainDeck;
			ArrayList<Card> tempDeck = new ArrayList<Card>();
			tempDeck.add(tempCard);
			for(Card temp : oldDeck)
			{
				tempDeck.add(temp);
			}
			mainDeck.clear();
			this.mainDeck = tempDeck;
		}
	}
	
	//adds cards to the deck
	public void addCard(Card tempCard)
	{
		mainDeck.add(tempCard);
	}
	//deck size function using arraylist size
	public int size()
	{
		return mainDeck.size();
	}
	//returns a card at a given index in the deck
	public Card getCard(int i)
	{
		return mainDeck.get(i);
	}
	//random shuffle the deck
	public void shuffle()
	{
		Collections.shuffle(mainDeck);
	}
	
	public void clear()
	{
		this.mainDeck.clear();
	}
	//return the top card of the deck
	public Card getTopCard()
	{
		Card tempCard = mainDeck.get(0);
		mainDeck.remove(0);
		return tempCard;
	}
	//System.out.print THE ENTIRE DECK
	public void showDeck()
	{
		for(int i = 0; i < this.size(); i++)
		{
			System.out.println("Card" + i + ": " + this.getCard(i));
		}
	}
}