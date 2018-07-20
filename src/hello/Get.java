package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Get {

	public static void main(String[] args) {
        try {
            URL url = new URL("http://192.168.0.4:3000/api/PrivateOwner/js%40email.com");
            URLConnection uc= url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) uc;
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("accept","application/json");
            if(httpConnection.getResponseCode()!=200) {
                throw new RuntimeException("Http POST request failed: " + httpConnection.getResponseCode());
            }
            else {
                BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                String output;
                String result = "";
                System.out.println("Output from HyperLedger Composer REST Server: ");
                while ((output = responseBuffer.readLine()) != null) {
                	result += output;
                }
                
                result = result.replaceAll(",", ",\n");
                System.out.println(result);
            }
            httpConnection.disconnect();
        }
        catch (IOException f){
            f.printStackTrace();
        }
    }
    
     

}