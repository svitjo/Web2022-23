package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import entities.RentACarObject;
import entities.User;

public class RentACarObjectDAO {
		private Map<String, RentACarObject> rentacarobjects = new HashMap<>();
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
					rentacarobjects.put(r.getObjectName(), r);
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
			File file = new File(contextPath + "storage\\rentacarobjects.txt");
			rentacarobject.setId(calculateLastIndex());
			rentacarobjects.put(rentacarobject.getObjectName(), rentacarobject);
			
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
			return rentacarobject;
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
}
