import java.io.*;
import java.net.*;
import org.apache.commons.net.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;

public class Transporter{
	private String urlString = "http://slimdowndesign.com/cs205-Final_Project/game_server/?gamedata=newdata"; 

	public Transporter(gameState currentState){

	}

	public Transporter(String testContent){
		this.postToServer();
	}


	public void postToServer(){
		try{
			String urlParameters = "param1=a&param2=b&param3=c";
			URL url = new URL(this.urlString); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setUseCaches (false);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			connection.disconnect();
			System.out.println("Post complete");
		}catch (IOException ex){
			// handl exception
			System.out.println("Could not connect to server");
		}
	}
}