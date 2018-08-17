package Transaction;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Vehicle.VehicleListing;

public class CloseBidding extends Transaction {
private final String restCall= file.getProperty("CloseBidding");
private String listing="resource:"+new VehicleListing().get$class();

public String Url(){
    return (this.restServer+restCall);
}
public String getListing() {
	return listing;
}
public void setListing(String listing) {
	this.listing = listing;
}
public boolean setListingByID(String id)
{
	boolean valid=false;
	if((valid=new VehicleListing().check(id)))
	    this.listing+="#"+id;
	return valid;
}
public void getCopyOf(CloseBidding close) {
	this.listing=close.getListing();
}

 public void jsonMap(String ownerJson) 
  {
		 try {
			  ObjectMapper map= new ObjectMapper();
			  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	          CloseBidding owner= new CloseBidding();
	          owner= map.readValue(ownerJson, CloseBidding.class);
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
	  map.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	  try {
		ownerJson=map.writeValueAsString(this);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return ownerJson;
  }
}
