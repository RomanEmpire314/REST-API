package PrivateOwner;

import hello.CarAuctionRESTCall;

public class Vehicle extends CarAuctionRESTCall {
	private String $class="org.acme.vehicle.auction.Vehicle";
	private String vin;
	private String owner;
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

}
