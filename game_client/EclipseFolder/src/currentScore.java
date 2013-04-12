import javax.swing.*;
import java.util.*;
import java.awt.*;

public class currentScore
{
	private int numPlayers;
	private Player[] allPlayers;
	private int currentIndex = 0;
	private gameState state;
	private UUID uniqueID;
	private boolean gameOver;
	private boolean roundOver;
	private int roundCount;
	private Deck mainDeck;
	private Deck discard;
	
	/** Constructor for currentScore class. Creates a currentScore object with the specified parameters.
	 *  
	 *
	 *  @param _numPlayers int containing the number of players in the game.
	 *  @param _state gameState object initializing the gameState
	 *  @param _uniqueID UUID object containing the uniqueID of the ???
	 *  @param _gameOver boolean determining whether the game is over or not
	 *  @param _roundOver boolean determining whether the round is over or not
	 *  @param _roundCount int containing the number of the round
	 *  @param _mainDeck Deck object containing the main deck
	 *  @Param _discard Deck object containing the discard pile
	 */
	public currentScore(int _numPlayers, gameState _state, UUID _uniqueID, boolean _gameOver, boolean _roundOver, int _roundCount, Deck _mainDeck, Deck _discard)
	{
		this.numPlayers = _numPlayers;
		this.allPlayers = new Player[_numPlayers];
		this.state = _state;
		this.uniqueID = _uniqueID;
		this.gameOver = _gameOver;
		this.roundOver = _roundOver;
		this.roundCount = _roundCount;		
		this.mainDeck = _mainDeck;
		this.discard = _discard;
		//MUST MANUALLY ADD PLAYER SCORES TO CURRENT SCORE ARRAY WITH ADD PLAYER SCORE
	}
	
	/** Class method to add player scores to the score array. Takes Player object as parameter
	 * 
	 *  @param _player Player object containing the player to add to the score array.
	 *
	 */
	public void addPlayer(Player _player)
	{
		this.allPlayers[this.currentIndex] = _player;
		this.currentIndex++;
	}
	
	/** Class method to get the number of players in the game. Returns player count.
	 *
	 *  @return  int containing the number of players.
	 *
	 */
	public int getPlayerCount()
	{
		return this.currentIndex;
	}
	
	/** Class method to get the player at specified index. Takes the index as parameter and returns the Player.
	 *
	 *  @param index int containing the index to retrieve the player at.
	 *  @return  Player object containing the player at the specified index.
	 *
	 */
	public Player getPlayerAtIndex(int index)
	{
		return this.allPlayers[index];
	}
	
	/** Class method to get the game state. Returns gameState object.
	 *
	 *  @return  gameState object containing the current gamestate.
	 *
	 */
	public gameState getGameState()
	{
		return this.state;
	}
}