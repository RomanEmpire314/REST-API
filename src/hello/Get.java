package hello;

import java.io.*;
import java.net.*;
import org.json.*;

public class Get {

	public static void main(String[] args) {
        try {
            URL url = new URL("http://192.168.0.4:3000/api/PrivateOwner/js%40email.com");
            URLConnection uc= url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) uc;
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("accept","application/json");
            if(httpConnection.getResponseCode()!=200)
            {
                throw new RuntimeException("Http POST request failed: "+httpConnection.getResponseCode());
            }
            else {
                BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                String output;
                String result = "";
                System.out.println("Output from HyperLedger Composer REST Server: ");
                while ((output = responseBuffer.readLine()) != null) {
                	result += output;
                }
                
                
                
                parseJSon(result);
                result = result.replaceAll(",", ",\n");
                System.out.println(result);
            }
        }
        catch (IOException f){
            f.printStackTrace();
        }
    }
    
     
    public static void parseJSon (String theJSon) {
    	try {
    		JSONObject json1 = new JSONObject (theJSon);
    		String email = json1.getString("email");
    		System.out.println(email);
    		
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    }
    
}