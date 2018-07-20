package hello;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class VehicleLifeCycleNetworkRestCall
{
  protected final String restServer="http://192.168.0.4:3000/api/";
  
  public String Url()
  {
    return restServer;
  }
  
  public String get()
  {
    String finalString="";
    try
    {
		URL url= new URL(this.Url());
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setDoOutput(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-type","application/json");
		if(connection.getResponseCode()!=200)
	    {
	      finalString=("Http GET Request Failed: "+connection.getResponseCode());
	    } else 
	    {
	        String list="";
	        String output;
	        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        while((output=responseBuffer.readLine())!=null)
	        {
	          list=output;
	        }
	        finalString=list;
	    }
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    return finalString;
  }
}