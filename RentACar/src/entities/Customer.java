package entities;

import java.util.Collection;

public class Customer extends User {
	private Collection<Order> orders;
	private Cart cart;
	private double points;
	private CustomerType typeOfCustomer;
	
	public Customer(User user) {
    }
	public Customer () {
		super();
	}
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public CustomerType getTypeOfCustomer() {
		return typeOfCustomer;
	}
	public void setTypeOfCustomer(CustomerType typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}

}
