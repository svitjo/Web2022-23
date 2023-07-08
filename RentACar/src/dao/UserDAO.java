package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import entities.RentACarObject;
import entities.User;
import enums.UserRole;

public class UserDAO {
	private Map<Integer, User> users = new HashMap<>();
	private String contextPath;
	
	public String getContextPath() {
		return contextPath;
	}
	
	public UserDAO() {
		loadUsers();
	}
	
	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		loadUsers();
		addAdminFromFile();
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		for(User u : users.values()) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) 
				return u;
		}
		return null;
	}
	
	public User findByUsername(String username) {
		for(User u : users.values()) {
			if(u.getUsername().equals(username)) 
				return u;
		}
		return null;
	}

	public boolean isNewUsernameUnique(User user) {
		for(User u : users.values()) {
			if(u.getId()!=user.getId() && u.getUsername().equals(user.getUsername())) 
				return false;
		}
		return true;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}
	
	public void loadUsers() {
		BufferedReader reader = null;
		try {
			File file = new File(contextPath + "storage\\users.txt");
			reader = new BufferedReader(new FileReader(file));
			String json = reader.lines().collect(Collectors.joining());
			System.out.println(json);
			Collection<User> uList = new ObjectMapper().readValue(json, new TypeReference<List<User>>(){});
			
			for(User u : uList) {
				users.put(u.getId(), u);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
	public Collection<User> loadUsersByRole(UserRole role) {
	    BufferedReader reader = null;
	    try {
	        File file = new File(contextPath + "storage\\users.txt");
	        reader = new BufferedReader(new FileReader(file));
	        String json = reader.lines().collect(Collectors.joining());
	        Collection<User> userList = new ObjectMapper().readValue(json, new TypeReference<List<User>>() {});

	        return userList.stream()
	                .filter(user -> user.getRole().equals(role))
	                .collect(Collectors.toList());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return Collections.emptyList();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public User addUser(User user) {
		user.setId(calculateLastIndex());
		users.put(user.getId(), user);	
		saveUsers();
		return user;
	}
	
	public User updateUser(User user) {
	    if (users.containsKey(user.getId())) {
	        users.put(user.getId(), user);
	        saveUsers();
	        return user;
	    } else {
	        throw new IllegalArgumentException("User with ID " + user.getId() + " does not exist.");
	    }
	}
	
	public User addAdminFromFile() {
	    BufferedReader reader = null;
	    try {
	        File file = new File(contextPath + "storage\\admin.txt");
	        reader = new BufferedReader(new FileReader(file));
	        String json = reader.lines().collect(Collectors.joining());
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<User> admins = objectMapper.readValue(json, new TypeReference<List<User>>(){});
	        User admin = admins.get(0); // IMAM JEDNOG ADMINA
	        users.put(admin.getId(), admin);
	        return admin;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (Exception e) {
	            }
	        }
	    }
	    return null;
	}
	
	public void saveUsers() {
		File file = new File(contextPath + "storage\\users.txt");

		BufferedWriter writer = null;
		try {
		    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		    String json = ow.writeValueAsString(users.values());
		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(json);
		} catch (Exception e) {
			e.printStackTrace();
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
		for(User u : users.values()) {
			if(u.getId()>maxId)
				maxId=u.getId();
		}
		maxId++;
		return maxId;
	}
	
	public List<User> getUsersByRole(UserRole role) {
	    return users.values().stream()
	            .filter(user -> user.getRole() == role)
	            .collect(Collectors.toList());
	}
	
	public User findById(int id) {
		for(User u : users.values()) {
			if(u.getId() == id) 
				return u;
		}
		return null;
	}
	
	public void updateRentACarObjectStatus(int id, boolean hasRentACarObject) {
	    User user = findById(id);
	    if (user != null) {
	        user.setHasRentACarObject(hasRentACarObject);
	        saveUsers();
	    }
	}
}