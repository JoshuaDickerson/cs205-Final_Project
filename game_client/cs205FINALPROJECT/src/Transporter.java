import java.io.*;
import java.net.*;
import org.apache.commons.net.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import com.google.gson.*;


/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
*
* The Transporter object takes a currentScore object on instanciation
* and generates JOSN which is then posted to the server 
*/




public class Transporter{
	private String urlString = "http://slimdowndesign.com/cs205-Final_Project/game_server/Gamedata/?gamedata=newdata"; 
	//private String urlString = "http://localhost/205_final_server/Gamedata/?gamedata=newdata"; 

	public Transporter(currentScore currentState){
		Gson gson = new Gson();
		//convert our object to json
		String json = gson.toJson(currentState);
		// send it off
		this.postToServer(json);
	}

	public Transporter(String testContent){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(this);
		this.postToServer(json);
	}
	


	public void postToServer(String postvar){
		try{
			String urlParameters = "gameState="+postvar;
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
} // end Transporter class
