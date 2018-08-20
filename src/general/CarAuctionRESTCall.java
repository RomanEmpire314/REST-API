package general;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class CarAuctionRESTCall
{
	protected Config file= new Config();
	protected final String restServer=file.getProperty("URL");
	private Properties config;
	
	public class Config
	{
	  public Config()
	  {
	    config= new Properties();
	    try{
	      config.load(new FileInputStream("Imported/rest.cfg"));
	    }
	    catch(IOException e){
	      e.printStackTrace();
	    }
	  }
	  public String getProperty(String key)
	  {
	    String value= config.getProperty(key);
	    return value;
	  }
	}
	
	/**
	 * 
	 * @return URL from config file
	 */
	public String Url()
	{
		return restServer;
	}
	
	/**
	 * Method to get all information from server in JSON form
	 * @return JSON form
	 */
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
      if(connection.getResponseCode()!=200) {
        JOptionPane.showMessageDialog(null, "Http GET Request Failed: "+connection.getResponseCode(), "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        String list="";
        String output;
        BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while((output=responseBuffer.readLine())!=null) {
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
  
  /**
   * get JSON-formed information from the server
   * @param iD: ID of the requested info
   * @return: Requested info in JSON form
   */
  public String getByID(String iD)
  {
    String finalString="";
    try
    {
      URL url= new URL(this.Url()+iD);
      System.out.println(url);
      HttpURLConnection connection=(HttpURLConnection) url.openConnection();
      connection.setDoOutput(false);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-type","application/json");
      if(connection.getResponseCode()!=200)
      {
        finalString=("Http GET Request Failed: "+connection.getResponseCode());
      }
      else
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
  
  /**
   * Check if the object is present on the web
   * @param iD: ID of the object
   * @return: true or false depending of the object is present
   */
  public boolean check(String iD)
  {
    boolean result=false;
    try
     {
       URL url= new URL(this.Url()+iD);
       HttpURLConnection connection=(HttpURLConnection) url.openConnection();
       connection.setDoOutput(false);
       connection.setRequestMethod("HEAD");
       connection.setRequestProperty("Content-type","application/json");
       if(connection.getResponseCode()==200)
       {
         result=true;
       }
     }
       catch(IOException e)
     {
        e.printStackTrace();
     }
    return result; 
  }
  
  /**
   * Create new object on REST server
   * @param newID: new object in JSON form
   * @return: boolean whether object is created successfully
   */
  public boolean create(String newID)
  {
	  try
	     {
			URL url= new URL(this.Url());
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-type","application/json");
			OutputStream outputStream=connection.getOutputStream();
			outputStream.write(newID.getBytes());
			BufferedReader responsebuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String output;
			System.out.println("Output from HyperLedger Composer REST Server: ");
			while ((output = responsebuffer.readLine()) != null) { 
				System.out.println(output+"\n");
			}
			return (connection.getResponseCode() == 200);
	     } catch(IOException e)  {
	        e.printStackTrace();
	     }
	  return false; 
  }
  
  /**
   * Delete an object on REST server
   * @param newID: object being deleted in JSON form
   * @return: boolean whether object is deleted successfully
   */
  public boolean delete(String iD)
  {
	  boolean result=false;
	  try
	  {
		  URL url= new URL(this.Url()+iD);
	      HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	      connection.setDoOutput(true);
	      connection.setRequestProperty("Content-type","application/json");
	      connection.setRequestMethod("DELETE");
	      connection.connect();
	      if(connection.getResponseCode()==204)
	    	  result=true;
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
	  return result;
  }
  
  /**
   * Edit an object on REST server
   * @param iD: of the edited object
   * @param info: new edited info in JSON form
   * @return: server response code
   */
  public int edit(String iD, String info)
  {
	  try	
	     {
	       URL url= new URL(this.Url()+iD);
	       System.out.println(url);
	       HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	       connection.setDoOutput(true);
	       connection.setRequestMethod("PUT");
	       connection.setRequestProperty("Content-type","application/json");
	       OutputStream outputStream=connection.getOutputStream();
	       outputStream.write(info.getBytes());
	       outputStream.close();
	       return connection.getResponseCode(); //expecting 200
	     }
	       catch(IOException e)
	     {
	        e.printStackTrace();
	     }
	  return 0;
  }
  
  /**
   * For testing
   * @param args
   */
  public static void main (String[] args) {
  }
  
} // end of class



