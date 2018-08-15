package Transaction;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class Offer extends Transaction {
	private final String restCall=file.getProperty("Offer");
	private String $class="org.acme.vehicle.auction.Offer";
	private double bidPrice;
	private String listing;
	private String member;
	private Date timestamp;
	
	public String Url(){
	    return (this.restServer+restCall);
	  }

	public String get$class() {
		return $class;
	}

	public void set$class(String $class) {
		this.$class = $class;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getListing() {
		return this.listing;
	}

	public void setListing(String listing) {
		this.listing = listing;
	}

	public String getMember() {
		return this.member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timeStamp) {
		this.timestamp = timeStamp;
	}
	
	public void getCopyOf(Offer offer) {
		this.listing=offer.getListing();
		this.member=offer.getMember();
		this.timestamp=offer.getTimestamp();
		this.bidPrice=offer.getBidPrice();
	}
	
	 public void jsonMap(String ownerJson) 
	  {
			 try {
				  ObjectMapper map= new ObjectMapper();
				  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		          Offer owner= new Offer();
		          owner= map.readValue(ownerJson, Offer.class);
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
