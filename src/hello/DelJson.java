import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.BufferedReader;
public class DelJson
{
  public static void main(String[] args)
  {
    try
    {
      URL url= new URL("http://190.168:3000/api/PrivateOwner/anastasia/");
      HttpURLConnection httpConnection= (HttpURLConnection) url.openConnection();
      httpConnection.setDoOutput(true);
      httpConnection.setRequestProperty("Content-type","application/json");
      httpConnection.setRequestMethod("DELETE");
      httpConnection.connect();
      System.out.println(httpConnection.getResponseCode());
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}