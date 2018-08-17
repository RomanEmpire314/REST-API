package Vehicle;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import general.CarAuctionRESTCall;
import transaction.Offer;

public class VehicleListing extends CarAuctionRESTCall {
	private final String restCall=file.getProperty("VehicleListing");
	private String $class="org.acme.vehicle.auction.VehicleListing";
	private String listingId;
	private double reservePrice;
	private String description;
	private List<Offer> offers;
	private ListingState state= ListingState.FOR_SALE;
	private String vehicle;
	@JsonIgnore
	private String vehicleID;

	enum ListingState{
		FOR_SALE,
		RESERVE_NOT_MET,
		SOLD
	}
	
	/* Constructor
	 * Takes in vehicle
	 */
	public VehicleListing(Vehicle vehicle, String listingID, double reservePrice, String description) {
		String vehicleAddress = "resource:" + vehicle.get$class() + "#" + vehicle.getVin();
		this.vehicle = vehicleAddress;
		this.vehicleID = vehicle.getVin();
		this.listingId = listingID;
		this.reservePrice = reservePrice;
		this.description = description;
		System.out.println(this.toString());
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

	@JsonIgnore
	public String getListingId() {
		return listingId;
	}

	@JsonProperty
	public void setListingId(String listingId) {
		this.listingId = listingId;
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
	        this.listingId= listing.getListingId();
	        this.description=listing.getDescription();
	        this.reservePrice=listing.getReservePrice();
	        this.state=listing.getState();
	      /*
	        String idListing = listing.getListingId();
	        this.vehicleID = idListing.substring(idListing.indexOf("#") + 1, idListing.length() );
	        System.out.println(vehicleID);
	        */
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
	
	public boolean setVehicleByID(String id)
	{
		boolean valid=false;
		if((valid=new Vehicle().check(id)))
		    this.vehicle+="#"+id;
		return valid;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	public String toString() {
		return "Listing ID: " + this.listingId + "; \nReserve Price: " + this.reservePrice + "; \nDescription: " + this.description
				+ "; \nVehicle vin: " +	this.vehicle.substring(vehicle.indexOf("#") + 1, vehicle.length()) + "; \nState: " + this.state;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

}
