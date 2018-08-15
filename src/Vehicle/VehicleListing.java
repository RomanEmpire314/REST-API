package Vehicle;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Transaction.Offer;
import general.CarAuctionRESTCall;

public class VehicleListing extends Vehicle {
	private final String restCall=file.getProperty("VehicleListing");
	private String $class="org.acme.vehicle.auction.VehicleListing";
	private String listingID;
	private double reservePrice;
	private String description;
	private List<Offer> offer;
	private ListingState state= ListingState.FOR_SALE;
	private String vehicle;
	enum ListingState{
		FOR_SALE,
		RESERVE_NOT_MET,
		SOLD
	}
	
	/* Constructor
	 * Takes in vehicle
	 */
	public VehicleListing(String vehicle) {
		this.vehicle = vehicle;
	}
	
	/* Constructor
	 * No parameter
	 */
	public VehicleListing () {
		
	}
	
	
	public String Url(){
	    return (this.restServer+restCall);
	  }

	public String get$class() {
		return $class;
	}

	public void set$class(String $class) {
		this.$class = $class;
	}

	public String getListingID() {
		return listingID;
	}

	public void setListingID(String listingID) {
		this.listingID = listingID;
	}

	public double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ListingState getState() {
		return state;
	}

	public void setState(ListingState state) {
		this.state = state;
	}
	
	 public String genJson()
	  {
		  String listing=null;
		  ObjectMapper map= new ObjectMapper();
		  map.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		  try {
			listing=map.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return listing;
	  }
	 
	 public void getCopy(VehicleListing listing)
	    {
	        this.listingID= listing.getListingID();
	        this.description=listing.getDescription();
	        this.reservePrice=listing.getReservePrice();
	        this.state=listing.getState();
	    }
	 
	 public void jsonMap(String vehicleJson) 
	  {
			 try {
				  ObjectMapper map= new ObjectMapper();
				  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		          VehicleListing vehicle= new VehicleListing();
		          vehicle= map.readValue(vehicleJson, VehicleListing.class);
		          this.getCopy(vehicle);
			 }   
			 catch(IOException e)
			 {
				 e.printStackTrace();
			 }
	  }

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public List<Offer> getOffer() {
		return offer;
	}

	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}

}
