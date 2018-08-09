package PrivateOwner;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Member extends Person
{
  private final String restCall=file.getProperty("Member");
  private String email;
  private String $class="org.acme.vehicle.auction.Member";
  private double balance;
  public String Url(){
    return (this.restServer+restCall);
  }
  public Member()  {
  }
  public Member(String first, String last,  double balance, String mail)
  {
    this.setFirstName(first);
    this.setLastName(last);
    this.balance = balance;
    this.setEmail(mail);
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
  @Override
  public String toString()
  {
      return ("Name: "+this.getFirstName()+" "+this.getLastName()+"\nEmail: "+email + "\nBalance: $" + balance);
  }
  public void jsonMap(String ownerJson) 
  {
		 try {
			  ObjectMapper map= new ObjectMapper();
	          Member owner= new Member();
	          owner= map.readValue(ownerJson, Member.class);
	          this.getCopyOf(owner);
		 }   
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
  }
  public String genJson()
  {
	  String ownerJson=null;
	  ObjectMapper map= new ObjectMapper();
	  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	  try {
		ownerJson=map.writeValueAsString(this);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return ownerJson;
  }
  protected void getCopyOf(Member owner2)

  {
	  this.set$class(owner2.get$class());
	  this.setEmail(owner2.getEmail());
	  this.setFirstName(owner2.getFirstName());
	  this.setLastName(owner2.getLastName());
	  this.setBalance(owner2.getBalance());
  }

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
} 