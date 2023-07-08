package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import entities.Vehicle;

public class VehicleDAO {
	private Map<String, Vehicle> vehicles = new HashMap<>();
	private String contextPath;

	public VehicleDAO() {
		loadVehicles();
	}
	
	public VehicleDAO(String contextPath) {
		this.contextPath = contextPath;
		loadVehicles();
	}
	
	public Vehicle findById(int id) {
		for(Vehicle r : vehicles.values()) {
			if(r.getId() == id) 
				return r;
		}
		return null;
	}
	
	public Collection<Vehicle> findAll() {
		return vehicles.values();
	}
	
	public void loadVehicles() {
		BufferedReader reader = null;
		try {
			File file = new File(contextPath + "storage\\vehicle.txt");
			reader = new BufferedReader(new FileReader(file));
			String json = reader.lines().collect(Collectors.joining());
			System.out.println(json);
			Collection<Vehicle> rList = new ObjectMapper().readValue(json, new TypeReference<List<Vehicle>>(){});
			
			for(Vehicle r : rList) {
				vehicles.put(r.getVehicleMark(), r);
			}
		} catch (Exception ex) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	public Vehicle addVehicle(Vehicle vehicle) {
		File file = new File(contextPath + "storage\\vehicle.txt");
		vehicle.setId(calculateLastIndex());
		vehicles.put(vehicle.getVehicleMark(), vehicle);
		
		BufferedWriter writer = null;
		try {
		    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		    String json = ow.writeValueAsString(vehicles.values());

		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(json);
		    
		    RentACarObjectDAO rentACarObjectDAO = new RentACarObjectDAO(contextPath);
	        rentACarObjectDAO.addVehicleToRentACarObject(vehicle.getRentacarobjectID(), vehicle);
	    
		} catch (Exception e) {
		} finally {
			if ( writer != null ) {
				try {
					writer.close();
				}
				catch (Exception e) { }
			}
		}
		return vehicle;
	}
	
	private int calculateLastIndex() {
		int maxId = -1;
		for(Vehicle r : vehicles.values()) {
			if(r.getId()>maxId)
				maxId=r.getId();
		}
		maxId++;
		return maxId;
	}
}