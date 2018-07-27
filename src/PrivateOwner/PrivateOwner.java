package PrivateOwner;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrivateOwner extends Person
{
  private final String restCall="PrivateOwner/";
  private String email;
  private String title;
  private String $class="org.acme.vehicle.lifecycle.PrivateOwner";
  private ContactDetails con;
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
 
  protected void set$class(String sclass)
  {
	  $class=sclass;
  }
  public String get$class()
  {
	  return $class;  
  }
  @JsonProperty("email")
  protected void setEmail(String mail)
  {
      email=mail;
  }
  public String getEmail()
  {
      return email;
  }
  @JsonProperty("title")
  protected void setTitle(String t)
  {
	  title=t;
  }
  public String getTitle()
  {
	  return title;
  }
  @JsonProperty("contactDetails")
  public ContactDetails getContactDetails()
  {
	  return con;
  }
  protected void setContactDetails(ContactDetails con2)
  {
	  this.con=con2;
  }
  @Override
  public String toString()
  {
      return ("Name: "+this.getFirstName()+" "+this.getMiddleNames()+" "+this.getLastName()+"\nNationalities: "+
      this.getNationalities()+"\nEmail: "+email);
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
	  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	  try {
		ownerJson=map.writeValueAsString(owner);
	} catch (IOException e) {
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
	 owner.jsonMap(owner.getByID("mana"));
	 System.out.println(owner.toString());
  }
} 