package entities;

import java.util.Collection;
import java.util.Date;

import enums.OrderStatus;

public class Order {
	private int id;
	private Collection<Vehicle> vehicles;
	private RentACarObject object;
	private Date orderDateAndTime;
	private String orderDuration;
	private double orderPrice;
	private Customer customer;
	private OrderStatus orderStatus;
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
	public RentACarObject getObject() {
		return object;
	}
	public void setObject(RentACarObject object) {
		this.object = object;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
