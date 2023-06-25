package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.UserDAO;
import entities.User;
import enums.UserRole;

@Path("/user")
public class UserService {
	@Context
	ServletContext ctx;
	
	public UserService() {	
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	@GET
	@Path("/allUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> allUsers() {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.findAll();
	}
	
	@GET
	@Path("/allUsersByRole")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> allUsersByRole(@QueryParam("role") UserRole role) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.getUsersByRole(role);
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User u) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User userWithNewUsername = userDao.findByUsername(u.getUsername());
		if(userWithNewUsername==null) {
			userDao.addUser(u);
			return u;
		}
		return null;
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User u) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");	
		User user = userDao.findByUsernameAndPassword(u.getUsername(), u.getPassword());	
		if(user == null) {
			return null;		
		}
		return user;		
	}
}