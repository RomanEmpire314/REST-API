package Transaction;

import java.util.Date;

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
	
	
}
