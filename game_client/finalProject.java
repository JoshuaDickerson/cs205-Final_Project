import javax.swing.*;
import java.util.*;
import java.awt.*;

public class finalProject extends JApplet
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
	public static final int HIGH_SCIRE = 2;
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

	public void gameLoop()
	{
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
		currentScore totalScore = new currentScore(2);
		totalScore.addPlayerScore(player1);
		totalScore.addPlayerScore(player2);
		System.out.println("TOTAL PLAYERS IN SCORE OBJECT SIZE: " + totalScore.getPlayerCount());
		System.out.println(totalScore.getPlayerScoreAtIndex(0).getName() + " has score " + totalScore.getPlayerScoreAtIndex(0).getScore());
		System.out.println(totalScore.getPlayerScoreAtIndex(1).getName() + " has score " + totalScore.getPlayerScoreAtIndex(1).getScore());

	}
	/* Invoked immediately after the start() method, and also any time
	the applet needs to repaint itself in the browser. The paint()
	method is actually inherited from the java.awt.*/
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawString("WOW THIS WORKED!",25,25);
	}
	
	/*This method is intended for whatever initialization is needed for your applet.
	It is called after the param tags inside the applet tag have been processed.*/
	public void init()
	{
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
}
