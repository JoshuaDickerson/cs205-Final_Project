import java.util.ArrayList;
import java.util.Collections;

class Deck
{
	private ArrayList<Card> mainDeck;
	private Card tempCard;
	
	/** Constructor to create the deck. No parameters or return values.
	 *
	 *
	 */
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
	
	/** Class method to add card to the top of the deck (used for the discard pile). Takes card to add as parameter.
	 *
	 * @param tempCard Card object containing the card to add to the top of the deck.
	 *
	 */
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
	
	/** Class method to add card to the (bottom of) the deck. Takes card to add as parameter.
	 *
	 *  @param tempCard Card object containing the card to add to the deck.
	 *
	 */
	public void addCard(Card tempCard)
	{
		mainDeck.add(tempCard);
	}

	/** Class method to get size of the deck. Returns size as int.
	 *
	 *  @return int object contaning the size of the deck.
	 */
	public int size()
	{
		return mainDeck.size();
	}
	
	/** Class method to get card at specified index. Takes integer as parameter and returns card at that index.
	 *
	 *  @param i int containing the index the card will be retrieved at.
	 *  @return 	Card object contaning the card at the specified index.
	 */
	public Card getCard(int i)
	{
		return mainDeck.get(i);
	}
	
	/** Class method to shuffle the deck. No parameters/return values.
	 *
	 *  	 
	 */
	public void shuffle()
	{
		Collections.shuffle(mainDeck);
	}
	
	/** Class method to clear the deck. No parameters/return values.
	 *
	 *  	 
	 */
	public void clear()
	{
		this.mainDeck.clear();
	}
	
	/** Class method to get the top card of the deck. Removes the top card and returns a Card object.
	 *
	 *  @return 	Card object containing the top card of the deck
	 */
	public Card getTopCard()
	{
		Card tempCard = mainDeck.get(0);
		mainDeck.remove(0);
		return tempCard;
	}
	
	/** Class method to System.out.println() the deck to console. No parameters/return values.
	 *
	 *  	 
	 */
	public void showDeck()
	{
		for(int i = 0; i < this.size(); i++)
		{
			System.out.println("Card" + i + ": " + this.getCard(i));
		}
	}
}