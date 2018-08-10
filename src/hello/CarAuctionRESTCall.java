package hello;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PrivateOwner.Member;

import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class CarAuctionRESTCall
{
	private Config file= new Config();
	private final String restServer=file.getProperty("URL");
	private Properties config;
	
	public class Config
	{
		@JsonIgnore
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
      if(connection.getResponseCode()!=200) {
        finalString=("Http GET Request Failed: "+connection.getResponseCode());
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
	
    String result = "";
    ObjectMapper mapper = new ObjectMapper();
	try {
		List<Member> listMember = mapper.readValue(finalString, new TypeReference<List<Member>>(){});
		System.out.println(listMember);
		for (int i = 0; i < listMember.size(); i++) {
			result += (listMember.get(i).toString() + "\n\n");
		};
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return result;
  }
  
  public String getByID(String iD)
  {
    String finalString="";
    try
    {
      URL url= new URL(this.Url()+iD);
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
  public void create(String newID)
  {
	  try
	     {
	       URL url= new URL(this.Url());
	       System.out.println(url + newID);
	       HttpURLConnection connection=(HttpURLConnection) url.openConnection();
	       connection.setRequestMethod("POST");
	       connection.setDoOutput(true);
	       connection.setRequestProperty("Content-type","application/json");
	       OutputStream outputStream=connection.getOutputStream();
	       outputStream.write(newID.getBytes());
	       BufferedReader responsebuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           String output;
           System.out.println("Output from HyperLedger Composer REST Server: ");
           while ((output = responsebuffer.readLine()) != null) 
            {System.out.println(output+"\n");}
           }
	       catch(IOException e)
	     {
	        e.printStackTrace();
	     }
  }
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
	       return connection.getResponseCode();
	     }
	       catch(IOException e)
	     {
	        e.printStackTrace();
	     }
	  return 0;
  }
  public static void main (String[] args) {
	  CarAuctionRESTCall auction= new CarAuctionRESTCall();
	  System.out.println(auction.restServer);
  }
}



