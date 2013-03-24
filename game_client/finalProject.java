import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.applet.*;
import java.util.Scanner;


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
	
	public void setUpGUI(int guiMode)
	{
		clearGUI();
		
		System.out.println("AFTER CLEAR");
		//setLayout(new BorderLayout());
			System.out.println("CREATED NEW BORDERLAYOUT");
				System.out.println(guiMode);
		switch(guiMode)
		{
			case(0): //MAIN_MENU GUI
			System.out.println("0");
				
				//TITLE
				title = new JLabel("Menu");
				getContentPane().add(title, BorderLayout.NORTH);
				
				//LEFT
				jbnLeft = new JButton("Menu");
				jbnLeft.setEnabled(false);
				jbnLeft.addActionListener(this);
				
				//MIDDLE
				jbnMiddle = new JButton("Game Board");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("enableMiddle");
				jbnMiddle.addActionListener(this);
				
				//RIGHT
				jbnRight = new JButton("Credits");
				jbnRight.setEnabled(true);
				jbnRight.setActionCommand("enableRight");
				jbnRight.addActionListener(this);
				
				//ADD THEM
				add(jbnLeft, BorderLayout.WEST);
				add(jbnRight, BorderLayout.EAST);
				add(jbnMiddle, BorderLayout.CENTER);
				break;
			
			case(1): //GAME_SCREEN
			System.out.println("1");
			
				 //TITLE
				title = new JLabel("Game Board");
				getContentPane().add(title, BorderLayout.NORTH);
				//LEFT
				jbnLeft = new JButton("Disable centre button");
				jbnLeft.setEnabled(true);
				jbnLeft.setActionCommand("enableLeft");
				jbnLeft.addActionListener(this);
				
				//MIDDLE
				jbnMiddle = new JButton("Game Board");
				jbnMiddle.setEnabled(false);
				jbnMiddle.addActionListener(this);
				
				//RIGHT
				jbnRight = new JButton("Credits");
				jbnRight.setEnabled(true);
				jbnRight.setActionCommand("enableRight");
				jbnRight.addActionListener(this);
				
				//ADD THEM
				add(jbnLeft, BorderLayout.WEST);
				add(jbnRight, BorderLayout.EAST);
				add(jbnMiddle, BorderLayout.CENTER);
				break;
			
			case(2): //CREDITS!
			System.out.println("2");
			
				 //TITLE
				title = new JLabel("Credits");
				getContentPane().add(title, BorderLayout.NORTH);
				
				//LEFT
				jbnLeft = new JButton("Disable centre button");
				jbnLeft.setEnabled(true);
				jbnLeft.setActionCommand("enableLeft");
				jbnLeft.addActionListener(this);
				
				//MIDDLE
				jbnMiddle = new JButton("Game Board");
				jbnMiddle.setEnabled(true);
				jbnMiddle.setActionCommand("enableMiddle");
				jbnMiddle.addActionListener(this);
				
				//RIGHT
				jbnRight = new JButton("Credits");
				jbnRight.setEnabled(false);
				jbnRight.addActionListener(this);
				
				//ADD THEM
				add(jbnLeft, BorderLayout.WEST);
				add(jbnRight, BorderLayout.EAST);
				add(jbnMiddle, BorderLayout.CENTER);
				break;
		}
		
	}
	
	public void clearGUI()
	{
		//this method should be called to remove everything from the frame
		//every time we switch from main menu to game screen or what ever
		//we need to clear and remove everything from the last view
		
		//getContentPane().removeAll();
	}

	public void gameLoop()
	{
		//DEBUG
		boolean debug = true;
		boolean extraHelp = true;
		
		//NEW INPUT SCANNER
		Scanner input = new Scanner(System.in);
		
		//CREATE GAME STATE
		gameState GAME_STATE = new gameState(2);	
		if(debug)
		{
			System.out.println("SHOWING INITIAL GAME STATE: ");
			GAME_STATE.print();	
		}
		
		//CREATE UNIQUE GAME ID
		UUID uniqueID = UUID.randomUUID();
		
		//CREATE DECK
		Deck mainDeck = new Deck();
		mainDeck.shuffle();
		
		//CREATE DISCARD PILE
		Deck discard = new Deck();
		discard.clear();
		
		//PLAYERS ARRAY
		Player [] playersArray = new Player[2];
		
		//HUMAN PLAYER
	   System.out.println("---->WHAT IS YOUR NAME?");
	   String name = input.next();
		Hand humanHand = new Hand(0);
		Player human = new Player(true, 0, name, humanHand);
		playersArray[0] = human;
		
		String[] opponents = new String[5];
		opponents[0] = "Jimmy";
		opponents[1] = "Einstein";
		opponents[2] = "God";
		
		//Difficulty
		System.out.println("Choose Opponenets Difficulty:");
		int difficulty = 0;
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
      				
		//COMPUTER PLAYER
		Hand computerHand = new Hand(1);
		Player computer = new Player(false, 1, opponents[difficulty -1], computerHand);
		playersArray[1] = computer;
		System.out.println("You are playing " + opponents[difficulty-1]);
		
		//Choose GAME MODE
		System.out.println("What Type of Game Do You Want To Play?");
		int gameMode = 0;
		do{
     		System.out.println("Type 1, for 5 Minute Max Round");
			System.out.println("Type 2, for 10 Turns Max Round");
			System.out.println("Type 3, for High Score Round");
			while(!input.hasNextInt())
  			{
				System.out.println("Thats not a number!");
				input.next();
  			}
		   gameMode = input.nextInt();
		}while(gameMode < 1 || gameMode > 3);
		
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
		GAME_STATE.updateGameState(NORMAL_ROUND, gameMode, 0, NORMAL_PLAY);
		if(debug)
		{
			System.out.println("********************************");
			System.out.println("SHOWING UPDATED GAME STATE: ");
			GAME_STATE.print();
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
		boolean gotDraw2 = false;
		int handIndex = 0;
		Card cardFromDeck;
		int playerWhoKnocked = -1;
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
			System.out.println("-------------------------------------");
			System.out.println("------------" + playersArray[GAME_STATE.getPlayer()].getName() + "-------------");
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			//------TURN
			System.out.println("Discard Card: " + discard.getCard(0).toString());
			if(extraHelp)
			{
				System.out.println("Top Card In Deck: " + mainDeck.getCard(0).toString());			
			}
			
			int choice = 1;
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
						do{
				     		System.out.println("Type index, of YOUR card you want to swap");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   myIndex = input.nextInt();
						}while(myIndex < 0 || myIndex > 3);
						
						//OPPOENETS INDEX
						int othersIndex;
						do{
				     		System.out.println("Type index, of OPPONENTS card you want to swap");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   othersIndex = input.nextInt();
						}while(othersIndex < 0 || othersIndex > 3);
						
						
						if(GAME_STATE.getPlayer() == 0)
						{
							Card fromOpponent = playersArray[1].myHand.getCard(othersIndex);
							Card newCard = playersArray[0].myHand.replaceCard(myIndex, fromOpponent);
							Card whocares = playersArray[1].myHand.replaceCard(othersIndex, newCard);
						}
						else
						{
							Card fromOpponent = playersArray[0].myHand.getCard(othersIndex);
							Card newCard = playersArray[1].myHand.replaceCard(myIndex, fromOpponent);
							Card whocares = playersArray[0].myHand.replaceCard(othersIndex, newCard);
						}
					}
					else if(cardFromDeck.getSpecial() == "peek")
					{
						gotDraw2 = false;
						System.out.println("--------------------------------------------WE GOT A PEEK");
						do{
				     		System.out.println("Type index, of the card you want to peek");
							while(!input.hasNextInt())
				  			{
								System.out.println("Thats not a number!");
								input.next();
				  			}
						   handIndex = input.nextInt();
						}while(handIndex < 0 || handIndex > 3);
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
				do{
		     		System.out.println("----->Type index, of the card you want to swap");
					while(!input.hasNextInt())
		  			{
						System.out.println("Thats not a number!");
						input.next();
		  			}
				   handIndex = input.nextInt();
				}while(handIndex < 0 || handIndex > 3);
			
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
				System.out.println("Do you want to knock?");
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
				if(choice != 1)
				{
					//update game state to knocked round!
					GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode());
					
					//Set player who knocked
					playerWhoKnocked = GAME_STATE.getPlayer();
				}
			}		
			//--------------------------------------------------------------------------------------END TURN
		
			
			/*
			//--------PREPARE THE JSON---------
			currentScore tempScore = new currentScore(GAME_STATE.numPlayers(), GAME_STATE, uniqueID);
			tempScore.addPlayer(playersArray[0]);
			tempScore.addPlayer(playersArray[1]);
			Transporter tempTransport = new Transporter(tempScore);		
			*/
			
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
					System.out.println("********************************");
					System.out.println("RESETTING BACK TO PLAYER 1");
					System.out.println("********************************");
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
		
		//SHOWING FINAL SCORES
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
							System.out.println("adding");
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
					
					System.out.println("FOUND SPECIAL");
					Card oldPowerCard = playersArray[i].myHand.replaceCard(j, cardFromDeck);
					discard.addTopCard(oldPowerCard);
					if(debug)
					{
						System.out.println("Swapped '" + oldPowerCard.toString() + "' with '" + cardFromDeck + "'");
					}
				}
				else
				{
					System.out.println("NO SPECIAL");
				}
			}
			System.out.println(playersArray[i].getName() + "'s final Score: " + playersArray[i].myHand.getScore());
			playersArray[i].myHand.showHand();
			if(playersArray[i].myHand.getScore() < winningScore)
			{
				winner = i;
				winningScore = playersArray[i].myHand.getScore();
			}
		}
		
		System.out.println("THE WINNER WAS: " + playersArray[winner].getName() + " with " + playersArray[winner].myHand.getScore());
		
		/*
		//TESTING GAME STATE
		System.out.println("--------TESTING GAME STATE OBJECT------------");
		gameState newGameState = new gameState();
		System.out.println("Initital Game State");
		newGameState.print();
		System.out.println();
		newGameState.updateGameState(NULL, NULL, PLAYER_THREE, NULL);
		System.out.println("Changed Game State");
		newGameState.print();
		
		//TESTING PLAYER SCORE
		System.out.println("--------TESTING PLAYER SCORE OBJECT------------");
		playerScore player1 = new playerScore(19, "Anders");
		playerScore player2 = new playerScore(183, "Bob");
		
		//TESTING CURRENTSCORE
		System.out.println("--------TESTING CURRENT SCORES OBJECT------------");
		currentScore totalScore = new currentScore(2, newGameState);
		totalScore.addPlayerScore(player1);
		totalScore.addPlayerScore(player2);
		System.out.println("TOTAL PLAYERS IN SCORE OBJECT SIZE: " + totalScore.getPlayerCount());
		System.out.println(totalScore.getPlayerScoreAtIndex(0).getName() + " has score " + totalScore.getPlayerScoreAtIndex(0).getScore());
		System.out.println(totalScore.getPlayerScoreAtIndex(1).getName() + " has score " + totalScore.getPlayerScoreAtIndex(1).getScore());
		gameState tempGameState = totalScore.getGameState();
		*/
	}
	/* Invoked immediately after the start() method, and also any time
	the applet needs to repaint itself in the browser. The paint()
	method is actually inherited from the java.awt.*/
	public void paint(Graphics g)
	{
		//HERE we can paint graphics to the screen which can be helpful
		super.paint(g);
		g.drawString("WOW THIS WORKED!",25,25);
	}
	
	/*This method is intended for whatever initialization is needed for your applet.
	It is called after the param tags inside the applet tag have been processed.*/
	public void init()
	{
		//setUpGUI(MAIN_MENU);
		gameLoop();
	}
	
	/* This method is automatically called after the browser calls the init method.
	It is also called whenever the user returns to the page containing the applet
	after having gone off to other pages.*/
	public void start()
	{
	
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
	
	public void actionPerformed(ActionEvent e) {
		
		if("enableLeft".equals(e.getActionCommand()))
		{
			System.out.println("left");
			setUpGUI(MAIN_MENU);
		}
		else if("enableMiddle".equals(e.getActionCommand()))
		{
			System.out.println("middle");
			setUpGUI(GAME_SCREEN);
		}
		else if("enableRight".equals(e.getActionCommand()))
		{
			System.out.println("right");
			setUpGUI(CREDITS_SCREEN);
		}
	}	
}
