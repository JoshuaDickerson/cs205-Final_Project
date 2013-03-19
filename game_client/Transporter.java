import java.io.*;
import java.net.*;
import org.apache.commons.net.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;

public class Transporter{
	private String url = "http://slimdowndesign.com/cs205-Final_Project/game_server/?gamedata=newdata"; 

	public Transporter(gameState currentState){

	}


	public void postToServer(){
		HttpClient httpClient = new DefaultHttpClient();

		try {
			URL urlObj = new URL(this.url);
		} catch (MalformedURLException ex) {
			// handle exception
		}
		HttpURLConnection urlConn = null;
	}
}