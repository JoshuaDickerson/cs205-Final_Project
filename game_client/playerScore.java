import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class playerScore
{
	private int currentScore;
	private String playerName;
	private String currentTime;
	
	public playerScore(int _currentScore, String _playerName)
	{
		this.currentScore = _currentScore;
		this.playerName = _playerName;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.currentTime = dateFormat.format(date);
	}
	
	public int getScore()
	{
		return this.currentScore;
	}
	
	public String getName()
	{
		return this.playerName;
	}
	
	public String getDate()
	{
		return this.currentTime;
	}
}