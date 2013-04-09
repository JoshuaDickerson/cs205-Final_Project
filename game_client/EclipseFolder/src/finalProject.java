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
	public static final String BASEDIR = "/Final_2/images/";
	
	//GLOBAL OBJECTs
	gameState GAME_STATE;
	Player[] playersArray;
	Deck mainDeck;
	Deck discard;
	boolean debug;
	boolean extraHelp;
	boolean sendJSON;
	String endTime; 
	boolean gotDraw2;
	boolean draw2SecondCard;
	UUID uniqueID;
	boolean gameOver;
	
	
	//GUI
	//Within Instructions GUI
    javax.swing.JButton buttonPlay;
    javax.swing.JComboBox comboDifficulty;
    javax.swing.JComboBox comboGameStyle;
    javax.swing.JLabel labelCatPic;
    javax.swing.JLabel labelDifficulty;
    javax.swing.JLabel labelInstructions;
    javax.swing.JLabel labelStyle;
    javax.swing.JLabel labelUrl;
    javax.swing.JScrollPane scrollGameRules;
    javax.swing.JTextArea txtGameRules;
    //Within Main GUI
    javax.swing.JLabel Deck;
    javax.swing.JLabel Player2Card1;
    javax.swing.JLabel Player2Card2;
    javax.swing.JLabel Player2Card3;
    javax.swing.JLabel Player2Card4;
    javax.swing.JLabel PlayerCard1;
    javax.swing.JLabel PlayerCard2;
    javax.swing.JLabel PlayerCard3;
    javax.swing.JLabel PlayerCard4;
    javax.swing.JButton buttonDiscard;
    javax.swing.JButton buttonDraw;
    javax.swing.JButton buttonChangeCard;
    javax.swing.JButton buttonKnock;
    javax.swing.JComboBox comboSelectCard;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JLabel labelDiscard;
    javax.swing.JLabel labelOpponentCard1;
    javax.swing.JLabel labelOpponentCard2;
    javax.swing.JLabel labelOpponentCard3;
    javax.swing.JLabel labelOpponentCard4;
    javax.swing.JLabel labelOpponentHand;
    javax.swing.JLabel labelPlayerCard1;
    javax.swing.JLabel labelPlayerCard2;
    javax.swing.JLabel labelPlayerCard3;
    javax.swing.JLabel labelPlayerCard4;
    javax.swing.JLabel labelRataTat;
    javax.swing.JLabel labelYourHand1;
    javax.swing.JTextArea textGameLog;
    //Within Draw 2 Tutorial Dialog
    javax.swing.JLabel labelDraw2;
    javax.swing.JLabel labelDraw2Picture1;
    javax.swing.JLabel labelDraw2Picture2;
    javax.swing.JButton okButton;
    javax.swing.JScrollPane scrollDraw2;
    javax.swing.JScrollPane scrollPowerCard2;
    javax.swing.JTextArea txtDraw2;
    javax.swing.JTextArea txtPowerCards;
    //Within Peek Tutorial Dialog
    javax.swing.JLabel labelPeek;
    javax.swing.JLabel labelPeekPic1;
    javax.swing.JLabel labelPeekPic2;
    javax.swing.JButton okButtonPeek;
    javax.swing.JScrollPane scrollPeek;
    javax.swing.JScrollPane scrollPowerCards;
    javax.swing.JTextArea txtPeek;
    javax.swing.JTextArea txtPowerCards1;
    //Within Swap/Peek Dialog
    javax.swing.JLabel OpponentCard1;
    javax.swing.JLabel OpponentCard2;
    javax.swing.JLabel OpponentCard3;
    javax.swing.JLabel OpponentCard4;
    javax.swing.JLabel Player1Card1;
    javax.swing.JLabel Player1Card2;
    javax.swing.JLabel Player1Card3;
    javax.swing.JLabel Player1Card4;
    javax.swing.JButton buttonPeek;
    javax.swing.JButton buttonSwap;
    javax.swing.JButton buttonSwapDiscard;
    javax.swing.JComboBox comboOpponentCard;
    javax.swing.JComboBox comboPlayerCard;
    javax.swing.JLabel labelCardShow;
    javax.swing.JLabel labelChooseCard;
    javax.swing.JLabel labelChooseOppCard;
    javax.swing.JLabel labelPlayer1;
    javax.swing.JLabel labelPlayer1Card1;
    javax.swing.JLabel labelPlayer1Card2;
    javax.swing.JLabel labelPlayer1Card3;
    javax.swing.JLabel labelPlayer1Card4;
    javax.swing.JLabel labelPlayer2;
    javax.swing.JLabel labelPlayer2Card1;
    javax.swing.JLabel labelPlayer2Card2;
    javax.swing.JLabel labelPlayer2Card3;
    javax.swing.JLabel labelPlayer2Card4;
    //Within Swap Tutorial Dialog
    javax.swing.JLabel labelPictureSwap1;
    javax.swing.JLabel labelPictureSwap2;
    javax.swing.JLabel labelSwapTutorial;
    javax.swing.JButton okButtonSwap;
    javax.swing.JScrollPane scrollPowerCard1;
    javax.swing.JScrollPane scrollSwap;
    javax.swing.JTextArea txtPowerCard1;
    javax.swing.JTextArea txtSwap;
    //Within Turn Change Dialog
    javax.swing.JLabel labelCardPicture1;
    javax.swing.JLabel labelCardPicture2;
    javax.swing.JLabel labelDontLook;
    javax.swing.JLabel labelPlayerTurn;
    javax.swing.JButton okButtonTurn;

	
	public void setUpGUI(int guiMode)
	{
		clearGUI();
		switch(guiMode)
		{
			case(0): //Instructions GUI

			labelInstructions = new javax.swing.JLabel();
	        buttonPlay = new javax.swing.JButton();
	        labelStyle = new javax.swing.JLabel();
	        labelDifficulty = new javax.swing.JLabel();
	        labelUrl = new javax.swing.JLabel();
	        scrollGameRules = new javax.swing.JScrollPane();
	        txtGameRules = new javax.swing.JTextArea();
	        labelCatPic = new javax.swing.JLabel();
	        comboGameStyle = new javax.swing.JComboBox();
	        comboDifficulty = new javax.swing.JComboBox();

	        setPreferredSize(new java.awt.Dimension(640, 480));
	        
	        labelInstructions.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        labelInstructions.setText("Instructions:");

	        buttonPlay.setText("Play");
	        buttonPlay.addActionListener(this);
	        buttonPlay.setActionCommand("Play");
	        
	        labelStyle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelStyle.setText("Style of Game:");

	        labelDifficulty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelDifficulty.setText("Difficulty: ");

	        labelUrl.setText("For More Game Rules See: http://www.gamewright.com/gamewright/pdfs/Rules/Rat-a-TatCat-RULES.pdf");

	        txtGameRules.setEditable(false);
	        txtGameRules.setColumns(20);
	        txtGameRules.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
	        txtGameRules.setLineWrap(true);
	        txtGameRules.setRows(5);
	        txtGameRules.setText("The goal is to have the lowest score at the end of the game.\n\nChoose one player to be the dealer, and a scorekeeper. The scorekeeper will record each playerâ€™s score at the end of each round of play.\n\nShuffle the deck. The player to the left of the dealer cuts the cards. \nThe dealer then deals four cards, one at a time and face down, to each player.\nThe remaining cards are placed face down, in the middle of the table, as the\ndraw pile. The top card of the draw pile is turned over to start the discard\npile. If that card is a Power card, it is placed back in the deck and another\ncard is turned over.\n\nWithout looking at his cards, each player places his or her four cards face down in a line on the table in front of them.\n\nDuring the game, players will always have their four cards face down on the\ntable. To begin the game, players peek at their two outer cards once, then\nturn them face down again. Each player now knows the point values of two of\nhis four cards and needs to remember them during the game.\nIf either of the outer cards are Power cards, the player keeps them, but they\ndo not have their powers (described below). Power cards only have their\npowers when they are drawn from the top of the draw pile.  \nThe player to the left of the dealer has the first turn, and play continues in a\nclockwise direction.\n\nFor each turn, a player may:\n\n1. Draw the top card from the discard pile. This card MUST be used to\nreplace one of her cards. The card replaced is then discarded, face up, to the\ndiscard pile.\n\n2. Draw the top card from the draw pile. A player may use it to: \n\t1. Replace one of her cards\n\t2. Peek, Swap, or Draw 2 if it is a Power card (see below)\n\t3. Discard it face up to the discard pile\n\nA playerâ€™s choice is based on remembering the values of his four face down\ncards. Keep track of what you have so you wonâ€™t accidentally replace your\nlow point cards with high point cards.\nDuring the game, when the draw pile is used up, shuffle the discard pile and\nturn it over for a new draw pile.\n\nPower cards only have their powers when you draw them from the draw pile. If a Power card is dealt to you at the beginning of the game, it cannot be used. Because Power cards have no point value, if one of them is among your cards at the end of the game, you must replace it with a card drawn from the draw pile. If a Power card is discarded, it may not be used again by any player.\n\nThere are three kinds of Power cards:\n1. Peek\n\tWhen you draw a Peek card, show it and then peek at any\n\tone of your cards. Now you will know what you have, or you\n\tcan refresh your memory if you have forgotten what you have.\n\tYour turn is over and you discard the Peek card.\n2. Swap\n\tWhen you draw a Swap card, show the Swap card and \n\tdiscard it. You may now switch any one of your cards with\n\tany card of another player (swapping is optional). Neither player \t\tcan look at either of the cards being swapped. After the swap \t\tyour turn is over.\n3. Draw 2\n\tWhen you draw a Draw 2 card, show the card and then you\n\tmay take two more turns. First you draw the next card from the\n\tdraw pile. You must decide whether to use this card and forfeit\n\tthe second turn OR discard this card and draw a second card.\n\tThis second card may be used or discarded. Your turn is then\n\tover. If either of the cards drawn are another Draw 2 card, the\n\tDraw 2 sequence starts again.\n\nEnding the Round\n\nWhen a player thinks he has the lowest score and can win the round, he or she may end the round by knocking on the table and saying â€œrat-a-tat catâ€� at\nthe end of their turn. Once they knock, every other player has one more turn.\nEach player then turns over their cards. Players replace all Power cards by\ndrawing from the draw pile. If another Power card is drawn, the player\ndraws again.\n\nScoring\n\nPlayers add the point values of their four cards. This is each playerâ€™s score\nfor the round. The scorekeeper records each playerâ€™s score. Remember that\nplayers are trying to get as low a score as possible.\n\nNext Rounds\n\nAll cards are collected and passed to the player to the left of the dealer who\nreshuffles and deals for the next round.\n\nEnding the Game\n\nThe player with the lowest total score at the end of the game is the winner.\n\nA game may be played three ways:\n1. Play for a certain number of rounds.\n2. Play for a specific length of time.\n3. Play to stay in the game and not reach 100 points. When a player \nreaches 100 points, he is out of the game. The last player in the game is \nthe winner. Players may also choose to play to 200, or any other number \nof points.");
	        txtGameRules.setWrapStyleWord(true);
	        scrollGameRules.setViewportView(txtGameRules);

	        labelCatPic.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/images/rat_a_tat.jpg")));
	        comboGameStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rounds", "Time", "Points" }));
	        comboGameStyle.setToolTipText("");

	        comboDifficulty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Medium", "Hard" }));
	        

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(280, 280, 280)
	                .addComponent(labelInstructions))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(10, 10, 10)
	                .addComponent(labelCatPic)
	                .addGap(5, 5, 5)
	                .addComponent(scrollGameRules, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(80, 80, 80)
	                .addComponent(labelUrl))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(140, 140, 140)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelStyle)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(30, 30, 30)
	                        .addComponent(labelDifficulty)))
	                .addGap(17, 17, 17)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(comboGameStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(comboDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(30, 30, 30)
	                .addComponent(buttonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(labelInstructions)
	                .addGap(11, 11, 11)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(40, 40, 40)
	                        .addComponent(labelCatPic))
	                    .addComponent(scrollGameRules, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(10, 10, 10)
	                .addComponent(labelUrl)
	                .addGap(6, 6, 6)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(comboGameStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(comboDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(10, 10, 10)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(labelStyle)
	                                .addGap(25, 25, 25)
	                                .addComponent(labelDifficulty))
	                            .addComponent(buttonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
	        );
				
				
				

				break;
			case(1): //Main GUI

		    buttonDraw = new javax.swing.JButton();
	        Deck = new javax.swing.JLabel();
	        PlayerCard1 = new javax.swing.JLabel();
	        PlayerCard2 = new javax.swing.JLabel();
	        PlayerCard4 = new javax.swing.JLabel();
	        PlayerCard3 = new javax.swing.JLabel();
	        Player2Card1 = new javax.swing.JLabel();
	        Player2Card2 = new javax.swing.JLabel();
	        Player2Card3 = new javax.swing.JLabel();
	        labelDiscard = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        textGameLog = new javax.swing.JTextArea();
	        buttonDiscard = new javax.swing.JButton();
	        labelPlayerCard4 = new javax.swing.JLabel();
	        labelPlayerCard1 = new javax.swing.JLabel();
	        labelPlayerCard2 = new javax.swing.JLabel();
	        labelPlayerCard3 = new javax.swing.JLabel();
	        labelOpponentCard1 = new javax.swing.JLabel();
	        labelOpponentCard2 = new javax.swing.JLabel();
	        labelOpponentCard3 = new javax.swing.JLabel();
	        labelOpponentCard4 = new javax.swing.JLabel();
	        Player2Card4 = new javax.swing.JLabel();
	        labelOpponentHand = new javax.swing.JLabel();
	        labelYourHand1 = new javax.swing.JLabel();
	        labelRataTat = new javax.swing.JLabel();
	        comboSelectCard = new javax.swing.JComboBox();
	        buttonChangeCard = new javax.swing.JButton();
	        buttonKnock = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(640, 480));

	        buttonDraw.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13.png"))))); // NOI18N
	        buttonDraw.addActionListener(this);
	        buttonDraw.setActionCommand("Deck");
	        
	        Deck.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13.png"))))); // NOI18N

	        PlayerCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        PlayerCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        PlayerCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        PlayerCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player2Card1.setIcon(new javax.swing.ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png")))); // NOI18N

	        Player2Card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player2Card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        labelDiscard.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/4.png"))))); // NOI18N
			
	        textGameLog.setColumns(20);
	        textGameLog.setRows(5);
	        jScrollPane1.setViewportView(textGameLog);

	        buttonDiscard.setText("Discard");
	        buttonDiscard.addActionListener(this);
	        buttonDiscard.setActionCommand("Discard");
	        

	        labelPlayerCard4.setText("Card 4");

	        labelPlayerCard1.setText("Card 1");

	        labelPlayerCard2.setText("Card 2");

	        labelPlayerCard3.setText("Card 3");

	        labelOpponentCard1.setText("Card 1");

	        labelOpponentCard2.setText("Card 2");

	        labelOpponentCard3.setText("Card 3");

	        labelOpponentCard4.setText("Card 4");

	        //Player2Card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))); // NOI18N

	        labelOpponentHand.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelOpponentHand.setText("Opponent's Hand:");

	        labelYourHand1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelYourHand1.setText("Your Hand:");

	        labelRataTat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
	        labelRataTat.setText("Rat a Tat Cat");

	        comboSelectCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Card 1", "Card 2", "Card 3", "Card 4" }));
	        comboSelectCard.setToolTipText("");
	        comboSelectCard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //Combo stuff, so however you have it now
	            }
	        });

	        buttonChangeCard.setText("Swap Card");
	        buttonChangeCard.addActionListener(this);
	        buttonChangeCard.setActionCommand("ChangeCard");
	        
	        buttonKnock.setText("Knock");
	        buttonKnock.addActionListener(this);
	        buttonKnock.setActionCommand("Knock");
	        
	        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(mainLayout);
	        mainLayout.setHorizontalGroup(
	            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(Deck)
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(labelOpponentHand)
	                .addGap(9, 9, 9)
	                .addComponent(Player2Card1)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card2)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card3)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card4)
	                .addGap(15, 15, 15)
	                .addComponent(labelRataTat))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(160, 160, 160)
	                .addComponent(labelOpponentCard1)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard2)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard3)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard4))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(34, 34, 34)
	                .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(23, 23, 23)
	                .addComponent(labelDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(28, 28, 28)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(labelYourHand1)
	                    .addComponent(buttonKnock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(22, 22, 22)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(20, 20, 20)
	                        .addComponent(labelPlayerCard1))
	                    .addComponent(PlayerCard1))
	                .addGap(15, 15, 15)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(20, 20, 20)
	                        .addComponent(labelPlayerCard2))
	                    .addComponent(PlayerCard2))
	                .addGap(15, 15, 15)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(20, 20, 20)
	                        .addComponent(labelPlayerCard3))
	                    .addComponent(PlayerCard3))
	                .addGap(15, 15, 15)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(20, 20, 20)
	                        .addComponent(labelPlayerCard4))
	                    .addComponent(PlayerCard4))
	                .addGap(25, 25, 25)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(comboSelectCard, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(buttonDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(buttonChangeCard, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
	        );
	        mainLayout.setVerticalGroup(
	            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(mainLayout.createSequentialGroup()
	                .addComponent(Deck)
	                .addGap(10, 10, 10)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(40, 40, 40)
	                        .addComponent(labelOpponentHand))
	                    .addComponent(Player2Card1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(30, 30, 30)
	                        .addComponent(labelRataTat)))
	                .addGap(10, 10, 10)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelOpponentCard1)
	                    .addComponent(labelOpponentCard2)
	                    .addComponent(labelOpponentCard3)
	                    .addComponent(labelOpponentCard4))
	                .addGap(6, 6, 6)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(10, 10, 10)
	                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(labelDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(6, 6, 6)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(5, 5, 5)
	                        .addComponent(buttonKnock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(19, 19, 19)
	                        .addComponent(labelYourHand1))
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addComponent(labelPlayerCard1)
	                        .addGap(10, 10, 10)
	                        .addComponent(PlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addComponent(labelPlayerCard2)
	                        .addGap(10, 10, 10)
	                        .addComponent(PlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addComponent(labelPlayerCard3)
	                        .addGap(10, 10, 10)
	                        .addComponent(PlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(4, 4, 4)
	                        .addComponent(labelPlayerCard4)
	                        .addGap(6, 6, 6)
	                        .addComponent(PlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(4, 4, 4)
	                        .addComponent(comboSelectCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(buttonDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(6, 6, 6)
	                        .addComponent(buttonChangeCard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
	        );
	        
	        
/*	            
	        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(mainLayout);
	        mainLayout.setHorizontalGroup(
	            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(Deck)
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(labelOpponentHand)
	                .addGap(9, 9, 9)
	                .addComponent(Player2Card1)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card2)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card3)
	                .addGap(15, 15, 15)
	                .addComponent(Player2Card4)
	                .addGap(15, 15, 15)
	                .addComponent(labelRataTat))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(160, 160, 160)
	                .addComponent(labelOpponentCard1)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard2)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard3)
	                .addGap(58, 58, 58)
	                .addComponent(labelOpponentCard4))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(34, 34, 34)
	                .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(23, 23, 23)
	                .addComponent(labelDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(160, 160, 160)
	                .addComponent(labelPlayerCard1)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayerCard2)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayerCard3)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayerCard4))
	            .addGroup(mainLayout.createSequentialGroup()
	                .addGap(50, 50, 50)
	                .addComponent(labelYourHand1)
	                .addGap(22, 22, 22)
	                .addComponent(PlayerCard1)
	                .addGap(15, 15, 15)
	                .addComponent(PlayerCard2)
	                .addGap(15, 15, 15)
	                .addComponent(PlayerCard3)
	                .addGap(15, 15, 15)
	                .addComponent(PlayerCard4)
	                .addGap(25, 25, 25)
	             .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addComponent(comboSelectCard, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(buttonDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(buttonChangeCard, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
	        );
	        mainLayout.setVerticalGroup(
	            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(mainLayout.createSequentialGroup()
	                .addComponent(Deck)
	                .addGap(10, 10, 10)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(40, 40, 40)
	                        .addComponent(labelOpponentHand))
	                    .addComponent(Player2Card1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(Player2Card4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(30, 30, 30)
	                        .addComponent(labelRataTat)))
	                .addGap(10, 10, 10)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelOpponentCard1)
	                    .addComponent(labelOpponentCard2)
	                    .addComponent(labelOpponentCard3)
	                    .addComponent(labelOpponentCard4))
	                .addGap(6, 6, 6)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(10, 10, 10)
	                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(buttonDraw, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(labelDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGap(10, 10, 10)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelPlayerCard1)
	                    .addComponent(labelPlayerCard2)
	                    .addComponent(labelPlayerCard3)
	                    .addComponent(labelPlayerCard4))
	                .addGap(6, 6, 6)
	                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(40, 40, 40)
	                        .addComponent(labelYourHand1))
	                    .addComponent(PlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(PlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(PlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(PlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(mainLayout.createSequentialGroup()
	                        .addGap(20, 20, 20)
	                        .addComponent(buttonDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
	        );
			*/	       
	        
				
				break;
			case(2): //Draw 2 Tutorial GUI

		    scrollPowerCard2 = new javax.swing.JScrollPane();
	        txtPowerCards = new javax.swing.JTextArea();
	        labelDraw2 = new javax.swing.JLabel();
	        labelDraw2Picture1 = new javax.swing.JLabel();
	        labelDraw2Picture2 = new javax.swing.JLabel();
	        okButton = new javax.swing.JButton();
	        scrollDraw2 = new javax.swing.JScrollPane();
	        txtDraw2 = new javax.swing.JTextArea();
	        
	        setPreferredSize(new java.awt.Dimension(640, 480));

	        txtPowerCards.setEditable(false);
	        txtPowerCards.setColumns(20);
	        txtPowerCards.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtPowerCards.setLineWrap(true);
	        txtPowerCards.setRows(5);
	        txtPowerCards.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
	        txtPowerCards.setWrapStyleWord(true);
	        scrollPowerCard2.setViewportView(txtPowerCards);

	        labelDraw2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        labelDraw2.setText("You've Drawn a Draw 2 Card!");

	        labelDraw2Picture1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/10.png"))))); // NOI18N

	        labelDraw2Picture2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/10.png"))))); // NOI18N

	        okButton.setText("OK");
	        okButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                okButton.setActionCommand("Ok");
	            }
	        });

	        txtDraw2.setEditable(false);
	        txtDraw2.setColumns(20);
	        txtDraw2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtDraw2.setLineWrap(true);
	        txtDraw2.setRows(5);
	        txtDraw2.setText("Take a card from the draw pile, and either swap it with one of your cards or discard it and draw a second card to be swapped or thrown out.");
	        txtDraw2.setWrapStyleWord(true);
	        scrollDraw2.setViewportView(txtDraw2);

	        javax.swing.GroupLayout Draw2Layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(Draw2Layout);
	        Draw2Layout.setHorizontalGroup(
	            Draw2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(Draw2Layout.createSequentialGroup()
	                .addGap(130, 130, 130)
	                .addComponent(labelDraw2))
	            .addGroup(Draw2Layout.createSequentialGroup()
	                .addGap(40, 40, 40)
	                .addComponent(labelDraw2Picture2)
	                .addGap(15, 15, 15)
	                .addGroup(Draw2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(scrollDraw2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(scrollPowerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(20, 20, 20)
	                .addComponent(labelDraw2Picture1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGroup(Draw2Layout.createSequentialGroup()
	                .addGap(270, 270, 270)
	                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
	        Draw2Layout.setVerticalGroup(
	            Draw2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(Draw2Layout.createSequentialGroup()
	                .addGap(70, 70, 70)
	                .addComponent(labelDraw2)
	                .addGap(51, 51, 51)
	                .addGroup(Draw2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelDraw2Picture2)
	                    .addGroup(Draw2Layout.createSequentialGroup()
	                        .addComponent(scrollDraw2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(19, 19, 19)
	                        .addComponent(scrollPowerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(labelDraw2Picture1))
	                .addGap(39, 39, 39)
	                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );
				
				
				
				break;
			case(3): //Peek Tutorial GUI
				
		    okButtonPeek = new javax.swing.JButton();
	        scrollPeek = new javax.swing.JScrollPane();
	        txtPeek = new javax.swing.JTextArea();
	        scrollPowerCards = new javax.swing.JScrollPane();
	        txtPowerCards1 = new javax.swing.JTextArea();
	        labelPeek = new javax.swing.JLabel();
	        labelPeekPic1 = new javax.swing.JLabel();
	        labelPeekPic2 = new javax.swing.JLabel();

	        setPreferredSize(new java.awt.Dimension(640, 480));
	        
	        okButtonPeek.setText("OK");
	        okButtonPeek.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                okButtonPeek.setActionCommand("Ok");
	            }
	        });

	        txtPeek.setEditable(false);
	        txtPeek.setColumns(20);
	        txtPeek.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtPeek.setLineWrap(true);
	        txtPeek.setRows(5);
	        txtPeek.setText("Take a card from the draw pile, and either swap it with one of your cards or discard it and draw a second card to be swapped or thrown out.");
	        txtPeek.setWrapStyleWord(true);
	        scrollPeek.setViewportView(txtPeek);

	        txtPowerCards1.setEditable(false);
	        txtPowerCards1.setColumns(20);
	        txtPowerCards1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtPowerCards1.setLineWrap(true);
	        txtPowerCards1.setRows(5);
	        txtPowerCards1.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
	        txtPowerCards1.setWrapStyleWord(true);
	        scrollPowerCards.setViewportView(txtPowerCards1);

	        labelPeek.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        labelPeek.setText("You've Drawn a Peek Card!");

	        labelPeekPic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/11.png"))))); // NOI18N

	        labelPeekPic2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/11.png"))))); // NOI18N

	        javax.swing.GroupLayout peekLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(peekLayout);
	        peekLayout.setHorizontalGroup(
	            peekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(peekLayout.createSequentialGroup()
	                .addGap(45, 45, 45)
	                .addGroup(peekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(peekLayout.createSequentialGroup()
	                        .addComponent(labelPeekPic2)
	                        .addGap(25, 25, 25)
	                        .addGroup(peekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(scrollPeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(scrollPowerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGap(24, 24, 24)
	                        .addComponent(labelPeekPic1))
	                    .addGroup(peekLayout.createSequentialGroup()
	                        .addGap(233, 233, 233)
	                        .addComponent(okButtonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(peekLayout.createSequentialGroup()
	                        .addGap(114, 114, 114)
	                        .addComponent(labelPeek)))
	                .addContainerGap(50, Short.MAX_VALUE))
	        );
	        peekLayout.setVerticalGroup(
	            peekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(peekLayout.createSequentialGroup()
	                .addGap(70, 70, 70)
	                .addComponent(labelPeek)
	                .addGap(44, 44, 44)
	                .addGroup(peekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelPeekPic2)
	                    .addGroup(peekLayout.createSequentialGroup()
	                        .addComponent(scrollPeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(9, 9, 9)
	                        .addComponent(scrollPowerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(labelPeekPic1))
	                .addGap(41, 41, 41)
	                .addComponent(okButtonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(76, Short.MAX_VALUE))
	        );
				
				
		    break;
			case(4): //Swap GUI
				
		    OpponentCard1 = new javax.swing.JLabel();
	        OpponentCard2 = new javax.swing.JLabel();
	        OpponentCard4 = new javax.swing.JLabel();
	        OpponentCard3 = new javax.swing.JLabel();
	        Player1Card2 = new javax.swing.JLabel();
	        Player1Card1 = new javax.swing.JLabel();
	        Player1Card4 = new javax.swing.JLabel();
	        Player1Card3 = new javax.swing.JLabel();
	        comboPlayerCard = new javax.swing.JComboBox();
	        comboOpponentCard = new javax.swing.JComboBox();
	        buttonSwapDiscard = new javax.swing.JButton();
	        buttonPeek = new javax.swing.JButton();
	        labelPlayer2Card1 = new javax.swing.JLabel();
	        labelPlayer2Card2 = new javax.swing.JLabel();
	        labelPlayer2Card3 = new javax.swing.JLabel();
	        labelPlayer2Card4 = new javax.swing.JLabel();
	        labelPlayer1Card1 = new javax.swing.JLabel();
	        labelPlayer1Card2 = new javax.swing.JLabel();
	        labelPlayer1Card3 = new javax.swing.JLabel();
	        labelPlayer1Card4 = new javax.swing.JLabel();
	        labelPlayer1 = new javax.swing.JLabel();
	        labelPlayer2 = new javax.swing.JLabel();
	        labelChooseCard = new javax.swing.JLabel();
	        labelChooseOppCard = new javax.swing.JLabel();
	        buttonSwap = new javax.swing.JButton();
	        labelCardShow = new javax.swing.JLabel();

	        setPreferredSize(new java.awt.Dimension(640, 480));

	        OpponentCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        OpponentCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        OpponentCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        OpponentCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player1Card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player1Card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player1Card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        Player1Card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13tiny.png"))))); // NOI18N

	        comboPlayerCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Card 1", "Card 2", "Card 3", "Card 4" }));
	        comboPlayerCard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	int cardPlayer = comboPlayerCard.getSelectedIndex();
	            	if(cardPlayer == 0){
	            		comboPlayerCard.setActionCommand("Card 1");
	            	}
	            	else if(cardPlayer == 1){
	            		comboPlayerCard.setActionCommand("Card 2");
	            	}
	            	else if(cardPlayer == 2){
	            		comboPlayerCard.setActionCommand("Card 3");
	            	}
	            	else if(cardPlayer == 3){
	            		comboPlayerCard.setActionCommand("Card 4");
	            	}
	            }
	        });
	        
	        comboOpponentCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Card 1", "Card 2", "Card 3", "Card 4" }));
	        comboOpponentCard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	int cardOpponent = comboPlayerCard.getSelectedIndex();
	            	if(cardOpponent == 0){
	            		comboOpponentCard.setActionCommand("Card 1");
	            	}
	            	else if(cardOpponent == 1){
	            		comboOpponentCard.setActionCommand("Card 2");
	            	}
	            	else if(cardOpponent == 2){
	            		comboOpponentCard.setActionCommand("Card 3");
	            	}
	            	else if(cardOpponent == 3){
	            		comboOpponentCard.setActionCommand("Card 4");
	            	}
	            }
	        });

	        buttonSwapDiscard.setText("Discard");
	        buttonSwapDiscard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                buttonSwapDiscard.setActionCommand("SwapDiscard");
	            }
	        });

	        buttonPeek.setText("Peek");
	        buttonPeek.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                buttonPeek.setActionCommand("Peek");
	            }
	        });

	        labelPlayer2Card1.setText("Card 1");

	        labelPlayer2Card2.setText("Card 2");

	        labelPlayer2Card3.setText("Card 3");

	        labelPlayer2Card4.setText("Card 4");

	        labelPlayer1Card1.setText("Card 1");

	        labelPlayer1Card2.setText("Card 2");

	        labelPlayer1Card3.setText("Card 3");

	        labelPlayer1Card4.setText("Card 4");

	        labelPlayer1.setText("Player 1");

	        labelPlayer2.setText("Player 2");

	        labelChooseCard.setText("Choose Your Card:");

	        labelChooseOppCard.setText("Choose Opponent's Card:");

	        buttonSwap.setText("Swap");
	        buttonSwap.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                buttonSwap.setActionCommand("Swap");
	            }
	        });

	        labelCardShow.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13.png"))))); // NOI18N

	        javax.swing.GroupLayout swapPeekLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(swapPeekLayout);
	        swapPeekLayout.setHorizontalGroup(
	            swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(70, 70, 70)
	                .addComponent(OpponentCard1)
	                .addGap(25, 25, 25)
	                .addComponent(OpponentCard2)
	                .addGap(15, 15, 15)
	                .addComponent(OpponentCard3)
	                .addGap(15, 15, 15)
	                .addComponent(OpponentCard4))
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(100, 100, 100)
	                .addComponent(labelPlayer2Card1)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer2Card2)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer2Card3)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer2Card4))
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(110, 110, 110)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addGap(120, 120, 120)
	                        .addComponent(labelPlayer2))
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addGap(110, 110, 110)
	                        .addComponent(labelChooseCard))
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addGap(80, 80, 80)
	                        .addComponent(labelChooseOppCard))
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addComponent(buttonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(27, 27, 27)
	                        .addComponent(buttonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(16, 16, 16)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(comboPlayerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(comboOpponentCard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(buttonSwapDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(37, 37, 37)
	                .addComponent(labelCardShow))
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(230, 230, 230)
	                .addComponent(labelPlayer1))
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(100, 100, 100)
	                .addComponent(labelPlayer1Card1)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer1Card2)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer1Card3)
	                .addGap(58, 58, 58)
	                .addComponent(labelPlayer1Card4))
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(70, 70, 70)
	                .addComponent(Player1Card1)
	                .addGap(25, 25, 25)
	                .addComponent(Player1Card2)
	                .addGap(15, 15, 15)
	                .addComponent(Player1Card3)
	                .addGap(15, 15, 15)
	                .addComponent(Player1Card4))
	        );
	        swapPeekLayout.setVerticalGroup(
	            swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(swapPeekLayout.createSequentialGroup()
	                .addGap(10, 10, 10)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(OpponentCard1)
	                    .addComponent(OpponentCard2)
	                    .addComponent(OpponentCard3)
	                    .addComponent(OpponentCard4))
	                .addGap(13, 13, 13)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelPlayer2Card1)
	                    .addComponent(labelPlayer2Card2)
	                    .addComponent(labelPlayer2Card3)
	                    .addComponent(labelPlayer2Card4))
	                .addGap(6, 6, 6)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addComponent(labelPlayer2)
	                        .addGap(26, 26, 26)
	                        .addComponent(labelChooseCard)
	                        .addGap(16, 16, 16)
	                        .addComponent(labelChooseOppCard)
	                        .addGap(36, 36, 36)
	                        .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(buttonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(buttonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(swapPeekLayout.createSequentialGroup()
	                        .addGap(30, 30, 30)
	                        .addComponent(comboPlayerCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(comboOpponentCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(28, 28, 28)
	                        .addComponent(buttonSwapDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(labelCardShow))
	                .addGap(5, 5, 5)
	                .addComponent(labelPlayer1)
	                .addGap(6, 6, 6)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelPlayer1Card1)
	                    .addComponent(labelPlayer1Card2)
	                    .addComponent(labelPlayer1Card3)
	                    .addComponent(labelPlayer1Card4))
	                .addGap(6, 6, 6)
	                .addGroup(swapPeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(Player1Card1)
	                    .addComponent(Player1Card2)
	                    .addComponent(Player1Card3)
	                    .addComponent(Player1Card4)))
	        );
				
				
			break;
			case(5): //Swap Tutorial GUI
				
		    okButtonSwap = new javax.swing.JButton();
	        scrollSwap = new javax.swing.JScrollPane();
	        txtSwap = new javax.swing.JTextArea();
	        labelSwapTutorial = new javax.swing.JLabel();
	        scrollPowerCard1 = new javax.swing.JScrollPane();
	        txtPowerCard1 = new javax.swing.JTextArea();
	        labelPictureSwap1 = new javax.swing.JLabel();
	        labelPictureSwap2 = new javax.swing.JLabel();

	        setPreferredSize(new java.awt.Dimension(640, 480));

	        okButtonSwap.setText("OK");
	        okButtonSwap.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                okButtonSwap.setActionCommand("Ok");
	            }
	        });

	        txtSwap.setEditable(false);
	        txtSwap.setColumns(20);
	        txtSwap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtSwap.setLineWrap(true);
	        txtSwap.setRows(5);
	        txtSwap.setText("You may trade one of your cards with an opponent's. Neither player may look at the card  values being swapped.");
	        txtSwap.setWrapStyleWord(true);
	        scrollSwap.setViewportView(txtSwap);

	        labelSwapTutorial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        labelSwapTutorial.setText("You've Drawn a Swap Card!");

	        txtPowerCard1.setEditable(false);
	        txtPowerCard1.setColumns(20);
	        txtPowerCard1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        txtPowerCard1.setLineWrap(true);
	        txtPowerCard1.setRows(5);
	        txtPowerCard1.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
	        txtPowerCard1.setWrapStyleWord(true);
	        scrollPowerCard1.setViewportView(txtPowerCard1);

	        labelPictureSwap1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/12.png"))))); // NOI18N

	        labelPictureSwap2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/12.png"))))); // NOI18N

	        javax.swing.GroupLayout swapLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(swapLayout);
	        swapLayout.setHorizontalGroup(
	            swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(swapLayout.createSequentialGroup()
	                .addGroup(swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(swapLayout.createSequentialGroup()
	                        .addGap(28, 28, 28)
	                        .addGroup(swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(swapLayout.createSequentialGroup()
	                                .addComponent(labelPictureSwap2)
	                                .addGap(15, 15, 15)
	                                .addGroup(swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(scrollSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(scrollPowerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addGap(18, 18, 18)
	                                .addComponent(labelPictureSwap1))
	                            .addGroup(swapLayout.createSequentialGroup()
	                                .addGap(250, 250, 250)
	                                .addComponent(okButtonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                    .addGroup(swapLayout.createSequentialGroup()
	                        .addGap(149, 149, 149)
	                        .addComponent(labelSwapTutorial)))
	                .addContainerGap(37, Short.MAX_VALUE))
	        );
	        swapLayout.setVerticalGroup(
	            swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(swapLayout.createSequentialGroup()
	                .addGap(69, 69, 69)
	                .addComponent(labelSwapTutorial)
	                .addGap(38, 38, 38)
	                .addGroup(swapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelPictureSwap2)
	                    .addGroup(swapLayout.createSequentialGroup()
	                        .addComponent(scrollSwap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(9, 9, 9)
	                        .addComponent(scrollPowerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(labelPictureSwap1))
	                .addGap(15, 15, 15)
	                .addComponent(okButtonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(111, Short.MAX_VALUE))
	        );
					
				
		    break;
			case(6): //Change Turn GUI
				
		    okButtonTurn = new javax.swing.JButton();
	        labelPlayerTurn = new javax.swing.JLabel();
	        labelDontLook = new javax.swing.JLabel();
	        labelCardPicture1 = new javax.swing.JLabel();
	        labelCardPicture2 = new javax.swing.JLabel();

	        setPreferredSize(new java.awt.Dimension(640, 480));

	        okButtonTurn.setText("OK");
	        okButtonTurn.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                okButtonTurn.setActionCommand("Ok");
	            }
	        });

	        labelPlayerTurn.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
	        labelPlayerTurn.setText("It's Player 2's Turn!");

	        labelDontLook.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
	        labelDontLook.setText("Other Players, Don't Look!");

	        labelCardPicture1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13.png"))))); // NOI18N

	        labelCardPicture2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"/13.png"))))); // NOI18N

	        javax.swing.GroupLayout turnLayout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(turnLayout);
	        turnLayout.setHorizontalGroup(
	            turnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(turnLayout.createSequentialGroup()
	                .addGap(130, 130, 130)
	                .addComponent(labelPlayerTurn))
	            .addGroup(turnLayout.createSequentialGroup()
	                .addGap(180, 180, 180)
	                .addComponent(labelDontLook))
	            .addGroup(turnLayout.createSequentialGroup()
	                .addGap(70, 70, 70)
	                .addComponent(labelCardPicture2)
	                .addGap(55, 55, 55)
	                .addComponent(okButtonTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(64, 64, 64)
	                .addComponent(labelCardPicture1))
	        );
	        turnLayout.setVerticalGroup(
	            turnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(turnLayout.createSequentialGroup()
	                .addGap(78, 78, 78)
	                .addComponent(labelPlayerTurn)
	                .addGap(6, 6, 6)
	                .addComponent(labelDontLook)
	                .addGap(11, 11, 11)
	                .addGroup(turnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(labelCardPicture2)
	                    .addGroup(turnLayout.createSequentialGroup()
	                        .addGap(50, 50, 50)
	                        .addComponent(okButtonTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(labelCardPicture1))
	                .addContainerGap(137, Short.MAX_VALUE))
	        );
				
		    
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
	
	public void swap()
	{
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
	
	public void drawFromDeck()
	{
		Card cardFromDeck;
		
		//GET NEW CARD FROM DECK
		System.out.println("Picking From Deck");
		cardFromDeck = mainDeck.getTopCard();

		if(cardFromDeck.isSpecial())
		{
			discard.addTopCard(cardFromDeck);
			if(cardFromDeck.getSpecial() == "swap")
			{
				gotDraw2 = false;
				swap();
			}
			else if(cardFromDeck.getSpecial() == "peek")
			{
				gotDraw2 = false;
				peek();
			}
			else if(cardFromDeck.getSpecial() == "draw2")
			{
				gotDraw2 = true;
				draw2();
			}
		}
		else
		{
			replaceCardInHand();
		}
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
	
	public void updateGUICards()
	{
		/*
		 * THIS METHOD NEEDS TO BE FIXED SO THE CORRECT CARD OBJECTS ARE LINKED
		 */
		
		/*
		//HUMANS HAND
		card1.setIcon(playersArray[0].getHand().getCard(0).getPictureName());
		card2.setIcon(playersArray[0].getHand().getCard(1).getPictureName());
		card3.setIcon(playersArray[0].getHand().getCard(2).getPictureName());
		card4.setIcon(playersArray[0].getHand().getCard(3).getPictureName());
		
		//COMPUTERS HAND
		card1.setIcon(playersArray[1].getHand().getCard(0).getPictureName());
		card2.setIcon(playersArray[1].getHand().getCard(1).getPictureName());
		card3.setIcon(playersArray[1].getHand().getCard(2).getPictureName());
		card4.setIcon(playersArray[1].getHand().getCard(3).getPictureName());
		*/
	}
	
	public void replaceCardInHand()
	{
		System.out.println("REAPLCING CARD FROM HADN WITH DECK");
		/*
		//System.out.println("You got an " + cardFromDeck.toString());
		
		if(1 == -1)
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
		*/
	}
	public void peek()
	{
		gotDraw2 = false;
		System.out.println("--------------------------------------------WE GOT A PEEK");
		
		
		//System.out.println("Card " + handIndex + " is a " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(handIndex));
	}
	
	public void draw2()
	{
		System.out.println("--------------------------------------------WE GOT A DRAW2");
		gotDraw2 = true;
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
		Player human = new Player(true, 0, "REPLACE WITH REAL NAME" , humanHand);
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
		GAME_STATE.updateGameState(NORMAL_ROUND, comboGameStyle.getSelectedIndex(), 0, NORMAL_PLAY, GAME_STATE.getRoundNum());
		
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
		//mainDeck.shuffle();
		
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
		System.out.println("dkfjsklfjakldsfjklsd");
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
	
	/**
	*This method is intended for whatever initialization is needed for your applet.
	*It is called after the param tags inside the applet tag have been processed.
	**/
	public void init()
	{		
		//DEBUG PARAMETERS
		debug = true;
		extraHelp = true;
		sendJSON = true;
		
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
		if("Play".equals(e.getActionCommand()))
		{
			if(debug)
			{
				System.out.println("Ive CLICKED PLAY WITH GAME MODE: " + comboGameStyle.getSelectedIndex() + " | and difficulty : " + comboDifficulty.getSelectedIndex());
				
			}
	        //INIT GAME
			initGame();
			
			initRound();
			
			//SETUP GUI
			setUpGUI(1);
			
		}
		if("Deck".equals(e.getActionCommand()))
		{
			System.out.println("Deck");
			drawFromDeck();
			//setUpGUI(2);
		}
		if("Discard".equals(e.getActionCommand()))
		{
			System.out.println("Discard");
			//setUpGUI(2);
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