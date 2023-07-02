package entities;

import enums.FuelType;
import enums.Transmissions;
import enums.VehicleAvailability;
import enums.VehicleType;

public class Vehicle {
	private int id;
	private String vehicleMark;
	private double vehiclePrice;
	private VehicleType vehicleType;
	private int rentacarobjectID;
	private double consumption;
	private Transmissions vehicleTransmissions;
	private FuelType fuelType;
	private double doorNumber;
	private double peopleInCarNumber;
	private String description;
	private String image;
	private VehicleAvailability status;
	public String getVehicleMark() {
		return vehicleMark;
	}
	public void setVehicleMark(String vehicleMark) {
		this.vehicleMark = vehicleMark;
	}
	public double getVehiclePrice() {
		return vehiclePrice;
	}
	public void setVehiclePrice(double vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	public Transmissions getVehicleTransmissions() {
		return vehicleTransmissions;
	}
	public void setVehicleTransmissions(Transmissions vehicleTransmissions) {
		this.vehicleTransmissions = vehicleTransmissions;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public double getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(double doorNumber) {
		this.doorNumber = doorNumber;
	}
	public double getPeopleInCarNumber() {
		return peopleInCarNumber;
	}
	public void setPeopleInCarNumber(double peopleInCarNumber) {
		this.peopleInCarNumber = peopleInCarNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public VehicleAvailability getStatus() {
		return status;
	}
	public void setStatus(VehicleAvailability status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRentacarobjectID() {
		return rentacarobjectID;
	}
	public void setRentacarobjectID(int rentacarobjectID) {
		this.rentacarobjectID = rentacarobjectID;
	}

}
