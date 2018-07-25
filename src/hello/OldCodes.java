package hello;
/**
package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class CheckJSon {

	@SuppressWarnings("deprecation")
	public static void main (String [] args) {
		URL url1;
		boolean exist;
		final String ownerURL = "http://192.168.0.4:3000/api/PrivateOwner/";
		String input = "ans";
		input = URLEncoder.encode(input);
		input = ownerURL + input;
		
		try {			
			url1 = new URL(input);			
			URLConnection connection1 = url1.openConnection();
	        HttpURLConnection httpConnection1 = (HttpURLConnection)connection1;
	        httpConnection1.setRequestMethod("HEAD");
            httpConnection1.setRequestProperty("accept","application/json");
            
            if (httpConnection1.getResponseCode() == 200) {
            	exist = true;
            } else {
            	exist = false;
            }
            
            System.out.println(exist);
            httpConnection1.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class DelJson
{
  public static void main(String[] args)
  {
    try
    {
      URL url= new URL("http://192.168.0.4:3000/api/PrivateOwner/anastasia/");
      HttpURLConnection httpConnection= (HttpURLConnection) url.openConnection();
      httpConnection.setDoOutput(true);
      httpConnection.setRequestMethod("DELETE");
      httpConnection.setRequestProperty("accept","application/json");
      System.out.println(httpConnection.getResponseCode());
      httpConnection.disconnect();

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CreateJson {

    public static void main(String[]args) {
        try {
            URL url1 = new URL("http://192.168.0.4:3000/api/PrivateOwner/");
            URLConnection connection1 = url1.openConnection();
            HttpURLConnection httpconnection1 = (HttpURLConnection)connection1;
            httpconnection1.setRequestMethod("POST");
            httpconnection1.setDoOutput(true);
            httpconnection1.setRequestProperty("Content-type", "application/json");
            String input = "{\"$class\":\"org.acme.vehicle.lifecycle.PrivateOwner\",\"email\":\"AjoeSmith@bangaichi\",\"firstName\":\"Joe\"}";
            System.out.println(input);
            OutputStream outputStream = httpconnection1.getOutputStream();
            outputStream.write(input.getBytes());
            BufferedReader responsebuffer = new BufferedReader(new InputStreamReader(httpconnection1.getInputStream()));
                String output;
                System.out.println("Output from HyperLedger Composer REST Server: ");
                while ((output = responsebuffer.readLine()) != null) {
                  System.out.println(output+"a\n");}
            httpconnection1.disconnect();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
}

*/
