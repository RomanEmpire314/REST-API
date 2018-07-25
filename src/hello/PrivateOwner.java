package blockchainNET;

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