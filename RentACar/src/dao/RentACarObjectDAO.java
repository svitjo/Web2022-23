package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import entities.RentACarObject;
import entities.User;
import entities.Vehicle;
import enums.ObjectStatus;

public class RentACarObjectDAO {
		private Map<Integer, RentACarObject> rentacarobjects = new HashMap<>();
		private String contextPath;
		private UserDAO userDao;

		public RentACarObjectDAO() {
			loadRentACarObjects();
		}
		
		public void setUserDao(UserDAO userDao) {
			this.userDao = userDao;
	    }
		
		public RentACarObjectDAO(String contextPath) {
			this.contextPath = contextPath;
			loadRentACarObjects();
		}
		
		public RentACarObject findById(int id) {
			for(RentACarObject r : rentacarobjects.values()) {
				if(r.getId() == id) 
					return r;
			}
			return null;
		}
		
		public Collection<RentACarObject> findAll() {
			return rentacarobjects.values();
		}
		
		public void loadRentACarObjects() {
			BufferedReader reader = null;
			try {
				File file = new File(contextPath + "storage\\rentacarobjects.txt");
				reader = new BufferedReader(new FileReader(file));
				String json = reader.lines().collect(Collectors.joining());
				System.out.println(json);
				Collection<RentACarObject> rList = new ObjectMapper().readValue(json, new TypeReference<List<RentACarObject>>(){});
				
				for(RentACarObject r : rList) {
					rentacarobjects.put(r.getId(), r);
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
		
		public RentACarObject addRentACarObject(RentACarObject rentacarobject) {
			
			rentacarobject.setId(calculateLastIndex());
			rentacarobjects.put(rentacarobject.getId(), rentacarobject);
			saveRentACarObjects();
			return rentacarobject;
		}
		
		public RentACarObject updateRentACarObject(RentACarObject rentacarobject) {
		    if (rentacarobjects.containsKey(rentacarobject.getId())) {
		        rentacarobjects.put(rentacarobject.getId(), rentacarobject);
		        saveRentACarObjects();
		        return rentacarobject;
		    } else {
		        throw new IllegalArgumentException("RentACarObject with ID " + rentacarobject.getId() + " does not exist.");
		    }
		}
		
		public void saveRentACarObjects() {
			File file = new File(contextPath + "storage\\rentacarobjects.txt");
		
			BufferedWriter writer = null;
			try {
			    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			    String json = ow.writeValueAsString(rentacarobjects.values());

			    writer = new BufferedWriter(new FileWriter(file));
			    writer.write(json);
			} catch (Exception e) {
			} finally {
				if ( writer != null ) {
					try {
						writer.close();
					}
					catch (Exception e) { }
				}
			}
		}

		private int calculateLastIndex() {
			int maxId = -1;
			for(RentACarObject r : rentacarobjects.values()) {
				if(r.getId()>maxId)
					maxId=r.getId();
			}
			maxId++;
			return maxId;
		}
		
		public void addVehicleToRentACarObject(int rentacarobjectId, Vehicle vehicle) {
		    RentACarObject rentACarObject = rentacarobjects.get(rentacarobjectId);
		    if (rentACarObject != null) {
		        vehicle.setRentacarobjectID(rentacarobjectId);
		        rentACarObject.getVehicle().add(vehicle);
		        saveRentACarObjects();
		    }
		}
		
		public List<RentACarObject> sortRentACarObjects() {
	        List<RentACarObject> allObjects = new ArrayList<>(rentacarobjects.values());
	        List<RentACarObject> sortedObjects = new ArrayList<>();

	        List<RentACarObject> openStatusObjects = new ArrayList<>();
	        List<RentACarObject> closeStatusObjects = new ArrayList<>();
	        for (RentACarObject obj : allObjects) {
	            if (obj.getObjectStatus() == ObjectStatus.OPEN) {
	                openStatusObjects.add(obj);
	            } else if (obj.getObjectStatus() == ObjectStatus.CLOSE) {
	                closeStatusObjects.add(obj);
	            }
	        }
	        sortedObjects.addAll(openStatusObjects);
	        sortedObjects.addAll(closeStatusObjects);

	        return sortedObjects;
	    }
		
		public RentACarObject findByManagerId(int managerId) {
		    for (RentACarObject r : rentacarobjects.values()) {
		        if (r.getManagerId() == managerId) {
		            return r;
		        }
		    }
		    return null;
		}
		
		public Collection<Vehicle> findVehiclesByRentACarObjectId(int rentacarobjectId) {
		    RentACarObject rentACarObject = rentacarobjects.get(rentacarobjectId);
		    if (rentACarObject != null) {
		        return rentACarObject.getVehicle();
		    }
		    return new ArrayList<>(); 
		}
		
		public void deleteRentACarObjectById(int id) {
		    RentACarObject rentACarObject = rentacarobjects.remove(id);
		    if (rentACarObject != null) {
		    	saveRentACarObjects();
		    }
		}
}
