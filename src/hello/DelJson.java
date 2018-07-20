package hello;

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