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
	
	//GLOBAL OBJECTs
	gameState GAME_STATE;
	Player[] playersArray;
	Deck mainDeck;
	Deck discard;
	boolean debug;
	boolean extraHelp;
	boolean sendJSON;
	String endTime;
	boolean draw2FirstCard;
	boolean draw2SecondCard;
	UUID uniqueID;
	boolean gameOver;
	
	//GUI
	JButton jbnLeft;
	JButton jbnRight;
	JButton jbnMiddle;
	JLabel title;
	TextField nameText;
	
	public void setUpGUI(int guiMode)
	{
		clearGUI();
		switch(guiMode)
		{
			case(0): //INITIAL SCREEN
				//TITLE
				title = new JLabel("WHATS YOUR NAME?");
				title.setHorizontalAlignment(JLabel.CENTER);
				getContentPane().add(title, BorderLayout.NORTH);
				
				//MIDDLE
				nameText = new TextField(10);
				getContentPane().add(nameText, BorderLayout.SOUTH);
				
				//Num Rounds
				jbnLeft = new JButton("5 Rounds");
				jbnLeft.setEnabled(true);
				jbnLeft.addActionListener(this);
				jbnLeft.setActionCommand("numRounds");
				
				//Timed Match
				jbnMiddle = new JButton("Timed Match");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("timedMatch");
				jbnMiddle.addActionListener(this);
				
				//High Score
				jbnRight = new JButton("High Score");
				jbnRight.setEnabled(true);
				jbnRight.setActionCommand("highScore");
				jbnRight.addActionListener(this);
				
				
				
				getContentPane().add(jbnLeft, BorderLayout.EAST);
				getContentPane().add(jbnMiddle, BorderLayout.CENTER);
				getContentPane().add(jbnRight, BorderLayout.WEST);
				
				getContentPane().validate();
				getContentPane().repaint();
				break;
			case(1): //DECK OR DISCARD
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
				
				getContentPane().validate();
				getContentPane().repaint();
				
				break;
			case(2): //KNOCK OR KEEP GOING?
				//TITLE
				title = new JLabel("Knock or Keep Playing?");
				title.setHorizontalAlignment(JLabel.CENTER);
				getContentPane().add(title, BorderLayout.NORTH);
				
				//LEFT
				jbnLeft = new JButton("knock");
				jbnLeft.setEnabled(true);
				jbnLeft.addActionListener(this);
				jbnLeft.setActionCommand("knock");
				
				//MIDDLE
				jbnMiddle = new JButton("Keep Playing");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("keepPlaying");
				jbnMiddle.addActionListener(this);
				
				
				getContentPane().add(jbnLeft, BorderLayout.EAST);
				getContentPane().add(jbnMiddle, BorderLayout.WEST);
				
				getContentPane().validate();
				getContentPane().repaint();
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
	
	public void drawFromDeck()
	{
		/*
		Card cardFromDeck;
		
		//GET NEW CARD FROM DECK
		System.out.println("Picking From Deck");
		cardFromDeck = mainDeck.getTopCard();

		if(cardFromDeck.isSpecial())
		{
			discard.addTopCard(cardFromDeck);
			if(cardFromDeck.getSpecial() == "swap")
			{
				draw2FirstCard = false;
				System.out.println("--------------------------------------------WE GOT A SWAP");
				//MY INDEX
				int myIndex;
				
				myIndex = 0;
				
				//OPPOENETS INDEX
				int othersIndex;
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
				draw2FirstCard = false;
				System.out.println("--------------------------------------------WE GOT A PEEK");
				
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
		*/
	}
	
	public void drawDiscard()
	{
		/*
		gotDraw2 = false;
		draw2SecondCard = false;
		
		cardFromDeck = discard.getTopCard();
		handIndex = 0;
		
		Card removedFromHand = playersArray[GAME_STATE.getPlayer()].myHand.replaceCard(handIndex, cardFromDeck);
		discard.addTopCard(removedFromHand);
		if(debug)
		{
			System.out.println("Swapped " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(handIndex).toString() + ", with " + cardFromDeck.toString());
		}
		*/
	}
	
	public void swap()
	{
		
	}
	
	public void peek()
	{
		
	}
	
	public void draw2()
	{
		
	}
	
	public void initGame()
	{		
		//CREATE GAME STATE
		GAME_STATE = new gameState(2);	
		
		
		//CREATE UNIQUE GAME ID
		uniqueID = UUID.randomUUID();
		
		//PLAYERS ARRAY
		playersArray = new Player[2];
		
		//HUMAN PLAYER
		Hand humanHand = new Hand(0);
		Player human = new Player(true, 0, nameText.getText() , humanHand);
		playersArray[0] = human;
		
		//COMPUTER OPPONENTS
		String[] opponents = new String[5];
		opponents[0] = "Jimmy";
		opponents[1] = "Einstein";
		opponents[2] = "God";
		
		//Difficulty
		int difficulty = 1;
		
		//COMPUTER PLAYER
		Hand computerHand = new Hand(1);
		Player computer = new Player(false, 1, opponents[difficulty -1], computerHand);
		playersArray[1] = computer;
		System.out.println("You are playing " + opponents[difficulty-1]);
		
		//UPDATE GAME STATE
		//public void updateGameState(int _status, int _winCon, int _player, int _mode)
		GAME_STATE.updateGameState(NORMAL_ROUND, GAME_STATE.getWinCon(), 0, NORMAL_PLAY, GAME_STATE.getRoundNum());
		
		if(debug)
		{
			GAME_STATE.print();
		}
		
		//UPDATE SERVER
		if(sendJSON)
		{
			sendJSON();
		}
	}	
	
	public void initRound()
	{
		System.out.println("DEBUG IS: " + debug);
		System.out.println("EXTRAHELP IS: " + extraHelp);
		
		//CREATE DECK
		mainDeck = new Deck();
		mainDeck.shuffle();
		
		//CREATE DISCARD PILE
		discard = new Deck();
		discard.clear();
		
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
		
		//SET FIRST PLAYER TO GO FIRST!
		GAME_STATE.setPlayer(0);
		
		if(debug)
		{
			GAME_STATE.print();
		}
	
		//UPDATE SERVER
		if(sendJSON)
		{
			sendJSON();
		}
	}
	
	public void rebuildEmptyDeck()
	{
		//CHECK IF DECK IS EMPTY
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
	
	public void showEndRound()
	{
		/*
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
		*/
	}
	
	public boolean checkIfRoundTimedOut()
	{
		return false;
		/*
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
				GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode(), GAME_STATE.getRoundNum());
				
				//Set player who knocked
				playerWhoKnocked = GAME_STATE.getPlayer();
			}
		}
		
		//update game state to knocked round!
		GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode(), GAME_STATE.getRoundNum());
		
		//Set player who knocked
		playerWhoKnocked = GAME_STATE.getPlayer();
		*/
	}

	public void sendJSON()
	{
		//--------PREPARE THE JSON---------
		currentScore tempScore = new currentScore(GAME_STATE.numPlayers(), GAME_STATE, uniqueID, false, false, GAME_STATE.getRoundNum(), mainDeck, discard);
		tempScore.addPlayer(playersArray[0]);
		tempScore.addPlayer(playersArray[1]);
		Transporter tempTransport = new Transporter(tempScore);	
	}
	
	public boolean checkGameOver()
	{
		return false;
		/*
		//CHECKING IF GAME IS OVER!
		if(GAME_STATE.getStatus() == KNOCKED_ROUND && GAME_STATE.getPlayer() == GAME_STATE.getPlayerWhoKnocked())
		{
			//THE GAME IS OVER ALL PLAYERS HAVE HAD THEIR FINAL TURN!
			//update game state
			GAME_STATE.updateGameState(ROUND_OVER, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode(), GAME_STATE.getRoundNum());
			System.out.println("THE ROUND IS OVER");
			
			gameOver = true;
		}
		*/
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
		//DEBUG PARAMETERS
		debug = true;
		extraHelp = true;
		sendJSON = false;
		
		setSize(500, 300);
		setUpGUI(0);
	}
	
	public void gameLoop(Object[] gameParameters)
	{
		/*
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
		*/
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
			//INIT GAME
			initGame();
			
			initRound();
			
			//SETUP GUI
			setUpGUI(1);
			
		}
		if("timedMatch".equals(e.getActionCommand()))
		{
			
		}
		if("highScore".equals(e.getActionCommand()))
		{
			
		}
		
		
		//FOR THE GAME
		if("drawDeck".equals(e.getActionCommand()))
		{
			System.out.println("DECK");
			setUpGUI(2);
		}
		if("drawDiscard".equals(e.getActionCommand()))
		{
			System.out.println("DISCARD");
			setUpGUI(2);
		}
		
		//KNOCK OR STAY
		if("knock".equals(e.getActionCommand()))
		{
			System.out.println("knock");
			setUpGUI(0);
		}
		if("keepPlaying".equals(e.getActionCommand()))
		{
			System.out.println("keep playing");
			setUpGUI(0);
		}
	}	
}
