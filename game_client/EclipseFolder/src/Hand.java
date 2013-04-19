/*
 * Author: Dana Desautes
 * Description: This is the Hand class.
 * Other Contributors: Anders Melen made updates to the class that were required for the game to work
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Hand
{
	private Card[] hand;
	private int id;
	
	//default constructor	
	public Hand(int id)
	{		
		this.id = id;
		this.hand = new Card[4];
	}
	//size function for hand using arraylist size
	public int size()
	{
		return hand.length;
	}
	
	public void addCard(Card tempCard)
	{
		for(int i = 0; i < this.hand.length; i++)
		{
			if(this.hand[i] == null)
			{
				this.hand[i]=(tempCard);
				break;
			}
		}
	}
	//removes a card from the hand at a given index
	public Card replaceCard(int index, Card newCard)
	{
		Card removed = this.hand[index];
		this.hand[index] = newCard;
		return removed;
	}
	//gets a card from the hand at a given index
	public Card getCard(int i)
	{
		return hand[i];
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
		for(int i = 0; i < this.hand.length; i++)
		{
			this.hand[i] = null;
		}
	}

	public void showHand()
	{
		for(int i = 0; i < this.size(); i++)
		{
			System.out.println(this.hand[i]);
		}
	}
	
	public int getScore()
	{
		int score = 0;
		
		for(int i = 0; i < this.hand.length; i++)
		{
			Card tempcard = this.hand[i];
			if(tempcard.isSpecial())
			{
				score = score + 10;
			}
			else
			{
				score = score + tempcard.getRank();
			}
		}
	
		return score;
	}
}