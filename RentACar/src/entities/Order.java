package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import enums.OrderStatus;

public class Order {
	private int id;
	private Collection<Vehicle> vehicles;
	private int objectId;
	private Date orderDateAndTime;
	private String orderDuration;
	private double orderPrice;
	private int userId;
	private OrderStatus orderStatus;
	
	public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
	public Order() {
		vehicles = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public Date getOrderDateAndTime() {
		return orderDateAndTime;
	}
	public void setOrderDateAndTime(Date orderDateAndTime) {
		this.orderDateAndTime = orderDateAndTime;
	}
	public String getOrderDuration() {
		return orderDuration;
	}
	public void setOrderDuration(String orderDuration) {
		this.orderDuration = orderDuration;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
