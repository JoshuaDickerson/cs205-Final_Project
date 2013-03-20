import java.io.*;
import java.net.*;
import org.apache.commons.net.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import com.google.gson.*;

public class Transporter{
	// private String urlString = "http://slimdowndesign.com/cs205-Final_Project/game_server/?gamedata=newdata"; 
	private String urlString = "http://localhost/205_final_server/?gamedata=newdata"; 

	public Transporter(gameState currentState){

	}

	public Transporter(String testContent){
		this.postToServer();
	}


	public void postToServer(){
		try{
			String urlParameters = "param1=a&param2=b&param3=c";
			URL url = new URL(this.urlString); 
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// displaying console output
			// while ((line = reader.readLine()) != null) {
			// 	System.out.println(line);
			// }
			writer.close();
			reader.close();
			System.out.println("Post complete");
		}catch (IOException ex){
			// handl exception
			System.out.println("Could not connect to server:  "+ex);
		}
	}
}