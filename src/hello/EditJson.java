package hello;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class EditJson {
	
  public static void main(String[]args) {
    try {
	    URL url=new URL("http://192.168.4:3000/api/PrivateOwner/joe%40bangaichi");
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("PUT");
	    connection.setRequestProperty("Content-type","application/json");
	    connection.setDoOutput(true);
	    String input="{\"firstName\":\"Joe\",\"nationalities\":[\"Nowhere\"],\"title\":\"Naked Boy\",\"lastName\":\"Gearless\"}";
	    OutputStream outputStream = connection.getOutputStream();
	    outputStream.write(input.getBytes());
	    BufferedReader responseBuffer= new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String output;
	    while((output=responseBuffer.readLine())!=null) {
	    	System.out.println(output);
	    }
	    connection.disconnect();
    }
    catch (IOException e)   {
      e.printStackTrace();
    }
    
  }
}