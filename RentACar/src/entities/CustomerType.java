package entities;

import enums.TypeOfCustomer;

public class CustomerType {
	private TypeOfCustomer type;
	private double discount;
	private double points;
	public TypeOfCustomer getType() {
		return type;
	}
	public void setType(TypeOfCustomer type) {
		this.type = type;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
}
