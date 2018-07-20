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
		try {
			input = URLEncoder.encode(input);
//			System.out.println(input);
			input = ownerURL + input;
			
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
