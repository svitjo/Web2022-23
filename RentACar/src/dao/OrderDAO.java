package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import entities.Order;
import entities.Vehicle;
import enums.FuelType;
import enums.OrderStatus;
import enums.Transmissions;
import enums.VehicleAvailability;
import enums.VehicleType;

public class OrderDAO {
    private List<Order> orders;

    public OrderDAO() {
        orders = new ArrayList<>();

        Order order1 = new Order();
        order1.setId(1);
        order1.setObjectId(1);
        Date specificDate = new Date(1234567890000L);
        order1.setOrderDateAndTime(specificDate);
        order1.setOrderDuration("2 days");
        order1.setOrderPrice(5000.0);
        order1.setUserId(2);
        order1.setOrderStatus(OrderStatus.APPROVED);

        List<Vehicle> vehicles1 = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setVehicleMark("Vehicle 1");
        vehicle1.setVehiclePrice(5000.0);
        vehicle1.setVehicleType(VehicleType.CAR);
        vehicle1.setRentacarobjectID(2);
        vehicle1.setConsumption(7.8);
        vehicle1.setVehicleTransmissions(Transmissions.MANUAL);
        vehicle1.setFuelType(FuelType.GAS);
        vehicle1.setDoorNumber(4);
        vehicle1.setPeopleInCarNumber(5);
        vehicle1.setDescription("Prvi order");
        vehicle1.setImage("Slika");
        vehicle1.setStatus(VehicleAvailability.RENTED);
        
        vehicles1.add(vehicle1);
        order1.setVehicles(vehicles1);
        orders.add(order1);
    }

    public List<Order> findAll() {
        return orders;
    }

    public List<Order> findByUserId(int userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId() == userId) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<Order> findByObjectID(int objectID) {
        List<Order> objectOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getObjectId() == objectID) {
                objectOrders.add(order);
            }
        }
        return objectOrders;
    }
}


