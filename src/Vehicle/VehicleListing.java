package Vehicle;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import general.CarAuctionRESTCall;

public class VehicleListing extends CarAuctionRESTCall {
	private final String restCall=file.getProperty("VehicleListing");
	private String $class="org.acme.vehicle.auction.VehicleListing";
	private String listingID;
	private double reservePrice;
	private String description;
	private ListingState state= ListingState.FOR_SALE;
	private Vehicle vehicle;
	enum ListingState{
		FOR_SALE,
		RESERVE_NOT_MET,
		SOLD
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
