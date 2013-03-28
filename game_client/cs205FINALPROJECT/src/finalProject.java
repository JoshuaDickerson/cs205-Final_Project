import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import java.util.Scanner;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class finalProject extends JApplet implements ActionListener
{
	//GAME STATE DEFINITIONS
	//UNIVERSAL NULL CASE
	public static final int NULL = -1;
	//Game Status
	public static final int INSTRUCTIONS = 0;
	public static final int KNOCKED_ROUND = 1;
	public static final int NORMAL_ROUND = 2;
	public static final int ROUND_OVER = 3;
	public static final int GAME_OVER = 4;
	//Win Condiction
	public static final int NUM_ROUNDS = 0;
	public static final int TIMED_PLAY = 1;
	public static final int HIGH_SCORE = 2;
	//Current Player
	public static final int PLAYER_ONE = 0;
	public static final int PLAYER_TWO = 1;
	public static final int PLAYER_THREE = 2;
	public static final int PLAYER_FOUR = 3;
	public static final int PLAYER_FIVE = 4;
	public static final int PLAYER_SIX = 5;
	//Game Mode
	public static final int NORMAL_PLAY = 0;
	public static final int DEMO_PLAY = 1;
	//GUI MODE
	public static final int MAIN_MENU = 0;
	public static final int GAME_SCREEN = 1;
	public static final int CREDITS_SCREEN = 2; //not sure if we need this...
	
	//GUI
	JButton jbnLeft;
	JButton jbnRight;
	JButton jbnMiddle;
	JLabel title;
	JTextField nameText;
	ArrayList<JComponent> guiObjects = new ArrayList<JComponent>(50);
	
	public boolean clickDiscard = false;
	public boolean clickDeck = false;
	
	public void setUpGUI(int guiMode)
	{
		clearGUI();
		setSize(500, 300);
		switch(guiMode)
		{
			case(0): //INITIAL SCREEN
				//TITLE
				title = new JLabel("WHATS YOUR NAME?");
				title.setHorizontalAlignment(JLabel.CENTER);
				getContentPane().add(title, BorderLayout.NORTH);
				guiObjects.add(title);
				
				//MIDDLE
				nameText = new JTextField(10);
				getContentPane().add(nameText, BorderLayout.CENTER);
				guiObjects.add(nameText);
				
				//Num Rounds
				jbnLeft = new JButton("5 Rounds");
				jbnLeft.setEnabled(true);
				jbnLeft.addActionListener(this);
				jbnLeft.setActionCommand("numRounds");
				guiObjects.add(jbnLeft);
				
				//Timed Match
				jbnMiddle = new JButton("Timed Match");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("timedMatch");
				jbnMiddle.addActionListener(this);
				guiObjects.add(jbnMiddle);
				
				//High Score
				jbnRight = new JButton("High Score");
				jbnRight.setEnabled(true);
				jbnRight.setActionCommand("highScore");
				jbnRight.addActionListener(this);
				guiObjects.add(jbnRight);
				
				
				getContentPane().add(jbnLeft, BorderLayout.EAST);
				getContentPane().add(jbnMiddle, BorderLayout.CENTER);
				getContentPane().add(jbnRight, BorderLayout.WEST);
				break;
			case(1): //DECK OR DISCARD
				System.out.println("COUNT: " + getContentPane().getComponentCount());
				//TITLE
				title = new JLabel("WHERE DO YOU WANT TO DRAW FROM?");
				title.setHorizontalAlignment(JLabel.CENTER);
				getContentPane().add(title, BorderLayout.NORTH);
				
				//LEFT
				jbnLeft = new JButton("Deck");
				jbnLeft.setEnabled(true);
				jbnLeft.addActionListener(this);
				jbnLeft.setActionCommand("drawDeck");
				
				//MIDDLE
				jbnMiddle = new JButton("Discard");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("drawDiscard");
				jbnMiddle.addActionListener(this);
				
				
				getContentPane().add(jbnLeft, BorderLayout.EAST);
				getContentPane().add(jbnMiddle, BorderLayout.WEST);
				
				System.out.println("AFER COUNT: " + getContentPane().getComponentCount());
				getContentPane().validate();
				getContentPane().repaint();
				
				
			case(2): //KNOCK OR KEEP GOING?
				break;
		}
		
	}
	
	public void clearGUI()
	{
		//this method should be called to remove everything from the frame
		//every time we switch from main menu to game screen or what ever
		//we need to clear and remove everything from the last view
		getContentPane().removeAll();
	}

	public Player[] round(Scanner input, Player[] playersArray, gameState GAME_STATE, boolean debug, boolean extraHelp, UUID uniqueID, String endTime, int currentRound)
	{		
		/*
		 * WE NEED TO REINITIALIZE THE MAINDECK AND DISCARD PILE EVERYROUND!!!!!!!!!!!!
		 */
		System.out.println("DEBUG IS: " + debug);
		System.out.println("EXTRAHELP IS: " + extraHelp);
		
		//CREATE DECK
		Deck mainDeck = new Deck();
		mainDeck.shuffle();
		
		//CREATE DISCARD PILE
		Deck discard = new Deck();
		discard.clear();
		
		//UPDATE GAME STATE
		if(debug)
		{
			System.out.println("********************************");
			System.out.println("ROUND STARTED - GAME STATE: ");
			GAME_STATE.print();
			System.out.println("********************************");
		}
		
		//Clearing old player hands
		playersArray[0].myHand.clear();
		playersArray[1].myHand.clear();

		//Give Players cards
		for(int i = 0; i < 4; i++)
		{
			Card temp = mainDeck.getTopCard();
			playersArray[0].myHand.addCard(temp);
			
			Card temp2 = mainDeck.getTopCard();
			playersArray[1].myHand.addCard(temp2);
		}
		
		//Start discard Pile
		Card firstCard = mainDeck.getTopCard();
		while(firstCard.isSpecial())
		{
			mainDeck.addCard(firstCard);
			firstCard = mainDeck.getTopCard();
		}
		discard.addTopCard(firstCard);

		//Calc end time if not null
		String currentTime;
		if(endTime != null)
		{
	    	Calendar cal = Calendar.getInstance();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    	currentTime = sdf.format(cal.getTime());
		}
		
		if(debug || extraHelp)
		{
			System.out.println("********************************");
			System.out.println(playersArray[0].getName() + "'s hand: ");
			playersArray[0].myHand.showHand();
			System.out.println("********************************");
			System.out.println(playersArray[1].getName() + "'s hand: ");
			playersArray[1].myHand.showHand();
			System.out.println("********************************");
		}
		if(debug)
		{
			System.out.println("SHOWING THE REMAINING DECK");
			System.out.println("********************************");
			mainDeck.showDeck();
			System.out.println("********************************");
		}
		

				
		//PEEKING FIRST PLAYERS TWO INITIAL CARDS
		System.out.println("Before the game begins remember these cards!");
		System.out.println("Your Left Most Card: " + playersArray[0].myHand.getCard(0).toString());
		System.out.println("Your Right Most Card: " + playersArray[0].myHand.getCard(3).toString());
		
		
		//-------------GAME STARTING----------------		
		//----------------------------------------------------------------------GAME LOOP-----------
		boolean gameOver = false;
		boolean draw2SecondCard = false;
		boolean sendJSON = false;
		boolean gotDraw2 = false;
		int handIndex = 0;
		Card cardFromDeck;
		int playerWhoKnocked = -1;
		
		if(sendJSON)
		{
			//--------PREPARE THE JSON---------
			currentScore tempScore = new currentScore(GAME_STATE.numPlayers(), GAME_STATE, uniqueID, false, false, currentRound, mainDeck, discard);
			tempScore.addPlayer(playersArray[0]);
			tempScore.addPlayer(playersArray[1]);
			Transporter tempTransport = new Transporter(tempScore);			
		}
		
		while(!gameOver)
		{
			//CHECK IF DECK IS EMPTY
			if(mainDeck.size() == 0)
			{
				System.out.println("----------------------------RESHUFFLING DECK!");
				//Deck is empty, shuffle discard and create new deck
				Card topCard = discard.getTopCard();
				discard.shuffle();
				if(debug)
				{
					System.out.println("BEFORE| deck: " + mainDeck.size() + " discard: " + discard.size());
				}	
				int tempSize = discard.size();
				for(int i = 0; i < tempSize; i++)
				{
					System.out.println("adding");
					mainDeck.addCard(discard.getTopCard());
				}
				discard.addTopCard(topCard);
				if(debug)
				{
					System.out.println("After| deck: " + mainDeck.size() + " discard: " + discard.size());
				}
			}
			System.out.println("-------------------------------------");
			System.out.println("------------" + playersArray[GAME_STATE.getPlayer()].getName());
			System.out.println("-------------------------------------");
			//------TURN
			System.out.println("Discard Card: " + discard.getCard(0).toString());
			if(extraHelp)
			{
				System.out.println("Top Card In Deck: " + mainDeck.getCard(0).toString());			
			}
			
			int choice = 1;
			if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			{
				System.out.println("<----------------WARNING-------------------->");
				System.out.println("A PLAYER HAS KNOCKED, THIS IS YOUR LAST TURN!");
				System.out.println("<----------------WARNING-------------------->");
			}
			if(discard.getCard(0).isSpecial())
			{
				if(debug)
				{
					System.out.println("Cannot draw from discard pile because its a power card");
				}
				choice = 1;
			}
			else
			{
				/*
				//CONSOL VERSION
				do{
		     		System.out.println("----->Type 1, for deck pile");
					System.out.println("----->Type 2, for discard pile");
					while(!input.hasNextInt())
		  			{
						System.out.println("Thats not a number!");
						input.next();
		  			}
				   choice = input.nextInt();
				}while(choice < 1 || choice > 2);
				*/		
				/*while(clickDeck == false || clickDiscard == false)
				{
					System.out.println("WAITNING FOR DECK OR DISCARD CHOICE");
				}*/
				if(clickDeck == true)
				{
					choice = 1;
				}
				else if(clickDiscard == true)
				{
					choice = 2;
				}
				else
				{
					System.out.println("--------------------------------------------------------------------------------------NO CHOICE");
				}
				clickDeck = false;
				clickDiscard = false;
			}
			if(choice == 1)
			{
				//GET NEW CARD FROM DECK
				System.out.println("Picking From Deck");
				cardFromDeck = mainDeck.getTopCard();
		
				if(cardFromDeck.isSpecial())
				{
					discard.addTopCard(cardFromDeck);
					if(cardFromDeck.getSpecial() == "swap")
					{
						gotDraw2 = false;
						System.out.println("--------------------------------------------WE GOT A SWAP");
						//MY INDEX
						int myIndex;
						/*
						//CONSOL MOTHOD
						do{
				     		System.out.println("Type index, of YOUR card you want to swap");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   myIndex = input.nextInt();
						}while(myIndex < 0 || myIndex > 3);
						*/
						myIndex = 0;
						
						//OPPOENETS INDEX
						int othersIndex;
						/*
						//CONSOL METHOD
						do{
				     		System.out.println("Type index, of OPPONENTS card you want to swap");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   othersIndex = input.nextInt();
						}while(othersIndex < 0 || othersIndex > 3);
						*/
						othersIndex = 0;
						
						if(GAME_STATE.getPlayer() == 0)
						{
							Card fromOpponent = playersArray[1].myHand.getCard(othersIndex);
							Card newCard = playersArray[0].myHand.replaceCard(myIndex, fromOpponent);
							Card worthlessCard = playersArray[1].myHand.replaceCard(othersIndex, newCard);
						}
						else
						{
							Card fromOpponent = playersArray[0].myHand.getCard(othersIndex);
							Card newCard = playersArray[1].myHand.replaceCard(myIndex, fromOpponent);
							Card worthlessCard = playersArray[0].myHand.replaceCard(othersIndex, newCard);
						}
					}
					else if(cardFromDeck.getSpecial() == "peek")
					{
						gotDraw2 = false;
						System.out.println("--------------------------------------------WE GOT A PEEK");
						/*
						//CONSOL METHOD
						do{
				     		System.out.println("Type index, of the card you want to peek");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   handIndex = input.nextInt();
						}while(handIndex < 0 || handIndex > 3);
						*/
						handIndex = 0;
						System.out.println("Card " + handIndex + " is a " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(handIndex));
					}
					else if(cardFromDeck.getSpecial() == "draw2")
					{
						System.out.println("--------------------------------------------WE GOT A DRAW2");
						gotDraw2 = true;
					}
				}
				else
				{
					System.out.println("You got an " + cardFromDeck.toString());
					/*
					//CONSOL METHOD
					do{
			     		System.out.println("----->Type -1, for discard");
						System.out.println("----->Type index, for swapping");
						while(!input.hasNextInt())
			  			{
							System.out.println("Thats not a number!");
							input.next();
			  			}
					   handIndex = input.nextInt();
					}while(handIndex < -1 || handIndex > 3);
					*/
					handIndex = 0;
					
					if(handIndex == -1)
					{
						if(!draw2SecondCard && gotDraw2)
						{
							//we get to try again drawing
							draw2SecondCard = true;
						}
						else
						{
							//this was already our second attempt
							draw2SecondCard = false;
						}
						gotDraw2 = false;
						//User discards back to deck
						discard.addTopCard(cardFromDeck);
						if(debug)
						{
							System.out.println("Decided to discard " + cardFromDeck.toString());
						}
					}
					else
					{
						//user wants to swap card with hand
						gotDraw2 = false;
						draw2SecondCard = false;
						if(debug)
						{
							System.out.println("Swapped " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(handIndex).toString() + ", with " + cardFromDeck.toString());
						}					
						Card removedFromHand = playersArray[GAME_STATE.getPlayer()].myHand.replaceCard(handIndex, cardFromDeck);
						discard.addTopCard(removedFromHand);
					}

				}
			}
			else
			{
				//GET CARD FROM TOP OF DISCARD PILE
				gotDraw2 = false;
				draw2SecondCard = false;
				
				cardFromDeck = discard.getTopCard();
				/*
				//CONSOL METHOD
				do{
		     		System.out.println("----->Type index, of the card you want to swap");
					while(!input.hasNextInt())
		  			{
						System.out.println("Thats not a number!");
						input.next();
		  			}
				   handIndex = input.nextInt();
				}while(handIndex < 0 || handIndex > 3);
				*/
				handIndex = 0;
				
				Card removedFromHand = playersArray[GAME_STATE.getPlayer()].myHand.replaceCard(handIndex, cardFromDeck);
				discard.addTopCard(removedFromHand);
				if(debug)
				{
					System.out.println("Swapped " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(handIndex).toString() + ", with " + cardFromDeck.toString());
				}	
			}
			
			//DO YOU WANT TO KNOCK?
			if(GAME_STATE.getStatus() != KNOCKED_ROUND)
			{
				//Check if current Time is past endTime if so automatically knock
				Calendar cal = Calendar.getInstance();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    	currentTime = sdf.format(cal.getTime());
		    	
				if(endTime != null && currentTime == endTime)
				{
					//AUTOMATICALLY KNOCK BECAUSE THE TIME IS OVER
					//update game state to knocked round!
					GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode());
					
					//Set player who knocked
					playerWhoKnocked = GAME_STATE.getPlayer();
				}
				else
				{
					//normal turn knocking
					System.out.println("Do you want to knock?");
					/*
					//CONSOL METHOD
					do{
			     		System.out.println("----->Type 1, to keep playing");
						System.out.println("----->Type 2, to knock (AKA this was your last turn)");
						while(!input.hasNextInt())
			  			{
							System.out.println("Thats not a number!");
							input.next();
			  			}
					   choice = input.nextInt();
					}while(choice < 1 || choice > 2);
					*/
					choice = 2;
					if(choice != 1)
					{
						//update game state to knocked round!
						GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode());
						
						//Set player who knocked
						playerWhoKnocked = GAME_STATE.getPlayer();
					}
				}
			}		
			//--------------------------------------------------------------------------------------END TURN
		
			
			
			if(sendJSON)
			{
				//--------PREPARE THE JSON---------
				currentScore tempScore = new currentScore(GAME_STATE.numPlayers(), GAME_STATE, uniqueID, false, false, currentRound, mainDeck, discard);
				tempScore.addPlayer(playersArray[0]);
				tempScore.addPlayer(playersArray[1]);
				Transporter tempTransport = new Transporter(tempScore);			
			}
			
			
			
			if(debug || extraHelp)
			{
				System.out.println("********************************");
				System.out.println(playersArray[0].getName() + "'s hand: ");
				playersArray[0].myHand.showHand();
				System.out.println("********************************");
				System.out.println(playersArray[1].getName() + "'s hand: ");
				playersArray[1].myHand.showHand();
				System.out.println("********************************");
			}
			if(debug)
			{
				System.out.println("SHOWING THE REMAINING DECK");
				System.out.println("********************************");
				mainDeck.showDeck();
				System.out.println("********************************");
			}
			
			//UPDATE GAME STATE
			if(!gotDraw2 && !draw2SecondCard)
			{
				if(GAME_STATE.getPlayer() == GAME_STATE.numPlayers() - 1)
				{
					//RESET BACK TO FIRST PLAYER
					GAME_STATE.setPlayer(0);
					if(debug)
					{
						System.out.println("********************************");
						System.out.println("RESETTING BACK TO PLAYER 1");
						System.out.println("********************************");
					}
				}
				else
				{
					//NEXT PLAYERS TURN
					GAME_STATE.setPlayer(GAME_STATE.getPlayer() + 1);
				}
			}
			else
			{
				if(debug)
				{
					System.out.println(playersArray[GAME_STATE.getPlayer()].getName() + "'s got a draw 2, they go again.......");
				}
			}
			if(debug)
			{
				System.out.println("********************************");
				System.out.println("SHOWING GAME STATE AFTER TURN: ");
				GAME_STATE.print();
				System.out.println("********************************");
			}
			
			//CHECKING IF GAME IS OVER!
			if(GAME_STATE.getStatus() == KNOCKED_ROUND && GAME_STATE.getPlayer() == playerWhoKnocked)
			{
				//THE GAME IS OVER ALL PLAYERS HAVE HAD THEIR FINAL TURN!
				//update game state
				GAME_STATE.updateGameState(ROUND_OVER, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode());
				System.out.println("THE ROUND IS OVER");
				
				gameOver = true;
			}
		}		
		
		//SHOWING ROUND SCORES
		int winner = -1;
		int winningScore = 50;
		for(int i = 0; i < 2; i++)
		{
			//CHECK FOR REMAINING POWER CARDS
			for(int j = 0; j < 4; j++)
			{
				if(playersArray[i].myHand.getCard(j).isSpecial())
				{
					//CHECK IF DECK IS EMPTY
					if(mainDeck.size() == 0)
					{
						System.out.println("----------------------------RESHUFFLING DECK!");
						//Deck is empty, shuffle discard and create new deck
						Card topCard = discard.getTopCard();
						discard.shuffle();
						if(debug)
						{
							System.out.println("BEFORE| deck: " + mainDeck.size() + " discard: " + discard.size());
						}	
						int tempSize = discard.size();
						for(int k = 0; k < tempSize; k++)
						{
							mainDeck.addCard(discard.getTopCard());
						}
						discard.addTopCard(topCard);
						if(debug)
						{
							System.out.println("After| deck: " + mainDeck.size() + " discard: " + discard.size());
						}
					}

					//swap out card
					//check if its another power card!
					cardFromDeck = mainDeck.getTopCard();
					while(cardFromDeck.isSpecial())
					{
						discard.addTopCard(cardFromDeck);
						cardFromDeck = mainDeck.getTopCard();
					}
					
					Card oldPowerCard = playersArray[i].myHand.replaceCard(j, cardFromDeck);
					discard.addTopCard(oldPowerCard);
					if(debug)
					{
						System.out.println("Swapped '" + oldPowerCard.toString() + "' with '" + cardFromDeck + "'");
					}
				}
			}
			
			//RESET players score
			playersArray[i].setScore(0);
			
			System.out.println(playersArray[i].getName() + "'s final Score: " + playersArray[i].myHand.getScore());
			playersArray[i].myHand.showHand();
			//Update their game Score
			playersArray[i].setScore(playersArray[i].getScore() + playersArray[i].myHand.getScore());
			if(playersArray[i].myHand.getScore() < winningScore)
			{
				winner = i;
				winningScore = playersArray[i].myHand.getScore();
			}
			//UPDATE PLAYERS TOTAL SCORE
			playersArray[i].setTotalScore(playersArray[i].getTotalScore() + playersArray[i].myHand.getScore());
			
		}
		//Increase round win counter
		playersArray[winner].setRoundsWon(playersArray[winner].getRoundsWon() + 1);
		
		//show winner!
		System.out.println("THE WINNER WAS: " + playersArray[winner].getName() + " with " + playersArray[winner].myHand.getScore());
		
		if(sendJSON)
		{
			//--------PREPARE THE JSON---------
			currentScore tempScore = new currentScore(GAME_STATE.numPlayers(), GAME_STATE, uniqueID, false, true, currentRound, mainDeck, discard);
			tempScore.addPlayer(playersArray[0]);
			tempScore.addPlayer(playersArray[1]);
			Transporter tempTransport = new Transporter(tempScore);		
		}
		
		return playersArray;
	}
	/* Invoked immediately after the start() method, and also any time
	the applet needs to repaint itself in the browser. The paint()
	method is actually inherited from the java.awt.*/
	public void paint(Graphics g)
	{
		//HERE we can paint graphics to the screen which can be helpful
		super.paint(g);
		//g.drawString("WOW THIS WORKED!",25,25);
	}
	
	/*This method is intended for whatever initialization is needed for your applet.
	It is called after the param tags inside the applet tag have been processed.*/
	public void init()
	{		
		setUpGUI(0);
	}
	
	public void gameLoop(Object[] gameParameters)
	{
		//DEBUG/EXTRA HELP PARAMETERS
		boolean debug = false;
		boolean extraHelp = true;
		
		System.out.println("GAME LOOP!");
		//Setup Game
		//Object[] gameParameters = initialGameSetup();
		//rounds counter for while loops
		int counter = 0;
		//GAME MODES
		switch(((gameState)gameParameters[2]).getWinCon())
		{
			case(NUM_ROUNDS):
				if(debug)
				{
					System.out.println("------------------------------------------------NUM ROUNDS");
				}
				//rounds loop
				for(int i = 0; i < 5; i++)
				{
					int roundNum = i+1;
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("-----------------------------Starting Round " + roundNum + "----------------------------");
					System.out.println("-------------------------------------------------------------------------");
					gameParameters[1] = round((Scanner)gameParameters[0], (Player[])gameParameters[1], (gameState)gameParameters[2], debug, extraHelp, (UUID)gameParameters[3], null, roundNum);
					counter = i+1;
				}
				break;
			case(TIMED_PLAY):
				if(debug)
				{
					System.out.println("------------------------------------------------TIMED");
					
				}
				//Current Start and Stop time
				Calendar cal = Calendar.getInstance();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		    	Calendar start = Calendar.getInstance();
		    	String startTime = sdf.format(start.getTime());
		    	Calendar end = Calendar.getInstance();
		        end.add(Calendar.MINUTE,5);
		    	String endTime = sdf.format(end.getTime());
		    			    	
		    	
				//rounds loop			
				counter = 1;
				while(((Player[])gameParameters[1])[0].getTotalScore() < 100 || ((Player[])gameParameters[1])[1].getTotalScore() < 100)
				{
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("------------------Started At: " + startTime + " - Ends At: " + endTime + "----------------------------");
					System.out.println("-----------------------------Starting Round " + counter + "----------------------------");
					System.out.println(((Player[])gameParameters[1])[0].getName() + "'s Score: " + ((Player[])gameParameters[1])[0].getTotalScore() + " | " + ((Player[])gameParameters[1])[1].getName() + "'s Score: " + ((Player[])gameParameters[1])[1].getTotalScore());
					System.out.println("-------------------------------------------------------------------------");
					gameParameters[1] = round((Scanner)gameParameters[0], (Player[])gameParameters[1], (gameState)gameParameters[2], debug, extraHelp, (UUID)gameParameters[3], endTime, counter);
					counter++;
				}
				break;
			case(HIGH_SCORE):
				if(debug)
				{
					System.out.println("------------------------------------------------HIGH SCORE");
				}
				//rounds loop
				counter = 1;
				while(((Player[])gameParameters[1])[0].getTotalScore() < 100 || ((Player[])gameParameters[1])[1].getTotalScore() < 100)
				{
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("-----------------------------Starting Round " + counter + "----------------------------");
					System.out.println(((Player[])gameParameters[1])[0].getName() + "'s Score: " + ((Player[])gameParameters[1])[0].getTotalScore() + " | " + ((Player[])gameParameters[1])[1].getName() + "'s Score: " + ((Player[])gameParameters[1])[1].getTotalScore());
					System.out.println("-------------------------------------------------------------------------");
					gameParameters[1] = round((Scanner)gameParameters[0], (Player[])gameParameters[1], (gameState)gameParameters[2], debug, extraHelp, (UUID)gameParameters[3], null, counter);
					counter++;
				}
				break;
		}
		
		
		int winner = -1;
		int numWins = -1;
		//SHOW FINAL WINNER
		for(int i = 0; i < 2; i++)
		{
			if(((Player[])gameParameters[1])[i].getRoundsWon() > numWins)
			{
				numWins = ((Player[])gameParameters[1])[i].getRoundsWon();
				winner = i;
			}
		}
		
		System.out.println("------------------------------------------------");
		System.out.println("------------------------------------------------");
		System.out.println("FINAL WINNER: " + ((Player[])gameParameters[1])[winner].getName() + ", with " + ((Player[])gameParameters[1])[winner].getRoundsWon() + " wins and " + ((Player[])gameParameters[1])[winner].getTotalScore() + " total points");
		if(winner == 0)
		{
			System.out.println("FINAL LOSER: " + ((Player[])gameParameters[1])[1].getName() + ", with " + ((Player[])gameParameters[1])[1].getRoundsWon() + " wins and " + ((Player[])gameParameters[1])[1].getTotalScore() + " total points");
		}
		else
		{
			System.out.println("FINAL LOSER: " + ((Player[])gameParameters[1])[0].getName() + ", with " + ((Player[])gameParameters[1])[0].getRoundsWon() + " wins and " + ((Player[])gameParameters[1])[0].getTotalScore() + " total points");
		}
		System.out.println("-------------------------------------------------");
		System.out.println("------------------------------------------------");
		
		//--------PREPARE THE JSON---------
		currentScore tempScore = new currentScore(((gameState)gameParameters[2]).numPlayers(), (gameState)gameParameters[2], (UUID)gameParameters[3], true, true, counter, null, null);
		tempScore.addPlayer(((Player[])gameParameters[1])[0]);
		tempScore.addPlayer(((Player[])gameParameters[1])[1]);
		Transporter tempTransport = new Transporter(tempScore);
	}
	
	public Object[] initialGameSetup(int difficulty, int mode, String name)
	{
		//NEW INPUT SCANNER1
		Scanner input = new Scanner(System.in);
		
		//CREATE GAME STATE
		gameState GAME_STATE = new gameState(2);	
		
		//CREATE UNIQUE GAME ID
		UUID uniqueID = UUID.randomUUID();
		
		//PLAYERS ARRAY
		Player [] playersArray = new Player[2];
		
		//HUMAN PLAYER
		/*
		//CONSOL METHOD
	    System.out.println("---->WHAT IS YOUR NAME?");
	    String name = input.next();
	    */
		Hand humanHand = new Hand(0);
		Player human = new Player(true, 0, name, humanHand);
		playersArray[0] = human;
		
		
		String[] opponents = new String[5];
		opponents[0] = "Jimmy";
		opponents[1] = "Einstein";
		opponents[2] = "God";
		
		//Difficulty
		System.out.println("Choose Opponenets Difficulty:");
		/*
		int difficulty = 0;
		//CONSOL METHOD
		do{
	    	System.out.println("Type 1, for EASY");
			System.out.println("Type 2, for MEDIUM");
			System.out.println("Type 3, for HARD");
			while(!input.hasNextInt())
			{
				System.out.println("Thats not a number!");
				input.next();
			}
		   difficulty = input.nextInt();
		}while(difficulty < 1 || difficulty > 3);
	  	*/
		
		
		//COMPUTER PLAYER
		Hand computerHand = new Hand(1);
		Player computer = new Player(false, 1, opponents[difficulty -1], computerHand);
		playersArray[1] = computer;
		System.out.println("You are playing " + opponents[difficulty-1]);
		
		//Choose GAME MODE
		System.out.println("What Type of Game Do You Want To Play?");
		int winCon = 0;
		/*
		//CONSOL METHOD
		do{
	 		System.out.println("Type 1, for 5 Rounds");
			System.out.println("Type 2, for 2 Minute Max Round");
			System.out.println("Type 3, for last one to be under 100 points");
			while(!input.hasNextInt())
			{
				System.out.println("Thats not a number!");
				input.next();
			}
		   winCon = input.nextInt();
		}while(winCon < 1 || winCon > 3);
		*/
		winCon = 1;
		
		//UPDATE GAME STATE
		GAME_STATE.updateGameState(NORMAL_ROUND, winCon-1, 0, NORMAL_PLAY);
		//Build return array
		Object[] gameParameters = new Object[4];
		gameParameters[0] = input;
		gameParameters[1] = playersArray;
		gameParameters[2] = GAME_STATE;
		gameParameters[3] = uniqueID;
		
		return gameParameters;
	}
	
	/* This method is automatically called after the browser calls the init method.
	It is also called whenever the user returns to the page containing the applet
	after having gone off to other pages.*/
	public void start()
	{
		//gameLoop();
	}
	/*This method is automatically called when the user moves off the page on which the applet sits.
	It can, therefore, be called repeatedly in the same applet.*/
	public void stop()
	{
	
	}
	

	/*This method is only called when the browser shuts down normally.
	Because applets are meant to live on an HTML page, you should
	not normally leave resources behind after a user leaves the page
	that contains the applet.*/
	public void destroy()
	{
	
	}
	
	public void actionPerformed(ActionEvent e)
	{	
		//FOR THE GAME SETUP
		if("numRounds".equals(e.getActionCommand()))
		{
			setUpGUI(1);
			//Object[] gameParameters = initialGameSetup(NUM_ROUNDS, 0, (String)nameText.getText());
			//gameLoop(gameParameters);
		}
		if("timedMatch".equals(e.getActionCommand()))
		{
			//Object[] gameParameters = initialGameSetup(TIMED_PLAY, 0, (String)nameText.getText());
			setUpGUI(1);
			//gameLoop(gameParameters);
		}
		if("highScore".equals(e.getActionCommand()))
		{
			//Object[] gameParameters = initialGameSetup(HIGH_SCORE, 0, (String)nameText.getText());
			setUpGUI(1);
			//gameLoop(gameParameters);
		}
		
		
		//FOR THE GAME
		if("drawDeck".equals(e.getActionCommand()))
		{
			System.out.println("DECK");
		}
		if("drawDiscard".equals(e.getActionCommand()))
		{
			System.out.println("DISCARD");
		}
	}	
}
