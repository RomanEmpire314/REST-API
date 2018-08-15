package Transaction;

import java.util.Date;
import java.util.List;

import Vehicle.VehicleListing;

public class Offer extends VehicleListing {
	private final String restCall=file.getProperty("Offer");
	private static String $class="org.acme.vehicle.auction.Offer";
	private static double bidPrice;
	private static List<String> listing;
	private static List<String> member;
	private static Date timeStamp;
	
	public String Url(){
	    return (this.restServer+restCall);
	  }

	public String get$class() {
		return $class;
	}

	public void set$class(String $class) {
		Offer.$class = $class;
	}

	public static double getBidPrice() {
		return bidPrice;
	}

	public static void setBidPrice(double bidPrice) {
		Offer.bidPrice = bidPrice;
	}

	public static List<String> getListing() {
		return listing;
	}

	public static void setListing(List<String> listing) {
		Offer.listing = listing;
	}

	public static List<String> getMember() {
		return member;
	}

	public static void setMember(List<String> member) {
		Offer.member = member;
	}

	public static Date getTimeStamp() {
		return timeStamp;
	}

	public static void setTimeStamp(Date timeStamp) {
		Offer.timeStamp = timeStamp;
	}
}
