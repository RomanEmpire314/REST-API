package Transaction;

import java.util.Date;
import java.util.List;

import Vehicle.VehicleListing;

public class Offer extends VehicleListing {
	private final String restCall=file.getProperty("Offer");
	private String $class="org.acme.vehicle.auction.Offer";
	private double bidPrice;
	private String listing;
	private String member;
	private Date timeStamp;
	
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

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
