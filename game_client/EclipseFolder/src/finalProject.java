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

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	public static final String BASEDIR = "/images/";
	//GLOBAL OBJECTs
	gameState GAME_STATE;
	Player[] playersArray;
	Deck mainDeck;
	Deck discard;
	boolean debug;
	boolean extraHelp;
	boolean sendJSON;
	LocalTime endTime;
	LocalTime startTime;
	boolean gotDraw2;
	boolean draw2SecondCard;
	UUID uniqueID;
	boolean gameOver;
	boolean chooseDeck;
	int cardToPeek;
	Random generator = new Random(System.currentTimeMillis());
	//GUI
	
	private javax.swing.GroupLayout layout;
	  private javax.swing.JButton draw2Instruct_jbutton_ok;
	  private javax.swing.JLabel draw2Instruct_jlabel_graphic1;
	  private javax.swing.JLabel draw2Instruct_jlabel_graphic2;
	  private javax.swing.JLabel draw2Instruct_jlabel_label1;
	  private javax.swing.JTextArea draw2Instruct_jtextarea_instruct1;
	  private javax.swing.JTextArea draw2Instruct_jtextarea_instruct2;
	  private javax.swing.JScrollPane scrollDraw2;
	  private javax.swing.JScrollPane scrollPowerCard2;

	private javax.swing.JButton instructions_jbutton_play;
	private javax.swing.JComboBox instructions_jcombobox_difficulty;
	private javax.swing.JComboBox instructions_jcombobox_rounds;
	private javax.swing.JLabel instructions_jlabel_label1;
	private javax.swing.JLabel instructions_jlabel_label2;
	private javax.swing.JLabel instructions_jlabel_label3;
	private javax.swing.JLabel instructions_jlabel_label4;
	private javax.swing.JLabel instructions_jlabel_label6;
	private javax.swing.JLabel instructions_jlabel_label6_link;
	private javax.swing.JTextArea instructions_jtextarea_ta1;
	private javax.swing.JTextField instructions_jtextfield_p1NameInput;
	private javax.swing.JScrollPane scrollGameRules;


	 private javax.swing.JLabel Deck;
	 private javax.swing.JScrollPane jScrollPane1;
	 private javax.swing.JLabel labelPlayerCard2mainGUI_jlabel_myHandLabel3;
	 private javax.swing.JLabel labelPlayerCard2mainGUI_jlabel_myHandLabel4;
	 private javax.swing.JLabel labelPlayerCard2mainGUI_jlabel_myHandLabel5;
	 private javax.swing.JButton mainGUI_jbutton_deck;
	 private javax.swing.JButton mainGUI_jbutton_discard;
	 private javax.swing.JLabel mainGUI_jlabel_myHandCard1;
	 private javax.swing.JLabel mainGUI_jlabel_myHandCard2;
	 private javax.swing.JLabel mainGUI_jlabel_myHandCard3;
	 private javax.swing.JLabel mainGUI_jlabel_myHandCard4;
	 private javax.swing.JLabel mainGUI_jlabel_myHandLabel1;
	 private javax.swing.JLabel mainGUI_jlabel_myHandLabel2;
	 private javax.swing.JLabel mainGUI_jlabel_oppCardLabel1;
	 private javax.swing.JLabel mainGUI_jlabel_oppCardLabel2;
	 private javax.swing.JLabel mainGUI_jlabel_oppCardLabel3;
	 private javax.swing.JLabel mainGUI_jlabel_oppCardLabel4;
	 private javax.swing.JLabel mainGUI_jlabel_oppHand1;
	 private javax.swing.JLabel mainGUI_jlabel_oppHand2;
	 private javax.swing.JLabel mainGUI_jlabel_oppHand3;
	 private javax.swing.JLabel mainGUI_jlabel_oppHand4;
	 private javax.swing.JLabel mainGUI_jlabel_oppLabel;
	 private javax.swing.JTextArea mainGUI_jtextarea_descripTextarea;


	private javax.swing.JButton peekInstruction_jbutton_ok;
	private javax.swing.JLabel peekInstruction_jlabel_graphic1;
	private javax.swing.JLabel peekInstruction_jlabel_graphic2;
	private javax.swing.JLabel peekInstruction_jlabel_label1;
	private javax.swing.JTextArea peekInstruction_jtextarea_instr1;
	private javax.swing.JTextArea peekInstruction_jtextarea_instr2;
	private javax.swing.JScrollPane scrollPeek;
	private javax.swing.JScrollPane scrollPowerCards;

	private javax.swing.JScrollPane scrollPowerCard1;
	private javax.swing.JScrollPane scrollSwap;
	private javax.swing.JButton swapInstrcution_jbutton_okButton;
	private javax.swing.JLabel swapInstrcution_jlabel_graphic1;
	private javax.swing.JLabel swapInstrcution_jlabel_graphic2;
	private javax.swing.JLabel swapInstrcution_jlabel_label1;
	private javax.swing.JTextArea swapInstrcution_jtextarea_textarea1;
	private javax.swing.JTextArea swapInstrcution_jtextarea_textarea2;

	private javax.swing.JLabel turnChange_jlabel_card1;
	private javax.swing.JLabel turnChange_jlabel_card2;
	private javax.swing.JButton turnChange_jlabel_goButton;
	private javax.swing.JLabel turnChange_jlabel_label1;
	private javax.swing.JLabel turnChange_jlabel_label2;



	private javax.swing.JButton cardDrew_jbutton_discard;
	private javax.swing.JButton cardDrew_jbutton_keep;
	private javax.swing.JLabel cardDrew_jlabel_graphic1;
	private javax.swing.JLabel cardDrew_jlabel_label1;

	private javax.swing.JButton knockPlay_jbutton_knock;
	private javax.swing.JButton knockPlay_jbutton_play;
	private javax.swing.JLabel knockPlay_jlabel_label1;

	private javax.swing.JButton peekInterface_jbutton_go;
	private javax.swing.JComboBox peekInterface_jcombobox_select1;
	private javax.swing.JLabel peekInterface_jlabel_card1;
	private javax.swing.JLabel peekInterface_jlabel_card2;
	private javax.swing.JLabel peekInterface_jlabel_card3;
	private javax.swing.JLabel peekInterface_jlabel_card4;
	private javax.swing.JLabel peekInterface_jlabel_label1;

	private javax.swing.JButton showPeekCard_jbutton_go;
	private javax.swing.JLabel showPeekCard_jlabel_card;
	private javax.swing.JLabel showPeekCard_jlabel_label1;

	private javax.swing.JButton swapDrawnCard_jbutton_go;
	private javax.swing.JComboBox swapDrawnCard_jcombobox_select1;
	private javax.swing.JLabel swapDrawnCard_jlabel_card1;
	private javax.swing.JLabel swapDrawnCard_jlabel_card2;
	private javax.swing.JLabel swapDrawnCard_jlabel_card3;
	private javax.swing.JLabel swapDrawnCard_jlabel_card4;
	private javax.swing.JLabel swapDrawnCard_jlabel_label1;

	private javax.swing.JButton swapOpp_jbutton_go;
	private javax.swing.JComboBox swapOpp_jcombobox_oppSelect;
	private javax.swing.JComboBox swapOpp_jcombobox_playerSelect;
	private javax.swing.JLabel swapOpp_jlabel_label1;
	private javax.swing.JLabel swapOpp_jlabel_label2;
	private javax.swing.JLabel swapOpp_jlabel_oppCard1;
	private javax.swing.JLabel swapOpp_jlabel_oppCard2;
	private javax.swing.JLabel swapOpp_jlabel_oppCard3;
	private javax.swing.JLabel swapOpp_jlabel_oppCard4;
	private javax.swing.JLabel swapOpp_jlabel_playerCard1;
	private javax.swing.JLabel swapOpp_jlabel_playerCard2;
	private javax.swing.JLabel swapOpp_jlabel_playerCard3;
	private javax.swing.JLabel swapOpp_jlabel_playerCard4;

	private javax.swing.JLabel timesUp_clockLabel1;
	private javax.swing.JLabel timesUp_clockLabel2;
	private javax.swing.JLabel timesUp_clockLabel3;
	private javax.swing.JLabel timesUp_clockLabel4;
	private javax.swing.JLabel timesUp_clockLabel5;
	private javax.swing.JButton timesUp_jbutton_go;
	private javax.swing.JLabel timesUp_label1;

	private javax.swing.JButton viewCardStart_jbutton_go;
	private javax.swing.JLabel viewCardStart_jlabel_card3;
	private javax.swing.JLabel viewCardStart_jlabel_card4;
	private javax.swing.JLabel viewCardsStart_jlabel_card1;
	private javax.swing.JLabel viewCardsStart_jlabel_card2;
	private javax.swing.JLabel viewCardsStart_jlabel_label1;
	
	private javax.swing.JLabel OpponentCard1;
	private javax.swing.JLabel OpponentCard2;
	private javax.swing.JLabel OpponentCard4;
	private javax.swing.JLabel OpponentCard3;
	private javax.swing.JLabel Player1Card2;
	private javax.swing.JLabel Player1Card1;
	private javax.swing.JLabel Player1Card4;
	private javax.swing.JLabel Player1Card3;
	private javax.swing.JComboBox comboPlayerCard;
	private javax.swing.JComboBox comboOpponentCard;
    private javax.swing.JButton buttonSwapDiscard;
    private javax.swing.JButton buttonPeek;
    private javax.swing.JLabel labelPlayer2Card1;
    private javax.swing.JLabel labelPlayer2Card2;
    private javax.swing.JLabel labelPlayer2Card3;
    private javax.swing.JLabel labelPlayer2Card4;
    private javax.swing.JLabel labelPlayer1Card1;
    private javax.swing.JLabel labelPlayer1Card2;
    private javax.swing.JLabel labelPlayer1Card3;
    private javax.swing.JLabel labelPlayer1Card4;
    private javax.swing.JLabel labelPlayer1;
    private javax.swing.JLabel labelPlayer2;
    private javax.swing.JLabel labelChooseCard;
    private javax.swing.JLabel labelChooseOppCard;
    private javax.swing.JButton buttonSwap;
    private javax.swing.JLabel labelCardShow;
    
    private javax.swing.JButton winnerFinal_jButton_continue;
    private javax.swing.JLabel winnerFinal_jLabel_p1Name;
    private javax.swing.JLabel winnerFinal_jLabel_p1TotalLabel;
    private javax.swing.JLabel winnerFinal_jLabel_p2TotalLabel;
    private javax.swing.JLabel winnerFinal_jLabel_winnerName;
    private javax.swing.JLabel winnerFinal_jlabel_label3;
    private javax.swing.JLabel winnerFinal_jlabel_p1Total;
    private javax.swing.JLabel winnerFinal_jlabel_p2Name;
    private javax.swing.JLabel winnerFinal_jlabel_p2Total;

    private javax.swing.JButton interRound_jButton_continue;
    private javax.swing.JLabel interRound_jLabel_label3;
    private javax.swing.JLabel interRound_jLabel_p1Name;
    private javax.swing.JLabel interRound_jLabel_p1SubtotalLabel;
    private javax.swing.JLabel interRound_jLabel_p1TotalLabel;
    private javax.swing.JLabel interRound_jLabel_p2SubtotalLabel;
    private javax.swing.JLabel interRound_jLabel_p2TotalLabel;
    private javax.swing.JLabel interRound_jLabel_rounds;
    private javax.swing.JLabel interRound_jLabel_time;
    private javax.swing.JLabel interRound_jlabel_p1Subtotal;
    private javax.swing.JLabel interRound_jlabel_p1Total;
    private javax.swing.JLabel interRound_jlabel_p2Name;
    private javax.swing.JLabel interRound_jlabel_p2Subtotal;
    private javax.swing.JLabel interRound_jlabel_p2Total;

    private javax.swing.JLabel loading_jLabel_label1;
    private javax.swing.JLabel loading_jlabel_gif;
 

	
	public void setUpGUI(int guiMode)
	{
		clearGUI();
		switch(guiMode)
		{
			case(0): //MAIN GUI
				 mainGUI_jbutton_deck = new javax.swing.JButton();
	        Deck = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandCard1 = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandCard2 = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandCard4 = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandCard3 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppHand1 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppHand2 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppHand3 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppHand4 = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        mainGUI_jtextarea_descripTextarea = new javax.swing.JTextArea();
	        labelPlayerCard2mainGUI_jlabel_myHandLabel5 = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandLabel2 = new javax.swing.JLabel();
	        labelPlayerCard2mainGUI_jlabel_myHandLabel3 = new javax.swing.JLabel();
	        labelPlayerCard2mainGUI_jlabel_myHandLabel4 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppCardLabel1 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppCardLabel2 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppCardLabel3 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppCardLabel4 = new javax.swing.JLabel();
	        mainGUI_jlabel_oppLabel = new javax.swing.JLabel();
	        mainGUI_jlabel_myHandLabel1 = new javax.swing.JLabel();
	        mainGUI_jbutton_discard = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(640, 480));
	        
	        mainGUI_jbutton_deck.setBackground(new java.awt.Color(255, 255, 255));
	        mainGUI_jbutton_deck.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
	        mainGUI_jbutton_deck.addActionListener(this);
	        mainGUI_jbutton_deck.setActionCommand("drawDeck");
	        
	        if(discard.getCard(0).isSpecial())
	        {
	        	mainGUI_jbutton_discard.setEnabled(false);
	        }

	        mainGUI_jlabel_myHandCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_myHandCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_myHandCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_myHandCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_oppHand1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_oppHand2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_oppHand3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jtextarea_descripTextarea.setColumns(20);
	        mainGUI_jtextarea_descripTextarea.setRows(5);
	        updateGameDialog();
	        jScrollPane1.setViewportView(mainGUI_jtextarea_descripTextarea);

	        labelPlayerCard2mainGUI_jlabel_myHandLabel5.setText("Card 4");

	        mainGUI_jlabel_myHandLabel2.setText("Card 1");

	        labelPlayerCard2mainGUI_jlabel_myHandLabel3.setText("Card 2");

	        labelPlayerCard2mainGUI_jlabel_myHandLabel4.setText("Card 3");

	        mainGUI_jlabel_oppCardLabel1.setText("Card 1");

	        mainGUI_jlabel_oppCardLabel2.setText("Card 2");

	        mainGUI_jlabel_oppCardLabel3.setText("Card 3");

	        mainGUI_jlabel_oppCardLabel4.setText("Card 4");

	        mainGUI_jlabel_oppHand4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N

	        mainGUI_jlabel_oppLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        mainGUI_jlabel_oppLabel.setText("Opponent's Hand:");

	        mainGUI_jlabel_myHandLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        mainGUI_jlabel_myHandLabel1.setText("Your Hand:");

	        mainGUI_jbutton_discard.setBackground(new java.awt.Color(255, 255, 255));
	        if(discard.getCard(0).getRank() == -1)
	        {
	        	mainGUI_jbutton_discard.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + discard.getCard(0).getSpecial() + ".png"))))); // NOI18N	
	        }
	        else
	        {
	        	mainGUI_jbutton_discard.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + discard.getCard(0).getRank() + ".png"))))); // NOI18N
	        }
	        mainGUI_jbutton_discard.setText("jButton1");
	        mainGUI_jbutton_discard.addActionListener(this);
	        mainGUI_jbutton_discard.setActionCommand("drawDiscard");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(Deck)
	                .addGap(0, 0, Short.MAX_VALUE))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(mainGUI_jbutton_deck, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addComponent(mainGUI_jbutton_discard, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(99, 99, 99)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(mainGUI_jlabel_oppHand1)
	                                    .addComponent(mainGUI_jlabel_oppCardLabel1))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(mainGUI_jlabel_oppCardLabel2)
	                                    .addComponent(mainGUI_jlabel_oppHand2))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(mainGUI_jlabel_oppCardLabel3)
	                                    .addComponent(mainGUI_jlabel_oppHand3))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(mainGUI_jlabel_oppCardLabel4)
	                                    .addComponent(mainGUI_jlabel_oppHand4)))
	                            .addComponent(mainGUI_jlabel_oppLabel)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(101, 101, 101)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(mainGUI_jlabel_myHandLabel1)
	                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                .addComponent(mainGUI_jlabel_myHandLabel2)
	                                .addComponent(mainGUI_jlabel_myHandCard1)))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel3)
	                            .addComponent(mainGUI_jlabel_myHandCard2))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel4)
	                            .addComponent(mainGUI_jlabel_myHandCard3))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel5)
	                            .addComponent(mainGUI_jlabel_myHandCard4))))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(Deck)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(mainGUI_jlabel_oppLabel)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(mainGUI_jlabel_oppHand1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_oppHand2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_oppHand3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_oppHand4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(mainGUI_jlabel_oppCardLabel2)
	                    .addComponent(mainGUI_jlabel_oppCardLabel3)
	                    .addComponent(mainGUI_jlabel_oppCardLabel4)
	                    .addComponent(mainGUI_jlabel_oppCardLabel1))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
	                    .addComponent(mainGUI_jbutton_deck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(mainGUI_jbutton_discard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addGap(38, 38, 38)
	                .addComponent(mainGUI_jlabel_myHandLabel1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(mainGUI_jlabel_myHandCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_myHandCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_myHandCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(mainGUI_jlabel_myHandCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(mainGUI_jlabel_myHandLabel2)
	                    .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel3)
	                    .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel4)
	                    .addComponent(labelPlayerCard2mainGUI_jlabel_myHandLabel5))
	                .addContainerGap(31, Short.MAX_VALUE))
	        );
				break;
			/*
			 * INSTRUCTIONS
			 */
			case(1):
				 instructions_jlabel_label1 = new javax.swing.JLabel();
		        instructions_jbutton_play = new javax.swing.JButton();
		        instructions_jlabel_label3 = new javax.swing.JLabel();
		        instructions_jlabel_label4 = new javax.swing.JLabel();
		        instructions_jlabel_label6_link = new javax.swing.JLabel();
		        scrollGameRules = new javax.swing.JScrollPane();
		        instructions_jtextarea_ta1 = new javax.swing.JTextArea();
		        instructions_jcombobox_rounds = new javax.swing.JComboBox();
		        instructions_jcombobox_difficulty = new javax.swing.JComboBox();
		        instructions_jtextfield_p1NameInput = new javax.swing.JTextField();
		        instructions_jlabel_label2 = new javax.swing.JLabel();
		        instructions_jlabel_label6 = new javax.swing.JLabel();
	
		        
		        instructions_jlabel_label1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		        instructions_jlabel_label1.setText("Instructions:");
	
		        instructions_jbutton_play.setText("Play");
		        instructions_jbutton_play.addActionListener(this);
		        instructions_jbutton_play.setActionCommand("play");
	
		        instructions_jlabel_label3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        instructions_jlabel_label3.setText("Style of Game");
	
		        instructions_jlabel_label4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        instructions_jlabel_label4.setText("Difficulty");
	
		        instructions_jlabel_label6_link.setText("<html><a href=\"http://www.gamewright.com/gamewright/pdfs/Rules/Rat-a-TatCat-RULES.pdf\">http://www.gamewright.com/gamewright/pdfs/Rules/Rat-a-TatCat-RULES.pdf</a>");
	
		        instructions_jtextarea_ta1.setEditable(false);
		        instructions_jtextarea_ta1.setColumns(20);
		        instructions_jtextarea_ta1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		        instructions_jtextarea_ta1.setLineWrap(true);
		        instructions_jtextarea_ta1.setRows(5);
		        instructions_jtextarea_ta1.setText("The goal is to have the lowest score at the end of the game.\n\nChoose one player to be the dealer, and a scorekeeper. The scorekeeper will record each playerâ€™s score at the end of each round of play.\n\nShuffle the deck. The player to the left of the dealer cuts the cards. \nThe dealer then deals four cards, one at a time and face down, to each player.\nThe remaining cards are placed face down, in the middle of the table, as the\ndraw pile. The top card of the draw pile is turned over to start the discard\npile. If that card is a Power card, it is placed back in the deck and another\ncard is turned over.\n\nWithout looking at his cards, each player places his or her four cards face down in a line on the table in front of them.\n\nDuring the game, players will always have their four cards face down on the\ntable. To begin the game, players peek at their two outer cards once, then\nturn them face down again. Each player now knows the point values of two of\nhis four cards and needs to remember them during the game.\nIf either of the outer cards are Power cards, the player keeps them, but they\ndo not have their powers (described below). Power cards only have their\npowers when they are drawn from the top of the draw pile.  \nThe player to the left of the dealer has the first turn, and play continues in a\nclockwise direction.\n\nFor each turn, a player may:\n\n1. Draw the top card from the discard pile. This card MUST be used to\nreplace one of her cards. The card replaced is then discarded, face up, to the\ndiscard pile.\n\n2. Draw the top card from the draw pile. A player may use it to: \n\t1. Replace one of her cards\n\t2. Peek, Swap, or Draw 2 if it is a Power card (see below)\n\t3. Discard it face up to the discard pile\n\nA playerâ€™s choice is based on remembering the values of his four face down\ncards. Keep track of what you have so you wonâ€™t accidentally replace your\nlow point cards with high point cards.\nDuring the game, when the draw pile is used up, shuffle the discard pile and\nturn it over for a new draw pile.\n\nPower cards only have their powers when you draw them from the draw pile. If a Power card is dealt to you at the beginning of the game, it cannot be used. Because Power cards have no point value, if one of them is among your cards at the end of the game, you must replace it with a card drawn from the draw pile. If a Power card is discarded, it may not be used again by any player.\n\nThere are three kinds of Power cards:\n1. Peek\n\tWhen you draw a Peek card, show it and then peek at any\n\tone of your cards. Now you will know what you have, or you\n\tcan refresh your memory if you have forgotten what you have.\n\tYour turn is over and you discard the Peek card.\n2. Swap\n\tWhen you draw a Swap card, show the Swap card and \n\tdiscard it. You may now switch any one of your cards with\n\tany card of another player (swapping is optional). Neither player \t\tcan look at either of the cards being swapped. After the swap \t\tyour turn is over.\n3. Draw 2\n\tWhen you draw a Draw 2 card, show the card and then you\n\tmay take two more turns. First you draw the next card from the\n\tdraw pile. You must decide whether to use this card and forfeit\n\tthe second turn OR discard this card and draw a second card.\n\tThis second card may be used or discarded. Your turn is then\n\tover. If either of the cards drawn are another Draw 2 card, the\n\tDraw 2 sequence starts again.\n\nEnding the Round\n\nWhen a player thinks he has the lowest score and can win the round, he or she may end the round by knocking on the table and saying â€œrat-a-tat catâ€� at\nthe end of their turn. Once they knock, every other player has one more turn.\nEach player then turns over their cards. Players replace all Power cards by\ndrawing from the draw pile. If another Power card is drawn, the player\ndraws again.\n\nScoring\n\nPlayers add the point values of their four cards. This is each playerâ€™s score\nfor the round. The scorekeeper records each playerâ€™s score. Remember that\nplayers are trying to get as low a score as possible.\n\nNext Rounds\n\nAll cards are collected and passed to the player to the left of the dealer who\nreshuffles and deals for the next round.\n\nEnding the Game\n\nThe player with the lowest total score at the end of the game is the winner.\n\nA game may be played three ways:\n1. Play for a certain number of rounds.\n2. Play for a specific length of time.\n3. Play to stay in the game and not reach 100 points. When a player \nreaches 100 points, he is out of the game. The last player in the game is \nthe winner. Players may also choose to play to 200, or any other number \nof points.");
		        instructions_jtextarea_ta1.setWrapStyleWord(true);
		        scrollGameRules.setViewportView(instructions_jtextarea_ta1);
	
		        instructions_jcombobox_rounds.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rounds", "Time", "Points" }));
		        instructions_jcombobox_rounds.setToolTipText("");
		  
		        instructions_jcombobox_difficulty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Medium", "Hard" }));
		
		        instructions_jtextfield_p1NameInput.setText("name here");

		        instructions_jlabel_label2.setText("Player 1 Name");
		      
	
		        instructions_jlabel_label6.setText("For More Game Rules See:");
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(20, 20, 20)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(instructions_jlabel_label6_link, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(instructions_jlabel_label6)
		                    .addComponent(instructions_jlabel_label1)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(scrollGameRules, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(instructions_jtextfield_p1NameInput)
		                            .addComponent(instructions_jlabel_label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(instructions_jlabel_label3)
		                            .addComponent(instructions_jlabel_label4)
		                            .addComponent(instructions_jbutton_play, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
		                            .addComponent(instructions_jcombobox_rounds, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(instructions_jcombobox_difficulty, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(instructions_jlabel_label1)
		                .addGap(12, 12, 12)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(instructions_jlabel_label2)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(instructions_jtextfield_p1NameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(55, 55, 55)
		                        .addComponent(instructions_jlabel_label3)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(instructions_jcombobox_rounds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(24, 24, 24)
		                        .addComponent(instructions_jlabel_label4)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(instructions_jcombobox_difficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(instructions_jbutton_play, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(scrollGameRules, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(instructions_jlabel_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(instructions_jlabel_label6_link, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
				break;
			/*
			 * SHOW CARD KEEP OR DISCARD
			 */
			case(2):
				cardDrew_jlabel_graphic1 = new javax.swing.JLabel();
		        cardDrew_jlabel_label1 = new javax.swing.JLabel();
		        cardDrew_jbutton_keep = new javax.swing.JButton();
		        cardDrew_jbutton_discard = new javax.swing.JButton();
	
		        if(mainDeck.getCard(0).getRank() == -1)
		        {
		        	cardDrew_jlabel_graphic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + mainDeck.getCard(0).getSpecial() + ".png"))))); // NOI18N
		        }
		        else
		        {
		        	cardDrew_jlabel_graphic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + mainDeck.getCard(0).getRank() + ".png"))))); // NOI18N
		        }
		        cardDrew_jlabel_graphic1.setText("jLabel1");
	
		        cardDrew_jlabel_label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		        cardDrew_jlabel_label1.setText("You drew a card:");
	
		        cardDrew_jbutton_keep.setText("Keep it!");
		        cardDrew_jbutton_keep.addActionListener(this);
		        cardDrew_jbutton_keep.setActionCommand("keepCard");
		        
	
		        cardDrew_jbutton_discard.setText("Discard it!");
		        cardDrew_jbutton_discard.addActionListener(this);
		        cardDrew_jbutton_discard.setActionCommand("giveUpCard");
		        
		        
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addComponent(cardDrew_jlabel_label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addComponent(cardDrew_jbutton_keep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(cardDrew_jbutton_discard)
		                        .addGap(6, 6, 6)))
		                .addGap(18, 18, 18)
		                .addComponent(cardDrew_jlabel_graphic1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(cardDrew_jlabel_graphic1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(26, 26, 26)
		                        .addComponent(cardDrew_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(cardDrew_jbutton_keep, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(cardDrew_jbutton_discard, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
				break;
			/*
			 * DRAW 2 INSTRUCTIONS
			 */
			case(3):
				scrollPowerCard2 = new javax.swing.JScrollPane();
		        draw2Instruct_jtextarea_instruct2 = new javax.swing.JTextArea();
		        draw2Instruct_jlabel_label1 = new javax.swing.JLabel();
		        draw2Instruct_jlabel_graphic2 = new javax.swing.JLabel();
		        draw2Instruct_jlabel_graphic1 = new javax.swing.JLabel();
		        draw2Instruct_jbutton_ok = new javax.swing.JButton();
		        scrollDraw2 = new javax.swing.JScrollPane();
		        draw2Instruct_jtextarea_instruct1 = new javax.swing.JTextArea();
	
		        draw2Instruct_jtextarea_instruct2.setEditable(false);
		        draw2Instruct_jtextarea_instruct2.setColumns(20);
		        draw2Instruct_jtextarea_instruct2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        draw2Instruct_jtextarea_instruct2.setLineWrap(true);
		        draw2Instruct_jtextarea_instruct2.setRows(5);
		        draw2Instruct_jtextarea_instruct2.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
		        draw2Instruct_jtextarea_instruct2.setWrapStyleWord(true);
		        scrollPowerCard2.setViewportView(draw2Instruct_jtextarea_instruct2);
	
		        draw2Instruct_jlabel_label1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		        draw2Instruct_jlabel_label1.setText("You've Drawn a Draw 2 Card!");
	
		        draw2Instruct_jlabel_graphic2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"10.png"))))); // NOI18N
	
		        draw2Instruct_jlabel_graphic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"10.png"))))); // NOI18N
	
		        draw2Instruct_jbutton_ok.setText("OK");
	
		        draw2Instruct_jtextarea_instruct1.setEditable(false);
		        draw2Instruct_jtextarea_instruct1.setColumns(20);
		        draw2Instruct_jtextarea_instruct1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        draw2Instruct_jtextarea_instruct1.setLineWrap(true);
		        draw2Instruct_jtextarea_instruct1.setRows(5);
		        draw2Instruct_jtextarea_instruct1.setText("Take a card from the draw pile, and either swap it with one of your cards or discard it and draw a second card to be swapped or thrown out.");
		        draw2Instruct_jtextarea_instruct1.setWrapStyleWord(true);
		        scrollDraw2.setViewportView(draw2Instruct_jtextarea_instruct1);
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(103, 103, 103)
		                        .addComponent(draw2Instruct_jlabel_label1))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(draw2Instruct_jlabel_graphic1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(draw2Instruct_jbutton_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                .addComponent(scrollDraw2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
		                                .addComponent(scrollPowerCard2)))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(draw2Instruct_jlabel_graphic2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(draw2Instruct_jlabel_label1)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(draw2Instruct_jlabel_graphic1)
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                        .addComponent(draw2Instruct_jlabel_graphic2)
		                        .addGroup(layout.createSequentialGroup()
		                            .addComponent(scrollDraw2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addGap(19, 19, 19)
		                            .addComponent(scrollPowerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(draw2Instruct_jbutton_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap())
		        );
	
		        getRootPane().setDefaultButton(draw2Instruct_jbutton_ok);
				break;
			/*
			 * KNOCK OR PLAY
			 */
			case(4):
			    knockPlay_jbutton_knock = new javax.swing.JButton();
		        knockPlay_jbutton_play = new javax.swing.JButton();
		        knockPlay_jlabel_label1 = new javax.swing.JLabel();
	
		  
		        knockPlay_jbutton_knock.setText("Knock");
		        knockPlay_jbutton_knock.addActionListener(this);
		        knockPlay_jbutton_knock.setActionCommand("knock");
		        
		        knockPlay_jbutton_play.setText("Keep Playing");
		        knockPlay_jbutton_play.addActionListener(this);
		        knockPlay_jbutton_play.setActionCommand("keepPlaying");
	
		        knockPlay_jlabel_label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		        knockPlay_jlabel_label1.setText("Do you want to knock or continue playing?");
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(knockPlay_jlabel_label1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
		                .addContainerGap())
		            .addGroup(layout.createSequentialGroup()
		                .addGap(84, 84, 84)
		                .addComponent(knockPlay_jbutton_knock, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(knockPlay_jbutton_play, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(105, 105, 105))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(knockPlay_jlabel_label1)
		                .addGap(30, 30, 30)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(knockPlay_jbutton_knock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(knockPlay_jbutton_play, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(29, Short.MAX_VALUE))
		        );
				break;
			/*
			 * PEEK INTERFACE
			 */
			case(5):
				peekInterface_jlabel_card1 = new javax.swing.JLabel();
		        peekInterface_jlabel_card2 = new javax.swing.JLabel();
		        peekInterface_jlabel_card3 = new javax.swing.JLabel();
		        peekInterface_jlabel_card4 = new javax.swing.JLabel();
		        peekInterface_jcombobox_select1 = new javax.swing.JComboBox();
		        peekInterface_jlabel_label1 = new javax.swing.JLabel();
		        peekInterface_jbutton_go = new javax.swing.JButton();
	
		        
		        peekInterface_jlabel_card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        peekInterface_jlabel_card1.setText("jLabel1");
	
		        peekInterface_jlabel_card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        peekInterface_jlabel_card2.setText("jLabel1");
	
		        peekInterface_jlabel_card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        peekInterface_jlabel_card3.setText("jLabel1");
	
		        peekInterface_jlabel_card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        peekInterface_jlabel_card4.setText("jLabel1");
	
		        peekInterface_jcombobox_select1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
	
		        peekInterface_jlabel_label1.setText("Which card would you like to peek?");
	
		        peekInterface_jbutton_go.setText("Go");
		        peekInterface_jbutton_go.addActionListener(this);
		        peekInterface_jbutton_go.setActionCommand("peekCard");
		        
		        
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(52, 52, 52)
		                        .addComponent(peekInterface_jlabel_label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(46, 46, 46)
		                        .addComponent(peekInterface_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(peekInterface_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(peekInterface_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(peekInterface_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(peekInterface_jcombobox_select1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(peekInterface_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(30, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(peekInterface_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(peekInterface_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(peekInterface_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(peekInterface_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(peekInterface_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(peekInterface_jcombobox_select1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(peekInterface_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
				break;
			/*
			 * PEEK INSTRUCTIONS
			 */
			case(6):
				peekInstruction_jbutton_ok = new javax.swing.JButton();
		        scrollPeek = new javax.swing.JScrollPane();
		        peekInstruction_jtextarea_instr1 = new javax.swing.JTextArea();
		        scrollPowerCards = new javax.swing.JScrollPane();
		        peekInstruction_jtextarea_instr2 = new javax.swing.JTextArea();
		        peekInstruction_jlabel_label1 = new javax.swing.JLabel();
		        peekInstruction_jlabel_graphic2 = new javax.swing.JLabel();
		        peekInstruction_jlabel_graphic1 = new javax.swing.JLabel();
	
		    
	
		        peekInstruction_jbutton_ok.setText("OK");
		        peekInstruction_jbutton_ok.addActionListener(this);
		        peekInstruction_jbutton_ok.setActionCommand("ok");
		        
	
		        peekInstruction_jtextarea_instr1.setEditable(false);
		        peekInstruction_jtextarea_instr1.setColumns(20);
		        peekInstruction_jtextarea_instr1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        peekInstruction_jtextarea_instr1.setLineWrap(true);
		        peekInstruction_jtextarea_instr1.setRows(5);
		        peekInstruction_jtextarea_instr1.setText("Take a card from the draw pile, and either swap it with one of your cards or discard it and draw a second card to be swapped or thrown out.");
		        peekInstruction_jtextarea_instr1.setWrapStyleWord(true);
		        scrollPeek.setViewportView(peekInstruction_jtextarea_instr1);
	
		        peekInstruction_jtextarea_instr2.setEditable(false);
		        peekInstruction_jtextarea_instr2.setColumns(20);
		        peekInstruction_jtextarea_instr2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        peekInstruction_jtextarea_instr2.setLineWrap(true);
		        peekInstruction_jtextarea_instr2.setRows(5);
		        peekInstruction_jtextarea_instr2.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
		        peekInstruction_jtextarea_instr2.setWrapStyleWord(true);
		        scrollPowerCards.setViewportView(peekInstruction_jtextarea_instr2);
	
		        peekInstruction_jlabel_label1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		        peekInstruction_jlabel_label1.setText("You've Drawn a Peek Card!");
	
		        peekInstruction_jlabel_graphic2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"11.png"))))); // NOI18N
	
		        peekInstruction_jlabel_graphic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"11.png"))))); // NOI18N
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(peekInstruction_jlabel_graphic1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(peekInstruction_jbutton_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                .addComponent(scrollPeek, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
		                                .addComponent(scrollPowerCards)))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(peekInstruction_jlabel_graphic2))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(113, 113, 113)
		                        .addComponent(peekInstruction_jlabel_label1)))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(21, 21, 21)
		                .addComponent(peekInstruction_jlabel_label1)
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(peekInstruction_jlabel_graphic1)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(scrollPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(scrollPowerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(peekInstruction_jlabel_graphic2))
		                .addGap(18, 18, 18)
		                .addComponent(peekInstruction_jbutton_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(25, Short.MAX_VALUE))
		        );
				break;
			/*
			 * SHOW PEEK CARD
			 */
			case(7):
				showPeekCard_jlabel_label1 = new javax.swing.JLabel();
		        showPeekCard_jlabel_card = new javax.swing.JLabel();
		        showPeekCard_jbutton_go = new javax.swing.JButton();
	
		     
		        showPeekCard_jlabel_label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		        showPeekCard_jlabel_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        showPeekCard_jlabel_label1.setText("Peek Card");
	
		        showPeekCard_jlabel_card.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[GAME_STATE.getPlayer()].myHand.getCard(cardToPeek) + ".png"))))); // NOI18N
		        cardToPeek = -1;
		        showPeekCard_jbutton_go.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        showPeekCard_jbutton_go.setText("Go");
		        showPeekCard_jbutton_go.addActionListener(this);
		        showPeekCard_jbutton_go.setActionCommand("changePlayer");
		        
		        
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                            .addComponent(showPeekCard_jlabel_label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(showPeekCard_jlabel_card, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                        .addGap(0, 0, Short.MAX_VALUE))
		                    .addComponent(showPeekCard_jbutton_go, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                .addContainerGap())
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(showPeekCard_jlabel_card)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(showPeekCard_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(18, 18, 18)
		                .addComponent(showPeekCard_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(20, Short.MAX_VALUE))
		        );
				break;
			/*
			 * SWAP OPPONENT CARD WITH MY CARD
			 */
			case(8):
				swapOpp_jlabel_oppCard4 = new javax.swing.JLabel();
		        swapOpp_jlabel_oppCard1 = new javax.swing.JLabel();
		        swapOpp_jlabel_oppCard2 = new javax.swing.JLabel();
		        swapOpp_jlabel_oppCard3 = new javax.swing.JLabel();
		        swapOpp_jlabel_label1 = new javax.swing.JLabel();
		        swapOpp_jlabel_playerCard1 = new javax.swing.JLabel();
		        swapOpp_jlabel_playerCard2 = new javax.swing.JLabel();
		        swapOpp_jlabel_playerCard3 = new javax.swing.JLabel();
		        swapOpp_jlabel_playerCard4 = new javax.swing.JLabel();
		        swapOpp_jlabel_label2 = new javax.swing.JLabel();
		        swapOpp_jcombobox_oppSelect = new javax.swing.JComboBox();
		        swapOpp_jcombobox_playerSelect = new javax.swing.JComboBox();
		        swapOpp_jbutton_go = new javax.swing.JButton();
		        
		        swapOpp_jlabel_oppCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_oppCard4.setText("jLabel1");
	
		        swapOpp_jlabel_oppCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_oppCard1.setText("jLabel1");
	
		        swapOpp_jlabel_oppCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_oppCard2.setText("jLabel1");
	
		        swapOpp_jlabel_oppCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_oppCard3.setText("jLabel1");
	
		        swapOpp_jlabel_label1.setText("Select the number of the opponent's card you want to swap");
	
		        swapOpp_jlabel_playerCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_playerCard1.setText("jLabel1");
	
		        swapOpp_jlabel_playerCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_playerCard2.setText("jLabel1");
	
		        swapOpp_jlabel_playerCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_playerCard3.setText("jLabel1");
	
		        swapOpp_jlabel_playerCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapOpp_jlabel_playerCard4.setText("jLabel1");
	
		        swapOpp_jlabel_label2.setText("Select the number of your card you want to swap");
	
		        swapOpp_jcombobox_oppSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
	
		        swapOpp_jcombobox_playerSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
	
		        swapOpp_jbutton_go.setText("Go");
		        swapOpp_jbutton_go.addActionListener(this);
		        swapOpp_jbutton_go.setActionCommand("swapFromOpp");
		        
		        
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(22, 22, 22)
		                        .addComponent(swapOpp_jlabel_oppCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(swapOpp_jlabel_oppCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(swapOpp_jlabel_oppCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(swapOpp_jlabel_oppCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(swapOpp_jlabel_playerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(swapOpp_jlabel_playerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(swapOpp_jlabel_playerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(swapOpp_jlabel_playerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(swapOpp_jlabel_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(31, 31, 31)
		                                .addComponent(swapOpp_jcombobox_playerSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(swapOpp_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(28, 28, 28)
		                        .addComponent(swapOpp_jcombobox_oppSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(swapOpp_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(15, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapOpp_jlabel_oppCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_oppCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_oppCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_oppCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapOpp_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jcombobox_oppSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapOpp_jlabel_playerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_playerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_playerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jlabel_playerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapOpp_jlabel_label2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapOpp_jcombobox_playerSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(swapOpp_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(0, 12, Short.MAX_VALUE))
		        );
				break;
			/*
			 * SWAP DIALOG
			 */
			case(9):
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
	
		        OpponentCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        OpponentCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        OpponentCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        OpponentCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        Player1Card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        Player1Card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        Player1Card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        Player1Card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13tiny.png"))))); // NOI18N
	
		        comboPlayerCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Card 1", "Card 2", "Card 3", "Card 4" }));
	
		        comboOpponentCard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Card 1", "Card 2", "Card 3", "Card 4" }));
	
		        buttonSwapDiscard.setText("Discard");
		        buttonSwapDiscard.addActionListener(this);
		        buttonSwapDiscard.setActionCommand("discard");
		        
	
		        buttonPeek.setText("Peek");
		        buttonPeek.addActionListener(this);
		        buttonPeek.setActionCommand("peek");
		    	
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
		        buttonSwap.addActionListener(this);
		        buttonSwap.setActionCommand("swap");
	
		        labelCardShow.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(70, 70, 70)
		                .addComponent(OpponentCard1)
		                .addGap(25, 25, 25)
		                .addComponent(OpponentCard2)
		                .addGap(15, 15, 15)
		                .addComponent(OpponentCard3)
		                .addGap(15, 15, 15)
		                .addComponent(OpponentCard4))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(100, 100, 100)
		                .addComponent(labelPlayer2Card1)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer2Card2)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer2Card3)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer2Card4))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(110, 110, 110)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(120, 120, 120)
		                        .addComponent(labelPlayer2))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(110, 110, 110)
		                        .addComponent(labelChooseCard))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(80, 80, 80)
		                        .addComponent(labelChooseOppCard))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(buttonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(27, 27, 27)
		                        .addComponent(buttonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addGap(16, 16, 16)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(comboPlayerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(comboOpponentCard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(buttonSwapDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(37, 37, 37)
		                .addComponent(labelCardShow))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(230, 230, 230)
		                .addComponent(labelPlayer1))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(100, 100, 100)
		                .addComponent(labelPlayer1Card1)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer1Card2)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer1Card3)
		                .addGap(58, 58, 58)
		                .addComponent(labelPlayer1Card4))
		            .addGroup(layout.createSequentialGroup()
		                .addGap(70, 70, 70)
		                .addComponent(Player1Card1)
		                .addGap(25, 25, 25)
		                .addComponent(Player1Card2)
		                .addGap(15, 15, 15)
		                .addComponent(Player1Card3)
		                .addGap(15, 15, 15)
		                .addComponent(Player1Card4))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(10, 10, 10)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(OpponentCard1)
		                    .addComponent(OpponentCard2)
		                    .addComponent(OpponentCard3)
		                    .addComponent(OpponentCard4))
		                .addGap(13, 13, 13)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(labelPlayer2Card1)
		                    .addComponent(labelPlayer2Card2)
		                    .addComponent(labelPlayer2Card3)
		                    .addComponent(labelPlayer2Card4))
		                .addGap(6, 6, 6)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(labelPlayer2)
		                        .addGap(26, 26, 26)
		                        .addComponent(labelChooseCard)
		                        .addGap(16, 16, 16)
		                        .addComponent(labelChooseOppCard)
		                        .addGap(36, 36, 36)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(buttonPeek, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(buttonSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                    .addGroup(layout.createSequentialGroup()
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
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(labelPlayer1Card1)
		                    .addComponent(labelPlayer1Card2)
		                    .addComponent(labelPlayer1Card3)
		                    .addComponent(labelPlayer1Card4))
		                .addGap(6, 6, 6)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(Player1Card1)
		                    .addComponent(Player1Card2)
		                    .addComponent(Player1Card3)
		                    .addComponent(Player1Card4)))
		        );
				break;
			/*
			 * SWAP DECK CARD WITH MY HAND
			 */
			case(10):
				 swapDrawnCard_jlabel_card1 = new javax.swing.JLabel();
		        swapDrawnCard_jlabel_card2 = new javax.swing.JLabel();
		        swapDrawnCard_jlabel_card3 = new javax.swing.JLabel();
		        swapDrawnCard_jlabel_card4 = new javax.swing.JLabel();
		        swapDrawnCard_jcombobox_select1 = new javax.swing.JComboBox();
		        swapDrawnCard_jlabel_label1 = new javax.swing.JLabel();
		        swapDrawnCard_jbutton_go = new javax.swing.JButton();
	
		        
		        swapDrawnCard_jlabel_card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapDrawnCard_jlabel_card1.setText("jLabel1");
	
		        swapDrawnCard_jlabel_card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapDrawnCard_jlabel_card2.setText("jLabel1");
	
		        swapDrawnCard_jlabel_card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapDrawnCard_jlabel_card3.setText("jLabel1");
	
		        swapDrawnCard_jlabel_card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        swapDrawnCard_jlabel_card4.setText("jLabel1");
	
		        swapDrawnCard_jcombobox_select1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
	
		        swapDrawnCard_jlabel_label1.setText("Which card would you like to swap with the drawn card?");
	
		        swapDrawnCard_jbutton_go.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        swapDrawnCard_jbutton_go.setText("Go");
		        swapDrawnCard_jbutton_go.addActionListener(this);
		        swapDrawnCard_jbutton_go.setActionCommand("chooseCardInHandToReplace");
		        
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(swapDrawnCard_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(swapDrawnCard_jcombobox_select1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(29, 29, 29)
		                        .addComponent(swapDrawnCard_jbutton_go, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(swapDrawnCard_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(swapDrawnCard_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(swapDrawnCard_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(swapDrawnCard_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addGap(0, 16, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap(24, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapDrawnCard_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapDrawnCard_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapDrawnCard_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapDrawnCard_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(swapDrawnCard_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapDrawnCard_jcombobox_select1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(swapDrawnCard_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
		        );
				break;
			/*
			 * SWAP INSTUCTIONS DIALOG
			 */
			case(11):
				swapInstrcution_jbutton_okButton = new javax.swing.JButton();
		        scrollSwap = new javax.swing.JScrollPane();
		        swapInstrcution_jtextarea_textarea1 = new javax.swing.JTextArea();
		        swapInstrcution_jlabel_label1 = new javax.swing.JLabel();
		        scrollPowerCard1 = new javax.swing.JScrollPane();
		        swapInstrcution_jtextarea_textarea2 = new javax.swing.JTextArea();
		        swapInstrcution_jlabel_graphic2 = new javax.swing.JLabel();
		        swapInstrcution_jlabel_graphic1 = new javax.swing.JLabel();
	
		        swapInstrcution_jbutton_okButton.setText("OK");
		        swapInstrcution_jbutton_okButton.addActionListener(this);
		        swapInstrcution_jbutton_okButton.setActionCommand("ok");
	
		        swapInstrcution_jtextarea_textarea1.setEditable(false);
		        swapInstrcution_jtextarea_textarea1.setColumns(20);
		        swapInstrcution_jtextarea_textarea1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        swapInstrcution_jtextarea_textarea1.setLineWrap(true);
		        swapInstrcution_jtextarea_textarea1.setRows(5);
		        swapInstrcution_jtextarea_textarea1.setText("You may trade one of your cards with an opponent's. Neither player may look at the card  values being swapped.");
		        swapInstrcution_jtextarea_textarea1.setWrapStyleWord(true);
		        scrollSwap.setViewportView(swapInstrcution_jtextarea_textarea1);
	
		        swapInstrcution_jlabel_label1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		        swapInstrcution_jlabel_label1.setText("You've Drawn a Swap Card!");
	
		        swapInstrcution_jtextarea_textarea2.setEditable(false);
		        swapInstrcution_jtextarea_textarea2.setColumns(20);
		        swapInstrcution_jtextarea_textarea2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		        swapInstrcution_jtextarea_textarea2.setLineWrap(true);
		        swapInstrcution_jtextarea_textarea2.setRows(5);
		        swapInstrcution_jtextarea_textarea2.setText("Power cards have no value and if they are in a player's hand at the end of the round. They must be swapped for the top card of the deck.");
		        swapInstrcution_jtextarea_textarea2.setWrapStyleWord(true);
		        scrollPowerCard1.setViewportView(swapInstrcution_jtextarea_textarea2);
	
		        swapInstrcution_jlabel_graphic2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"12.png"))))); // NOI18N
	
		        swapInstrcution_jlabel_graphic1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"12.png"))))); // NOI18N
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(swapInstrcution_jlabel_graphic1)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(scrollPowerCard1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
		                            .addComponent(scrollSwap, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
		                            .addComponent(swapInstrcution_jbutton_okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(swapInstrcution_jlabel_graphic2))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(116, 116, 116)
		                        .addComponent(swapInstrcution_jlabel_label1)))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(swapInstrcution_jlabel_label1)
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                    .addComponent(scrollPowerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addComponent(scrollSwap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(swapInstrcution_jlabel_graphic1)
		                        .addComponent(swapInstrcution_jlabel_graphic2, javax.swing.GroupLayout.Alignment.TRAILING)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(swapInstrcution_jbutton_okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
				break;
			/*
			 * TIMES UP GUI
			 */
			case(12):
				timesUp_label1 = new javax.swing.JLabel();
		        timesUp_clockLabel2 = new javax.swing.JLabel();
		        timesUp_clockLabel1 = new javax.swing.JLabel();
		        timesUp_clockLabel3 = new javax.swing.JLabel();
		        timesUp_clockLabel5 = new javax.swing.JLabel();
		        timesUp_clockLabel4 = new javax.swing.JLabel();
		        timesUp_jbutton_go = new javax.swing.JButton();
	
		       
		        timesUp_label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        timesUp_label1.setText("Your Time is Up, this is your last turn. ");
	
		        timesUp_clockLabel2.setBackground(new java.awt.Color(255, 255, 255));
		        timesUp_clockLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        timesUp_clockLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        timesUp_clockLabel2.setText("0");
		        timesUp_clockLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        timesUp_clockLabel1.setBackground(new java.awt.Color(255, 255, 255));
		        timesUp_clockLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        timesUp_clockLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        timesUp_clockLabel1.setText("0");
		        timesUp_clockLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        timesUp_clockLabel3.setBackground(new java.awt.Color(255, 255, 255));
		        timesUp_clockLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        timesUp_clockLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        timesUp_clockLabel3.setText(":");
	
		        timesUp_clockLabel5.setBackground(new java.awt.Color(255, 255, 255));
		        timesUp_clockLabel5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        timesUp_clockLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        timesUp_clockLabel5.setText("0");
		        timesUp_clockLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        timesUp_clockLabel4.setBackground(new java.awt.Color(255, 255, 255));
		        timesUp_clockLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        timesUp_clockLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        timesUp_clockLabel4.setText("0");
		        timesUp_clockLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        timesUp_jbutton_go.setText("Go");
		        timesUp_jbutton_go.addActionListener(this);
		        timesUp_jbutton_go.setActionCommand("go");
		        
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addContainerGap()
		                        .addComponent(timesUp_label1))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(64, 64, 64)
		                        .addComponent(timesUp_clockLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(timesUp_clockLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(timesUp_clockLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(timesUp_clockLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(timesUp_clockLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(94, 94, 94)
		                        .addComponent(timesUp_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(timesUp_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(timesUp_clockLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(timesUp_clockLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(timesUp_clockLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(timesUp_clockLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(timesUp_clockLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addComponent(timesUp_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(16, Short.MAX_VALUE))
		        );
				break;
			/*
			 * TURN CHANGE DIALOG
			 */
			case(13):
				turnChange_jlabel_goButton = new javax.swing.JButton();
		        turnChange_jlabel_label1 = new javax.swing.JLabel();
		        turnChange_jlabel_label2 = new javax.swing.JLabel();
		        turnChange_jlabel_card2 = new javax.swing.JLabel();
		        turnChange_jlabel_card1 = new javax.swing.JLabel();
	
		        setPreferredSize(new java.awt.Dimension(640, 480));

	
		        turnChange_jlabel_goButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        turnChange_jlabel_goButton.setText("GO!");
		        turnChange_jlabel_goButton.addActionListener(this);
		        turnChange_jlabel_goButton.setActionCommand("changePlayer");
		        turnChange_jlabel_label1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
		        String text = "";
		        if(gotDraw2 || draw2SecondCard)
		        {
		        	//SAME PLAYERS TURN
		        	text = text + "Got Draw 2 Go Again " + playersArray[GAME_STATE.getPlayer()].getName() + "! ";
			        if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			        {
				        text = text + "It's a Knocked Round, Last Turn!";	
			        }
		        }
		        else
		        {
		        	//NORMAL PLAYER CHANGE
		        	if(GAME_STATE.getPlayer() == 0)
			        {
			        	text = text + "It's " + playersArray[1].getName() + " Turn! ";
			        }
			        else
			        {
			        	text = text + "It's " + playersArray[0].getName() + " Turn! ";
			        }
			        if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			        {
				        text = text + "It's a Knocked Round, Last Turn!";	
			        }
		        }
		        
		        turnChange_jlabel_label1.setText(text);
		        
		        
	
		        turnChange_jlabel_label2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		        turnChange_jlabel_label2.setText("Other Players, Don't Look!");
	
		        turnChange_jlabel_card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
	
		        turnChange_jlabel_card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(26, 26, 26)
		                .addComponent(turnChange_jlabel_card1)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(turnChange_jlabel_goButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(turnChange_jlabel_card2)
		                .addGap(28, 28, 28))
		            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                .addContainerGap(43, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addComponent(turnChange_jlabel_label1)
		                        .addGap(39, 39, 39))
		                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
		                        .addComponent(turnChange_jlabel_label2)
		                        .addGap(75, 75, 75))))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(37, 37, 37)
		                .addComponent(turnChange_jlabel_label1)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(turnChange_jlabel_label2)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(70, 70, 70)
		                        .addComponent(turnChange_jlabel_goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(18, 18, 18)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(turnChange_jlabel_card1)
		                            .addComponent(turnChange_jlabel_card2))))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
	
		        getRootPane().setDefaultButton(turnChange_jlabel_goButton);

				break;
			/*
			 * VIEW OUTSIDE CARDS
			 */
			case(14):
				viewCardStart_jlabel_card4 = new javax.swing.JLabel();
		        viewCardStart_jlabel_card3 = new javax.swing.JLabel();
		        viewCardsStart_jlabel_card2 = new javax.swing.JLabel();
		        viewCardsStart_jlabel_card1 = new javax.swing.JLabel();
		        viewCardsStart_jlabel_label1 = new javax.swing.JLabel();
		        viewCardStart_jbutton_go = new javax.swing.JButton();
	
		        viewCardStart_jlabel_card4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[GAME_STATE.getPlayer()].myHand.getCard(0) + ".png"))))); // NOI18N
		        viewCardStart_jlabel_card4.setText("jLabel1");
	
		        viewCardStart_jlabel_card3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        viewCardStart_jlabel_card3.setText("jLabel1");
	
		        viewCardsStart_jlabel_card2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR+"13.png"))))); // NOI18N
		        viewCardsStart_jlabel_card2.setText("jLabel1");
	
		        viewCardsStart_jlabel_card1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[GAME_STATE.getPlayer()].myHand.getCard(3) + ".png"))))); // NOI18N
		        viewCardsStart_jlabel_card1.setText("jLabel1");
	
		        viewCardsStart_jlabel_label1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        viewCardsStart_jlabel_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        viewCardsStart_jlabel_label1.setText(playersArray[GAME_STATE.getPlayer()].getName() + ", Remember Your Cards!");
	
		        viewCardStart_jbutton_go.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
		        viewCardStart_jbutton_go.setText("Go");
		        viewCardStart_jbutton_go.addActionListener(this);
		        viewCardStart_jbutton_go.setActionCommand("doneViewingOutsideCards");
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(34, 34, 34)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(viewCardsStart_jlabel_label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(viewCardsStart_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(viewCardsStart_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(viewCardStart_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(viewCardStart_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(93, 93, 93)
		                        .addComponent(viewCardStart_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(47, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(viewCardsStart_jlabel_card1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(viewCardStart_jlabel_card4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(viewCardsStart_jlabel_card2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(viewCardStart_jlabel_card3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(32, 32, 32)
		                .addComponent(viewCardsStart_jlabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(viewCardStart_jbutton_go, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(28, Short.MAX_VALUE))
		        );
				break;
			case(15):
				winnerFinal_jLabel_p1Name = new javax.swing.JLabel();
		        winnerFinal_jlabel_p2Name = new javax.swing.JLabel();
		        winnerFinal_jLabel_p2TotalLabel = new javax.swing.JLabel();
		        winnerFinal_jlabel_p2Total = new javax.swing.JLabel();
		        winnerFinal_jlabel_p1Total = new javax.swing.JLabel();
		        winnerFinal_jLabel_p1TotalLabel = new javax.swing.JLabel();
		        winnerFinal_jlabel_label3 = new javax.swing.JLabel();
		        winnerFinal_jButton_continue = new javax.swing.JButton();
		        winnerFinal_jLabel_winnerName = new javax.swing.JLabel();
	
		        winnerFinal_jLabel_p1Name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        winnerFinal_jLabel_p1Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jLabel_p1Name.setText("P1 Name");
	
		        winnerFinal_jlabel_p2Name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        winnerFinal_jlabel_p2Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jlabel_p2Name.setText("P2 Name");
	
		        winnerFinal_jLabel_p2TotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        winnerFinal_jLabel_p2TotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jLabel_p2TotalLabel.setText("Final Score");
	
		        winnerFinal_jlabel_p2Total.setBackground(new java.awt.Color(255, 255, 255));
		        winnerFinal_jlabel_p2Total.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        winnerFinal_jlabel_p2Total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jlabel_p2Total.setText("   P2 Total");
		        winnerFinal_jlabel_p2Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        winnerFinal_jlabel_p1Total.setBackground(new java.awt.Color(255, 255, 255));
		        winnerFinal_jlabel_p1Total.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        winnerFinal_jlabel_p1Total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jlabel_p1Total.setText("   P1 Total");
		        winnerFinal_jlabel_p1Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        winnerFinal_jLabel_p1TotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        winnerFinal_jLabel_p1TotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jLabel_p1TotalLabel.setText("Final Score");
	
		        winnerFinal_jlabel_label3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
		        winnerFinal_jlabel_label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        winnerFinal_jlabel_label3.setText("Winner:");
	
		        winnerFinal_jButton_continue.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        winnerFinal_jButton_continue.setText("New Game");
		        winnerFinal_jButton_continue.addActionListener(this);
		        winnerFinal_jButton_continue.setActionCommand("newGame");
		        
	
		        winnerFinal_jLabel_winnerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        winnerFinal_jLabel_winnerName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        winnerFinal_jLabel_winnerName.setText("Winner name");
	
		       layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(23, 23, 23)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(winnerFinal_jlabel_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(26, 26, 26)
		                        .addComponent(winnerFinal_jLabel_winnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(layout.createSequentialGroup()
		                                .addComponent(winnerFinal_jLabel_p1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(31, 31, 31)
		                                .addComponent(winnerFinal_jlabel_p2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                                .addGroup(layout.createSequentialGroup()
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                        .addComponent(winnerFinal_jlabel_p1Total, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
		                                        .addComponent(winnerFinal_jLabel_p1TotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                                    .addGap(31, 31, 31)
		                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                        .addComponent(winnerFinal_jlabel_p2Total, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                        .addComponent(winnerFinal_jLabel_p2TotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                                .addComponent(winnerFinal_jButton_continue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		                        .addGap(0, 23, Short.MAX_VALUE))))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(winnerFinal_jLabel_p1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(winnerFinal_jlabel_p2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(18, 18, 18)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(winnerFinal_jLabel_p2TotalLabel)
		                    .addComponent(winnerFinal_jLabel_p1TotalLabel))
		                .addGap(3, 3, 3)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                    .addComponent(winnerFinal_jlabel_p2Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                    .addComponent(winnerFinal_jlabel_p1Total, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(40, 40, 40)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(winnerFinal_jlabel_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(winnerFinal_jLabel_winnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(40, 40, 40)
		                .addComponent(winnerFinal_jButton_continue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(33, Short.MAX_VALUE))
		        );
				break;
			case(16):
				interRound_jLabel_p1Name = new javax.swing.JLabel();
		        interRound_jlabel_p2Name = new javax.swing.JLabel();
		        interRound_jlabel_p1Subtotal = new javax.swing.JLabel();
		        interRound_jlabel_p2Subtotal = new javax.swing.JLabel();
		        interRound_jlabel_p1Total = new javax.swing.JLabel();
		        interRound_jlabel_p2Total = new javax.swing.JLabel();
		        interRound_jLabel_p2SubtotalLabel = new javax.swing.JLabel();
		        interRound_jLabel_p1TotalLabel = new javax.swing.JLabel();
		        interRound_jLabel_p2TotalLabel = new javax.swing.JLabel();
		        interRound_jLabel_p1SubtotalLabel = new javax.swing.JLabel();
		        interRound_jLabel_rounds = new javax.swing.JLabel();
		        interRound_jLabel_label3 = new javax.swing.JLabel();
		        interRound_jButton_continue = new javax.swing.JButton();
		        interRound_jLabel_time = new javax.swing.JLabel();
	
		       
		        interRound_jLabel_p1Name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        interRound_jLabel_p1Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_p1Name.setText("P1 Name");
	
		        interRound_jlabel_p2Name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        interRound_jlabel_p2Name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jlabel_p2Name.setText("P2 Name");
	
		        interRound_jlabel_p1Subtotal.setBackground(new java.awt.Color(255, 255, 255));
		        interRound_jlabel_p1Subtotal.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        interRound_jlabel_p1Subtotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jlabel_p1Subtotal.setText("   P1 Subtotal");
		        interRound_jlabel_p1Subtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        interRound_jlabel_p2Subtotal.setBackground(new java.awt.Color(255, 255, 255));
		        interRound_jlabel_p2Subtotal.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        interRound_jlabel_p2Subtotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jlabel_p2Subtotal.setText("   P2 Subtotal");
		        interRound_jlabel_p2Subtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        interRound_jlabel_p1Total.setBackground(new java.awt.Color(255, 255, 255));
		        interRound_jlabel_p1Total.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        interRound_jlabel_p1Total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jlabel_p1Total.setText("   P1 Total");
		        interRound_jlabel_p1Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        interRound_jlabel_p2Total.setBackground(new java.awt.Color(255, 255, 255));
		        interRound_jlabel_p2Total.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
		        interRound_jlabel_p2Total.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jlabel_p2Total.setText("   P2 Total");
		        interRound_jlabel_p2Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
		        interRound_jLabel_p2SubtotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        interRound_jLabel_p2SubtotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_p2SubtotalLabel.setText("Subtotal");
	
		        interRound_jLabel_p1TotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        interRound_jLabel_p1TotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_p1TotalLabel.setText("Total");
	
		        interRound_jLabel_p2TotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        interRound_jLabel_p2TotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_p2TotalLabel.setText("Total");
	
		        interRound_jLabel_p1SubtotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
		        interRound_jLabel_p1SubtotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_p1SubtotalLabel.setText("Subtotal");
	
		        interRound_jLabel_rounds.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        interRound_jLabel_rounds.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_rounds.setText("Time / Rounds label");
	
		        interRound_jLabel_label3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        interRound_jLabel_label3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_label3.setText("Time Left to Play");
	
		        interRound_jButton_continue.setText("Continue");
		        interRound_jButton_continue.addActionListener(this);
		        interRound_jButton_continue.setActionCommand("nextRound");
		        
	
		        interRound_jLabel_time.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        interRound_jLabel_time.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		        interRound_jLabel_time.setText("Time / Rounds label");
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                        .addGroup(layout.createSequentialGroup()
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                .addComponent(interRound_jlabel_p1Subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                .addComponent(interRound_jlabel_p1Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                .addComponent(interRound_jLabel_p1TotalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                .addComponent(interRound_jLabel_p1SubtotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
		                            .addGap(31, 31, 31)
		                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addComponent(interRound_jLabel_p2SubtotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addComponent(interRound_jlabel_p2Total, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addComponent(interRound_jlabel_p2Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addComponent(interRound_jLabel_p2TotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                        .addGroup(layout.createSequentialGroup()
		                            .addComponent(interRound_jLabel_p1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addGap(31, 31, 31)
		                            .addComponent(interRound_jlabel_p2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGroup(layout.createSequentialGroup()
		                            .addComponent(interRound_jLabel_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(interRound_jButton_continue, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addComponent(interRound_jLabel_time, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addComponent(interRound_jLabel_rounds, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(interRound_jLabel_p1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(interRound_jlabel_p2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(interRound_jLabel_p1SubtotalLabel)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(interRound_jlabel_p1Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(interRound_jlabel_p2Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(interRound_jLabel_p2SubtotalLabel)
		                        .addGap(66, 66, 66)
		                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(interRound_jLabel_p2TotalLabel)
		                            .addComponent(interRound_jLabel_p1TotalLabel))))
		                .addGap(3, 3, 3)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
		                    .addComponent(interRound_jlabel_p2Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                    .addComponent(interRound_jlabel_p1Total, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(10, 10, 10)
		                        .addComponent(interRound_jButton_continue, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addComponent(interRound_jLabel_label3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(interRound_jLabel_time, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addComponent(interRound_jLabel_rounds, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(40, 40, 40))
		        );
				break;
			case(17):
				loading_jLabel_label1 = new javax.swing.JLabel();
		        loading_jlabel_gif = new javax.swing.JLabel();
	
		        
		        loading_jLabel_label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
		        loading_jLabel_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        loading_jLabel_label1.setText("Wait for the computer to play");
	
		        loading_jlabel_gif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		        loading_jlabel_gif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/Cards/ajax-loader.gif"))); // NOI18N
	
		        layout = new javax.swing.GroupLayout(getContentPane());
		        getContentPane().setLayout(layout);
		        layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(24, 24, 24)
		                        .addComponent(loading_jLabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
		                    .addGroup(layout.createSequentialGroup()
		                        .addGap(138, 138, 138)
		                        .addComponent(loading_jlabel_gif, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addContainerGap(31, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(loading_jLabel_label1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addComponent(loading_jlabel_gif, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
		                .addGap(18, 18, 18))
		        );
				break;
		}
		
	}
        
        // TurnChangeDailog
	
	public void clearGUI()
	{
		//this method should be called to remove everything from the frame
		//every time we switch from main menu to game screen or what ever
		//we need to clear and remove everything from the last view
		getContentPane().removeAll();
	}
	
	public void swap()
	{
		int othersIndex = swapOpp_jcombobox_oppSelect.getSelectedIndex();
        int myIndex = swapOpp_jcombobox_playerSelect.getSelectedIndex();
		
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
		cardFromDeck = mainDeck.getCard(0); //---------------CHANGED FROM GETCARD(0)

		if(cardFromDeck.isSpecial())
		{
			discard.addTopCard(cardFromDeck);
			if(cardFromDeck.getSpecial() == "swap")
			{
				System.out.println("---------------------------------I GOT A SWAP CARD");
				gotDraw2 = false;
				draw2SecondCard = false;
				setUpGUI(8);
			}
			else if(cardFromDeck.getSpecial() == "peek")
			{
				System.out.println("---------------------------------I GOT A PEEK CARD");
				gotDraw2 = false;
				draw2SecondCard = false;
				setUpGUI(5);
			}
			else if(cardFromDeck.getSpecial() == "draw2")
			{
				System.out.println("---------------------------------I GOT A DRAW2 CARD");
				gotDraw2 = true;
				draw2SecondCard = true;
				discardCardFromDeck();
				setUpGUI(0);
			}
		}
		else
		{
			gotDraw2 = false;
			draw2SecondCard = false;
			System.out.println("---------------------------------I GOT A NORMAL CARD");
			//NORMAL CARD WE WANT TO REPLACE
			System.out.println("++++++++++++++++++++++++++++++++++ REPLACE CARD FROM DECK: " + swapDrawnCard_jcombobox_select1.getSelectedIndex());
			replaceCardFromDeck(swapDrawnCard_jcombobox_select1.getSelectedIndex());
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
	
	public void replaceCardFromDeck(int card)
	{
		System.out.println("REAPLCING CARD IN HAND FROM DECK");
		//System.out.println("You got an " + cardFromDeck.toString());
		
		
		Card cardFromDeck = mainDeck.getTopCard();
		Card oldCard = playersArray[GAME_STATE.getPlayer()].myHand.replaceCard(card, cardFromDeck);
		System.out.println("CARD I DONT WANT:" + oldCard.getRank());
		discard.addTopCard(oldCard);
		
		System.out.println("NEW CARD I GOT FROM DECK: " + cardFromDeck.getRank());
		
		System.out.println("NEW TOP DISCARD:" + discard.getCard(0).getRank());
	}
	
	public void replaceCardFromDiscard(int card)
	{
		System.out.println("REAPLCING CARD IN HAND FROM DISCARD");
		//System.out.println("You got an " + cardFromDeck.toString());
		
		Card cardFromDiscard = discard.getTopCard();
		Card oldCard = playersArray[GAME_STATE.getPlayer()].myHand.replaceCard(card, cardFromDiscard);
		discard.addTopCard(oldCard);
	}
	
	
	public void draw2()
	{
		System.out.println("--------------------------------------------WE GOT A DRAW2");
		gotDraw2 = true;
	}
	
	public void processAI()
	{
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("---------------------------------PROCESSING AI PLAYER TURN------------------------");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------");
		
	
		//Decide From Deck Or Discard
		int rand = -1;
		boolean chooseDiscard = false;
		switch(GAME_STATE.getDifficulty())
		{
			case(0):
				//EASY AI
				System.out.println("DECIDING DRAW OR DISCARD AI EASY FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				rand = generator.nextInt(2);
				break;
			case(1):
				//MEIDUM AI
				System.out.println("DECIDING DRAW OR DISCARD AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				chooseDiscard = false;
				for(int i = 0; i < 4; i++)
				{
					if(generator.nextInt(2) == 0)
					{
						//WE HAVE REMEBERED THAT CARD AND CAN COMPAIR WITH DISCARD
						if(discard.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank())
						{
							chooseDiscard = true;
						}
					}
					else
					{
						//WE DID NOT REMEBER THAT CARD THUS WILL NOT INFLUENCE OUR DESICION
					}
					
				}
				if(chooseDiscard)
				{
					rand = 1;
				}
				else
				{
					rand = 0;
				}
				break;
			case(2):
				//HARD AI
				System.out.println("DECIDING DRAW OR DISCARD AI HARD FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				chooseDiscard = false;
				for(int i = 0; i < 4; i++)
				{
					if(discard.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank())
					{
						chooseDiscard = true;
					}
				}
				if(chooseDiscard)
				{
					rand = 1;
				}
				else
				{
					rand = 0;
				}
				break;
		}
		
		//check if discard is special so we cannot draw from it...
		if(discard.getCard(0).isSpecial())
		{
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("------ DISCARD IS SPACIAL CANNOT DRAW FROM IT--------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			rand = 0;			
		}

		//CHOOSE DRAW FROM DECK OR DISCARD
		if(rand == 0)
		{
			System.out.println("------ WE WANT TO DRAW FROM THE DECK---------");
			//DRAW FROM DECK
			boolean foundBetterCard = false;
			switch(GAME_STATE.getDifficulty())
			{
				case(0):
					//EASY AI
					System.out.println("DECIDING KEEP OR GIVE UP CARD AI EASY FOR " + playersArray[GAME_STATE.getPlayer()].getName());
					rand = generator.nextInt(2);
					break;
				case(1):
					//MEIDUM AI
					System.out.println("DECIDING KEEP OR GIVE UP CARD AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());
					for(int i = 0; i < 4; i++)
					{
						if(generator.nextInt(2) == 0)
						{
							//WE HAVE REMEBERED THAT CARD AND CAN COMPAIR WITH DISCARD
							if(mainDeck.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank())
							{
								foundBetterCard = true;
							}
						}
						else
						{
							//WE DID NOT REMEBER THAT CARD THUS WILL NOT INFLUENCE OUR DESICION
						}
						
					}
					if(foundBetterCard)
					{
						//WE WAN TO KEEP THE CARD
						rand = 0;
					}
					else
					{
						//DISCARD NOT BETTER THAN ANY IN OUR HAND
						rand = 1;
					}
					
					//HANDLE SPACIAL CARDS
					if(mainDeck.getCard(0).isSpecial() && mainDeck.getCard(0).getSpecial() != "draw2")
					{
						//IF ITS A SPECIAL THATS NOT DRAW 2 WE DONT CARE, THEY ARE GOOD ENOUGH AS IT IS!
						rand = 1;
					}
					else if(mainDeck.getCard(0).getSpecial() == "draw2")
					{
						rand = 0;
					}
					break;
				case(2):
					//HARD AI
					System.out.println("DECIDING KEEP OR GIVE UP CARD AI HARD FOR " + playersArray[GAME_STATE.getPlayer()].getName());
					for(int i = 0; i < 4; i++)
					{
						//WE HAVE REMEBERED THAT CARD AND CAN COMPAIR WITH DISCARD
						if(mainDeck.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank())
						{
							foundBetterCard = true;
						}	
					}
					if(foundBetterCard)
					{
						//WE WAN TO KEEP THE CARD
						rand = 0;
					}
					else
					{
						//DISCARD NOT BETTER THAN ANY IN OUR HAND
						rand = 1;
					}
					
					//HANDLE SPACIAL CARDS
					if(mainDeck.getCard(0).isSpecial() && mainDeck.getCard(0).getSpecial() != "draw2")
					{
						//IF ITS A SPECIAL THATS NOT DRAW 2 WE DONT CARE, THEY ARE GOOD ENOUGH AS IT IS!
						rand = 1;
					}
					else if(mainDeck.getCard(0).getSpecial() == "draw2")
					{
						rand = 0;
					}
					break;
			}
			
			//KEEP OR GIVE AWAY DRAWN CARD
			if(rand == 0)
			{
				System.out.println("------ WE WANT TO KEEP THE CARD---------");
				
				gotDraw2 = false;
				draw2SecondCard = false;
				
				//WE WANT TO KEEP THE CARD
				if(mainDeck.getCard(0).isSpecial())
				{
					//WE DREW A SPECIAL CARD!
					/*
					 * SPEACL LOGIC HERE
					 * SPEACL LOGIC HERE
					 * SPEACL LOGIC HERE
					 * SPEACL LOGIC HERE
					 * SPEACL LOGIC HERE
					 * 
					 */
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("----------------------------------- AI: Keep Special Card");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
				}
				else
				{							
					//WE DREW A NORMAL CARD!
					int cardToReplace = -1;
					int highestCard = -1;
					switch(GAME_STATE.getDifficulty())
					{
						case(0):
							//EASY AI
							System.out.println("KEEP CARD AI EASY FOR " + playersArray[GAME_STATE.getPlayer()].getName());
							rand = generator.nextInt(4);
							break;
						case(1):
							//MEIDUM AI
							System.out.println("KEEP CARD AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());
	
							for(int i = 0; i < 4; i++)
							{
								if(generator.nextInt(2) == 0)
								{
									if(mainDeck.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() && mainDeck.getCard(0).getRank() < highestCard)
									{
										cardToReplace = i;
										highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
									}
								}
							}
							for(int i = 0; i < 4; i++)
							{
								if(generator.nextInt(2) == 0)
								{
									if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).isSpecial())
									{
										cardToReplace = i;
										highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
									}
								}
							}
							
							
							
							
							if(cardToReplace != -1)
							{
								//WE WANT TO REPLACE THE ONE WE FOUND THATS BAD
								rand = cardToReplace;
							}
							else
							{
								//NOT SURE IF WE'D END UP HERE BUT REPLACE CARD ZERO IF WE DO...
								rand = 0;
							}
							break;
						case(2):
							//HARD AI
							System.out.println("KEEP CARD AI HARD FOR " + playersArray[GAME_STATE.getPlayer()].getName());
							for(int i = 0; i < 4; i++)
							{
								if(mainDeck.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() && mainDeck.getCard(0).getRank() < highestCard)
								{
									cardToReplace = i;
									highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
								}
							}
							for(int i = 0; i < 4; i++)
							{
								if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).isSpecial())
								{
									cardToReplace = i;
									highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
								}
							}
							
							
							if(cardToReplace != -1)
							{
								//WE WANT TO REPLACE THE ONE WE FOUND THATS BAD
								rand = cardToReplace;
							}
							else
							{
								//NOT SURE IF WE'D END UP HERE BUT REPLACE CARD ZERO IF WE DO...
								rand = 0;
							}
							break;
					}
										
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("--------- AI: Keep Normal Card, REAPLCING CARD: " + rand);
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					System.out.println("-----------------------------------------------------------");
					
					//CHOOSE THE HAND CARD WE WANT TO REPLACE WITH
					replaceCardFromDeck(rand);
					
				}						
			}
			else
			{
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("----------------------------------- AI: Dont Keep Card Card");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				
				draw2SecondCard = false;
				//WE DONT CARE GIVE UP THE CARD
				discardCardFromDeck();
			}
		}
		else
		{					
			System.out.println("------ WE WANT TO DRAW FROM THE DISCARD---------");
			//DRAW FROM DISCARD
			int cardToReplace = -1;
			int highestCard = -1;
			switch(GAME_STATE.getDifficulty())
			{
				case(0):
					//EASY AI
					System.out.println("REPLACING CARD FROM DISCARD  AI EASY FOR " + playersArray[GAME_STATE.getPlayer()].getName());
					rand = generator.nextInt(4);
					break;
				case(1):
					//MEIDUM AI
					System.out.println("REPLACING CARD FROM DISCARD  AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());

					for(int i = 0; i < 4; i++)
					{
						if(generator.nextInt(2) == 0)
						{
							if(discard.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() && discard.getCard(0).getRank() < highestCard)
							{
								cardToReplace = i;
								highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
							}
						}
					}
					for(int i = 0; i < 4; i++)
					{
						if(generator.nextInt(2) == 0)
						{
							if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).isSpecial())
							{
								cardToReplace = i;
								highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
							}
						}
					}
				
				
					if(cardToReplace != -1)
					{
						//WE WANT TO REPLACE THE ONE WE FOUND THATS BAD
						rand = cardToReplace;
					}
					else
					{
						//CHOOSE THE HIGHEST VALUE CARD IN OUR HAND TO REPLACE BEACUSE WE HAVE TO..
						int highestValue = -1;
						for(int i = 0; i < 4; i++)
						{
							if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() > highestValue)
							{
								highestValue = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
								rand = i;
							}
						}
					}
					break;
				case(2):
					//HARD AI
					System.out.println("REPLACING CARD FROM DISCARD AI HARD FOR " + playersArray[GAME_STATE.getPlayer()].getName());
					for(int i = 0; i < 4; i++)
					{
						if(discard.getCard(0).getRank() < playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() && discard.getCard(0).getRank() < highestCard)
						{
							cardToReplace = i;
							highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
						}
					}
					for(int i = 0; i < 4; i++)
					{
						if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).isSpecial())
						{
							cardToReplace = i;
							highestCard = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
						}
					}
				
					if(cardToReplace != -1)
					{
						//WE WANT TO REPLACE THE ONE WE FOUND THATS BAD
						rand = cardToReplace;
					}
					else
					{
						//CHOOSE THE HIGHEST VALUE CARD IN OUR HAND TO REPLACE BEACUSE WE HAVE TO..
						int highestValue = -1;
						for(int i = 0; i < 4; i++)
						{
							if(playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank() > highestValue)
							{
								highestValue = playersArray[GAME_STATE.getPlayer()].myHand.getCard(i).getRank();
								rand = i;
							}
						}
					}
					break;
			}
			//CHOOSE THE HAND CARD WE WANT TO REPLACE WITH
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("----------- AI: Draw Discard Card, REAPLCING CARD: " + rand);
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			System.out.println("-----------------------------------------------------------");
			
			replaceCardFromDiscard(rand);
		}
		
		//KNOCK OR KEEP PLAYING
		boolean shouldKnock = false;
		switch(GAME_STATE.getDifficulty())
		{
			case(0):
				//EASY AI
				System.out.println("DECIDING KNOCK AI EASY FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				rand = generator.nextInt(2);
				if(rand == 0)
				{
					shouldKnock = true;
				}
				break;
			case(1):
				//MEIDUM AI
				shouldKnock = false;
				System.out.println("DECIDING KNOCK AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				if(generator.nextInt(2) == 0)
				{
					//WE HAVE REMEBERED THAT CARD AND CAN COMPAIR WITH DISCARD
					if(GAME_STATE.getPlayer() == 0)
					{
						if(playersArray[0].myHand.getScore() < playersArray[1].myHand.getScore())
						{
							//I HAVE BETTER SCORE KNOCK!!!
							shouldKnock = true;
						}
						else
						{
							shouldKnock = false;
						}
					}
					else
					{
						if(playersArray[1].myHand.getScore() < playersArray[0].myHand.getScore())
						{
							//I HAVE BETTER SCORE KNOCK!!!
							shouldKnock = true;
						}
						else
						{
							shouldKnock = false;
						}
					}
				}
				else
				{
					//WE DID NOT REMEBER AND CANNOT REMEBER!
				}
				break;
			case(2):
				shouldKnock = false;
				System.out.println("DECIDING KNOCK AI MEDIUM FOR " + playersArray[GAME_STATE.getPlayer()].getName());
				if(generator.nextInt(2) == 0)
				{
					//WE HAVE REMEBERED THAT CARD AND CAN COMPAIR WITH DISCARD
					if(GAME_STATE.getPlayer() == 0)
					{
						if(playersArray[0].myHand.getScore() < playersArray[1].myHand.getScore())
						{
							//I HAVE BETTER SCORE KNOCK!!!
							shouldKnock = true;
						}
						else
						{
							shouldKnock = false;
						}
					}
					else
					{
						if(playersArray[1].myHand.getScore() < playersArray[0].myHand.getScore())
						{
							//I HAVE BETTER SCORE KNOCK!!!
							shouldKnock = true;
						}
						else
						{
							shouldKnock = false;
						}
					}
				}
				else
				{
					//WE DID NOT REMEBER AND CANNOT REMEBER!
				}
				break;
		}
		
		if(shouldKnock && GAME_STATE.getStatus() != KNOCKED_ROUND)
		{
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("--------------------------------- AI PLAYER IS KNOCKING --------------------------");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("----------------------------------------------------------------------------------");
			
			playerKnocked();
		}
		
		//EVERYTHING IS DONE CHANGE PLAYERS AND LOAD MAIN GUI
		changePlayer();
	}
	
	public void initGame()
	{		
		//CREATE GAME STATE
		GAME_STATE = new gameState(2);	
		
		startTime = new LocalTime();
		endTime = startTime.plusMinutes(6); //HAS TO BE 1 MINUTE LONGER THAN WE WANT!!!!
		
    	System.out.println("***START TIME: " + startTime + "| END TIME: " + endTime.minusMinutes(1));
		
		//CREATE UNIQUE GAME ID
		uniqueID = UUID.randomUUID();
		
		//PLAYERS ARRAY
		playersArray = new Player[2];
		
		//HUMAN PLAYER
		Hand humanHand = new Hand(0);
		Player human = new Player(true, 0, instructions_jtextfield_p1NameInput.getText() , humanHand);
		playersArray[0] = human;
		
		//COMPUTER OPPONENTS
		String[] opponents = new String[5];
		opponents[0] = "Jimmy";
		opponents[1] = "Einstein";
		opponents[2] = "God";
		
		//COMPUTER PLAYER
		Hand computerHand = new Hand(1);
		Player computer = new Player(false, 1, opponents[instructions_jcombobox_difficulty.getSelectedIndex()], computerHand);
		playersArray[1] = computer;
		System.out.println("You are playing " + opponents[instructions_jcombobox_difficulty.getSelectedIndex()]);
		
		for(int i = 0; i < 2; i++)
			System.out.println("PLAYER " + i + ": " + playersArray[i].getHumanity());
		
		//UPDATE GAME STATE
		GAME_STATE.setDifficulty(instructions_jcombobox_difficulty.getSelectedIndex());
		GAME_STATE.updateGameState(INSTRUCTIONS, instructions_jcombobox_rounds.getSelectedIndex(), 0, NORMAL_PLAY, GAME_STATE.getRoundNum());
		
		//SETUP GAME WITH -2 ROUNDS SO WE CAN SHOW THEIR OUTSIDE CARDS FIRST
		GAME_STATE.setRoundNum(0);
		
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
		chooseDeck = false;
		
		
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


		
		printDebugLog();
		
		//SET FIRST PLAYER TO GO FIRST!
		GAME_STATE.setPlayer(0);
		
		//Clear Player who knocked
		GAME_STATE.setPlayerWhoKnocked(-1);
		
		if(debug)
		{
			GAME_STATE.print();
		}
	
		//UPDATE SERVER
		if(sendJSON)
		{
			sendJSON();
		}
		
		if(debug)
		{
			System.out.println("********************************");
			System.out.println("********************************");
			System.out.println("********************************");
			System.out.println("********************************");
			System.out.println("ROUND STARTED - GAME STATE: ");
			GAME_STATE.print();
			System.out.println("********************************");
			System.out.println("********************************");
			System.out.println("********************************");
			System.out.println("********************************");
		}
	}
	
	public void printDebugLog()
	{
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
			System.out.println("SHOWING THE GAME STATE STATUS");
			GAME_STATE.print();
			System.out.println("********************************");
			System.out.println("SHOWING THE REMAINING DECK");
			System.out.println("********************************");
			mainDeck.showDeck();
			System.out.println("********************************");
		}
	}
	
	public void updateGameDialog()
	{
		String text = "Current Player: " + playersArray[GAME_STATE.getPlayer()].getName() + "\n";
		text = text + "----------Current Round: " + GAME_STATE.getRoundNum() + "\n";
		text = text + "-Player 1's Hand - | - Players 2's Hand -\n";
		
		for(int i = 0; i < 4; i++)
		{
			text = text + "Card " + i + ": " + playersArray[GAME_STATE.getPlayer()].myHand.getCard(i) + "\t  | ";
			if(GAME_STATE.getPlayer() == 0)
			{
				text = text + "Card " + i + ": " + playersArray[1].myHand.getCard(i) + "\n";
			}
			else
			{
				text = text + "Card " + i + ": " + playersArray[0].myHand.getCard(i) + "\n";
			}
		}
		text = text + "-----GAME STATE-----\n";
		text = text + GAME_STATE.returnGameState();
		text = text + "-----DECK-----\n";
		for(int i = 0; i < mainDeck.size(); i++)
		{
			if(mainDeck.getCard(i).isSpecial())
			{
				text = text + "Card " + i + ": " + mainDeck.getCard(i).getSpecial() + "\n";
			}
			else
			{
				text = text + "Card " + i + ": " + mainDeck.getCard(i).getRank() + "\n";
			}
		}
		mainGUI_jtextarea_descripTextarea.setText(text);
	}
	
	public void showCardHand()
	{
		//PLAYER 1
		if(playersArray[0].myHand.getCard(0).getRank() == -1)
		{
			mainGUI_jlabel_myHandCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(0).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_myHandCard1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(0).getRank() + ".png")))));
		}
		if(playersArray[0].myHand.getCard(1).getRank() == -1)
		{
			mainGUI_jlabel_myHandCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(1).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_myHandCard2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(1).getRank() + ".png")))));
		}
		if(playersArray[0].myHand.getCard(2).getRank() == -1)
		{
			mainGUI_jlabel_myHandCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(2).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_myHandCard3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(2).getRank() + ".png")))));
		}
		if(playersArray[0].myHand.getCard(3).getRank() == -1)
		{
			mainGUI_jlabel_myHandCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(3).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_myHandCard4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[0].myHand.getCard(3).getRank() + ".png")))));
		}
		
		//PLAYER 2
		if(playersArray[1].myHand.getCard(0).getRank() == -1)
		{
			mainGUI_jlabel_oppCardLabel1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(0).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_oppCardLabel1.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(0).getRank() + ".png")))));
		}
		if(playersArray[1].myHand.getCard(1).getRank() == -1)
		{
			mainGUI_jlabel_oppCardLabel2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(1).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_oppCardLabel2.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(1).getRank() + ".png")))));
		}
		if(playersArray[1].myHand.getCard(2).getRank() == -1)
		{
			mainGUI_jlabel_oppCardLabel3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(2).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_oppCardLabel3.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(2).getRank() + ".png")))));
		}
		if(playersArray[1].myHand.getCard(3).getRank() == -1)
		{
			mainGUI_jlabel_oppCardLabel4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(3).getSpecial() + ".png")))));
		}
		else
		{
			mainGUI_jlabel_oppCardLabel4.setIcon(new javax.swing.ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource(BASEDIR + playersArray[1].myHand.getCard(3).getRank() + ".png")))));
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
	
	public void replacePowerCardsInHands()
	{
		for(int i = 0; i < GAME_STATE.numPlayers(); i++)
		{
			//CHECK FOR REMAINING POWER CARDS
			for(int j = 0; j < 4; j++)
			{
				if(playersArray[i].myHand.getCard(j).isSpecial())
				{
					//CHECK IF DECK IS EMPTY
					if(mainDeck.size() == 0)
					{
						rebuildEmptyDeck();
					}

					//swap out card
					Card cardFromDeck = mainDeck.getTopCard();
					//check if we got another power card!
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
	
	public void updatePlayersTotalScore()
	{
		playersArray[0].setTotalScore(playersArray[0].myHand.getScore() + playersArray[0].getTotalScore());
		playersArray[1].setTotalScore(playersArray[1].myHand.getScore() + playersArray[1].getTotalScore());
		System.out.println("PLAYER 0 TOTAL SCORE:" + playersArray[0].getTotalScore());
		System.out.println("PLAYER 1 TOTAL SCORE:" + playersArray[1].getTotalScore());
	}
	
	public int checkWhoWonRound()
	{
		int winner = -1;
		int winningScore = 1000;
		if(playersArray[0].myHand.getScore() < winningScore)
		{
			winner = 0;
			winningScore = playersArray[0].myHand.getScore();
		}
		if(playersArray[1].myHand.getScore() < winningScore)
		{
			winner = 1;
			winningScore = playersArray[1].myHand.getScore();
		}
		if(playersArray[0].myHand.getScore() == playersArray[1].myHand.getScore())
		{
			winner = -2; //TIE!
		}
		
		//UPDATE THEIR WIN COUNTER
		playersArray[winner].setRoundsWon(playersArray[winner].getRoundsWon() + 1);
		
		return winner;
	}
	
	public int checkWhoWonGame()
	{
		int winner = -1;
		int winningScore = 1000;
		if(playersArray[0].getTotalScore() < winningScore)
		{
			winner = 0;
			winningScore = playersArray[0].getTotalScore();
		}
		if(playersArray[1].getTotalScore() < winningScore)
		{
			winner = 1;
			winningScore = playersArray[1].getTotalScore();
		}
		if(playersArray[0].getTotalScore() == playersArray[1].getTotalScore())
		{
			winner = -2; //TIE!
		}
		return winner;
	}
	
	public boolean checkIfGameTimedOut()
	{
		LocalTime currentTime = new LocalTime();
    	Minutes Interval = Minutes.minutesBetween(currentTime, endTime);
    	Minutes minInterval = Minutes.minutes(1);

    	System.out.println("THE CURRENT TIME IS: " + currentTime + " | END TIME: " + endTime.minusMinutes(1));
    	
    	if(Interval.isLessThan(minInterval))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}

	}
	
	public boolean checkIfHighScoreReached()
	{
		if(playersArray[0].getTotalScore() >= 100 || playersArray[1].getTotalScore() >= 100)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	
	public boolean checkRoundOver()
	{
		System.out.println("PLAYER WHO KNOCKED: " + GAME_STATE.getPlayerWhoKnocked());
		System.out.println("CURRENT PLAYER: " + GAME_STATE.getPlayer());
		System.out.println("GAME STATUS: " + GAME_STATE.getStatus());
		
		//CHECKING IF GAME IS OVER!
		if(GAME_STATE.getStatus() == KNOCKED_ROUND && GAME_STATE.getPlayer() != GAME_STATE.getPlayerWhoKnocked())
		{
			//THE GAME IS OVER ALL PLAYERS HAVE HAD THEIR FINAL TURN!
			GAME_STATE.updateGameState(ROUND_OVER, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode(), GAME_STATE.getRoundNum() + 1);
			System.out.println("THE ROUND IS OVER");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void discardCardFromDeck()
	{
		Card cardFromDeck = mainDeck.getTopCard();
		discard.addTopCard(cardFromDeck);
	}
	
	public void changePlayer()
	{
		if(GAME_STATE.getStatus() == INSTRUCTIONS)
		{
			System.out.println("================================ INSTUCTIONS ==================");
			//CHANGING TURNS FOR INITIAL PEEK
			//GAME NOT YET OVER KEEP PLAYING
			if(GAME_STATE.getPlayer() == 0)
			{
				GAME_STATE.setPlayer(1);
			}
			else
			{
				GAME_STATE.setPlayer(0);
			}	
		}
		else
		{
			//NORMAL TURN CHANGE
			//CHECK IF DECK IS EMPTY
			if(mainDeck.size() == 0)
			{
				rebuildEmptyDeck();
			}
			if(!checkRoundOver())
			{			
				//GAME NOT YET OVER KEEP PLAYING
				if(GAME_STATE.getPlayer() == 0)
				{
					GAME_STATE.setPlayer(1);
				}
				else
				{
					GAME_STATE.setPlayer(0);
				}	
				
				//LOAD CORRECT GUI
				if(checkIfGameOver())
				{
					//Update Total Scoring
					updatePlayersTotalScore();
					int gameWinner = checkWhoWonGame();
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("------------------GAME WINNER------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					if(gameWinner == -2)
					{
						//TIE GAME
						System.out.println(playersArray[0].getName() + " TIED with " + playersArray[0].getTotalScore() + " points");
						System.out.println(playersArray[1].getName() + " TIED with " + playersArray[1].getTotalScore() + " points");
					}
					else
					{
						System.out.println(playersArray[gameWinner].getName() + " WON with " + playersArray[gameWinner].getTotalScore() + " points");
						if(gameWinner == 0)
						{
							System.out.println(playersArray[1].getName() + " LOST with " + playersArray[1].getTotalScore() + " points");
						}
						else
						{
							System.out.println(playersArray[0].getName() + " LOST with " + playersArray[0].getTotalScore() + " points");
						}
					}
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");			
					
					//SHOW FINAL SCORS
					setUpGUI(15);
				}
				else
				{
					if(playersArray[GAME_STATE.getPlayer()].getHumanity() == false)
					{
						//NEXT TURN IS AI TURN
						processAI();
					}
					else
					{
						//NEXT TURN IS HUMAN TURN
						setUpGUI(0);
					}	
				}
			}
			else
			{
				//Get Rid of Any power cards remaining in hands
				replacePowerCardsInHands();
				
				if(checkIfGameOver())
				{
					//Update Total Scoring
					int gameWinner = checkWhoWonGame();
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("------------------GAME WINNER------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					if(gameWinner == -2)
					{
						//TIE GAME
						System.out.println(playersArray[0].getName() + " TIED with " + playersArray[0].getTotalScore() + " points");
						System.out.println(playersArray[1].getName() + " TIED with " + playersArray[1].getTotalScore() + " points");
					}
					else
					{
						System.out.println(playersArray[gameWinner].getName() + " WON with " + playersArray[gameWinner].getTotalScore() + " points");
						if(gameWinner == 0)
						{
							System.out.println(playersArray[1].getName() + " LOST with " + playersArray[1].getTotalScore() + " points");
						}
						else
						{
							System.out.println(playersArray[0].getName() + " LOST with " + playersArray[0].getTotalScore() + " points");
						}
					}
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					updatePlayersTotalScore();
					
					//SHOW FINAL SCORES
					setUpGUI(15);
				}
				else
				{
					//Update Total Scoring
					updatePlayersTotalScore();
					
					//Check who won round
					int roundWinner = checkWhoWonRound();
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("------------------ROUND WINNER-----------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					if(roundWinner == -2)
					{
						System.out.println(playersArray[0].getName() + " TIED with " + playersArray[0].myHand.getScore() + " points");
						System.out.println(playersArray[1].getName() + " TIED with " + playersArray[1].myHand.getScore() + " points");
					}
					else
					{
						System.out.println(playersArray[roundWinner].getName() + " WON with " + playersArray[roundWinner].myHand.getScore() + " points");
						if(roundWinner == 0)
						{
							System.out.println(playersArray[1].getName() + " LOST with " + playersArray[1].myHand.getScore() + " points");
						}
						else
						{
							System.out.println(playersArray[0].getName() + " LOST with " + playersArray[0].myHand.getScore() + " points");
						}
					}
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					System.out.println("-----------------------------------------------");
					
					//SHOW ROUND SCORES
					setUpGUI(16);	
				}
			}
		}
		printDebugLog();
	}
	
	public boolean checkIfGameOver()
	{
		if(GAME_STATE.getWinCon() == NUM_ROUNDS)
		{
			if(GAME_STATE.getRoundNum() == 5)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(GAME_STATE.getWinCon() == TIMED_PLAY)
		{
			if(checkIfGameTimedOut())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(GAME_STATE.getWinCon() == HIGH_SCORE)
		{
			if(checkIfHighScoreReached())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			//SHOW NEVER EVER FALL UNDER THIS RETURN STATMENT
			System.out.println("SOMETHING GOT TERRIBLY WRONG!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return false;
		}
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
		setUpGUI(1);
	}
	
	public void playerKnocked()
	{
		GAME_STATE.setPlayerWhoKnocked(GAME_STATE.getPlayer());
		GAME_STATE.updateGameState(KNOCKED_ROUND, GAME_STATE.getWinCon(), GAME_STATE.getPlayer(), GAME_STATE.getMode(), GAME_STATE.getRoundNum());
	}

	
	/* This method is automatically called after the browser calls the init method.
	It is also called whenever the user returns to the page containing the applet
	after having gone off to other pages.*/
	public void start()
	{
		//POSSIBLY A PLACE TO ASK THE SERVER FOR THE USERS NAME
		
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
		if("play".equals(e.getActionCommand()))
		{
			if(debug)
			{
				System.out.println("Ive CLICKED PLAY WITH GAME MODE: " + instructions_jcombobox_rounds.getSelectedIndex() + " | and difficulty : " + instructions_jcombobox_difficulty.getSelectedIndex());	
			}
	        //INIT GAME
			initGame();
			
			initRound();
			
			//SETUP GUI
			setUpGUI(14);
		}
		if("doneViewingOutsideCards".equals(e.getActionCommand()))
		{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++CURRENT PLAYER BEOFRE: " + GAME_STATE.getPlayer());
			if(GAME_STATE.getPlayer() == 0)
			{
				//DO NOT SHOW PLAYERS 2 OUTSIDS CARDS
				GAME_STATE.setStatus(NORMAL_ROUND);
				setUpGUI(0);
			}
		}
		
		if("drawDeck".equals(e.getActionCommand()))
		{
			System.out.println("Want From Deck");
			chooseDeck = true;
			setUpGUI(2);
		}
		if("drawDiscard".equals(e.getActionCommand()))
		{
			gotDraw2 = false;
			draw2SecondCard = false;
			System.out.println("Want From Discard");
			chooseDeck = false;
			setUpGUI(10); 
		}
		
		//KNOCK OR STAY
		if("knock".equals(e.getActionCommand()))
		{
			System.out.println("knock");
			playerKnocked();
			setUpGUI(13);
		}
		if("keepPlaying".equals(e.getActionCommand()))
		{
			System.out.println("keep playing");
			setUpGUI(13);
		}
		if("keepCard".equals(e.getActionCommand()))
		{
			draw2SecondCard = false;
			gotDraw2 = false;
			System.out.println("keep the card I picked");
			setUpGUI(10);
			drawFromDeck();
		}
		if("giveUpCard".equals(e.getActionCommand()))
		{
			if(gotDraw2) //FIRST DRAW 2 CARD
			{
				gotDraw2 = false;
			}
			else if(draw2SecondCard && !gotDraw2) //SECOND DRAW 2 CARD
			{
				draw2SecondCard = false;
			}
			System.out.println("Discarding card to discard pile");
			discardCardFromDeck();
			if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			{
				changePlayer();
			}
			else
			{
				setUpGUI(4); //ASK IF KNOCK OR KEEP PLAYING
			}
		}
		if("chooseCardInHandToReplace".equals(e.getActionCommand()))
		{
			System.out.println("replacing card from my hand");
			System.out.println("++++++++++++++++++++++++++++++++++ REPLACE CARD FROM DISCARD: " + swapDrawnCard_jcombobox_select1.getSelectedIndex());
			replaceCardFromDiscard(swapDrawnCard_jcombobox_select1.getSelectedIndex());
			if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			{
				changePlayer();
			}
			else
			{
				setUpGUI(4); //ASK IF KNOCK OR KEEP PLAYING
			}
		}
		if("changePlayer".equals(e.getActionCommand()))
		{
			if(gotDraw2 || draw2SecondCard)
			{
				//DONT CHANGE PLAYERS
				setUpGUI(0);
			}
			else
			{
				//CHANGE PLAYERS NORMALLY
				changePlayer();
			}
		}
		if("peekCard".equals(e.getActionCommand()))
		{
			discardCardFromDeck();
			cardToPeek = peekInterface_jcombobox_select1.getSelectedIndex();
			setUpGUI(7);
		}
		if("swapFromOpp".equals(e.getActionCommand()))
		{
			System.out.println("SWAPPING FROM OPPONENT:");
			System.out.println("I WANT TO SWAP MY INDEX: " + swapOpp_jcombobox_playerSelect.getSelectedIndex() + " with opponents index: " + swapOpp_jcombobox_oppSelect.getSelectedIndex());
			swap();
			if(GAME_STATE.getStatus() == KNOCKED_ROUND)
			{
				changePlayer();
				setUpGUI(0);
			}
			else
			{
				setUpGUI(4); //ASK IF KNOCK OR KEEP PLAYING
			}
		}	
		if("nextRound".equals(e.getActionCommand()))
		{
			//Start New Round
			initRound();
			
			System.out.println("-------Hand for player 1");
			for(int i = 0; i < 4; i++)
			{
				System.out.println("Card " + i + ": " + playersArray[0].myHand.getCard(i).toString());
			}
			System.out.println("-------Hand for player 2");
			for(int i = 0; i < 4; i++)
			{
				System.out.println("Card " + i + ": " + playersArray[1].myHand.getCard(i).toString());
			}
			
			//Show ROUN
			setUpGUI(14);
		}
		if("nextGame".equals(e.getActionCommand()))
		{
			setUpGUI(1);
		}
	}	
}