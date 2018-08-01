package hello;

/*

GET

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


PRIVATEOWNER

package hello;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PrivateOwner extends Person
{
  private final String restCall="PrivateOwner/";
  private String email;
  private String title;
  private String $class="org.acme.vehicle.lifecycle.PrivateOwner";
  private ContractDetails con;
  public String Url(){
    return (this.restServer+restCall);
  }
  public PrivateOwner()
  {
  }
  public PrivateOwner(String first, String last,  List<String> middle, List<String> nations, String mail)
  {
    this.setFirstName(first);
    this.setMiddleNames(middle);
    this.setLastName(last);
    this.setNationalities(nations);
    setEmail(mail);
  }
  class ContractDetails
  {
	  private String $class="composer.base.ContractDetails";
	  private String email;
	  private String mobilePhone;
	  private String homePhone;

	public String get$class() {
		return $class;
	}
	public void set$class(String $class) {
		this.$class = $class;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
  }
  protected void set$class(String sclass)
  {
	  $class=sclass;
  }
  public String get$class()
  {
	  return $class;  
  }
  protected void setEmail(String mail)
  {
      email=mail;
  }
  public String getEmail()
  {
      return email;
  }
  protected void setTitle(String t)
  {
	  title=t;
  }
  public String getTitle()
  {
	  return title;
  }
  public String toString()
  {
      return ("Name: "+this.getFirstName()+" "+this.getMiddleNames()+" "+this.getLastName()+"\nNationalities: "+
      this.getNationalities()+"\nEmail: "+email);
  }
  protected void setContractDetails(ContractDetails con2)
  {
	  con=con2;
  }
  protected void jsonMap(String ownerJson) 
  {
		 try {
			  ObjectMapper map= new ObjectMapper();
	          PrivateOwner owner= new PrivateOwner();
	          owner= map.readValue(ownerJson, PrivateOwner.class);
	          this.getCopyOf(owner);
		 }   
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
  }
  public String genJson(PrivateOwner owner)
  {
	  String ownerJson=null;
	  ObjectMapper map= new ObjectMapper();
	  try {
		ownerJson=map.writeValueAsString(owner);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	  return ownerJson;
  }
  protected void getCopyOf(PrivateOwner owner2)
  {
	  this.set$class(owner2.get$class());
	  this.setEmail(owner2.getEmail());
	  this.setFirstName(owner2.getFirstName());
	  this.setMiddleNames(owner2.getMiddleNames());
	  this.setLastName(owner2.getLastName());
	  this.setNationalities(owner2.getNationalities());
  }
  public static void main(String[]args)
  {
	 PrivateOwner owner=new PrivateOwner();
	 owner.jsonMap(owner.getByID("magma"));
	 System.out.println(owner.toString());
  }
} 

*/
