package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.RentACarObjectDAO;
import dao.UserDAO;
import entities.RentACarObject;

@Path("/rentacarobject")
public class RentACarObjectService {
	
	@Context
	ServletContext ctx;
	
	public RentACarObjectService() {
		
	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("rentacarobjectDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			//ctx.setAttribute("rentacarobjectDAO", new RentACarObjectDAO(contextPath));
	    	RentACarObjectDAO rentacarobjectDAO = new RentACarObjectDAO(contextPath);
            UserDAO userDao = new UserDAO(); // Create a UserDAO object
            rentacarobjectDAO.setUserDao(userDao); // Set the UserDAO object in RentACarObjectDAO
            ctx.setAttribute("rentacarobjectDAO", rentacarobjectDAO);
		}
	}
	
	@GET
	@Path("/allRentACarObjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<RentACarObject> allRestaurants() {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		rentacarobjectDAO.loadRentACarObjects();
		return rentacarobjectDAO.findAll();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RentACarObject restourantView(RentACarObject r) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		return rentacarobjectDAO.findById(r.getId());
		
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RentACarObject addRentACarObject(RentACarObject r) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		rentacarobjectDAO.addRentACarObject(r);
		
		int managerId = r.getManagerId();
        userDao.updateRentACarObjectStatus(managerId, true);

		return r;
	}
}
