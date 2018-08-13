package Vehicle;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import general.CarAuctionRESTCall;

public class Vehicle extends CarAuctionRESTCall {
	
	private final String restCall=file.getProperty("Vehicle");
	private String $class="org.acme.vehicle.auction.Vehicle";
	private String vin;
	private String owner;
	
	public String Url(){
	    return (this.restServer+restCall);
	  }
	public String get$class() {
		return $class;
	}
	public void set$class(String $class) {
		this.$class = $class;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	 public String genJson()
	  {
		  String vehicle=null;
		  ObjectMapper map= new ObjectMapper();
		  map.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		  map.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		  try {
			vehicle=map.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return vehicle;
	  }
	 public void getCopy(Vehicle vehicle)
	    {
	        this.vin=vehicle.getVin();
	        this.owner=vehicle.getOwner();
	    }
	 public void jsonMap(String vehicleJson) 
	  {
			 try {
				  ObjectMapper map= new ObjectMapper();
		          Vehicle vehicle= new Vehicle();
		          vehicle= map.readValue(vehicleJson, Vehicle.class);
		          this.getCopy(vehicle);
			 }   
			 catch(IOException e)
			 {
				 e.printStackTrace();
			 }
	  }
}
