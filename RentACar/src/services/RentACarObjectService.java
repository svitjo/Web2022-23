package services;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.RentACarObjectDAO;
import dao.UserDAO;
import entities.RentACarObject;
import entities.Vehicle;

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
	public Collection<RentACarObject> allRentACarObjectss() {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		rentacarobjectDAO.loadRentACarObjects();
		return rentacarobjectDAO.findAll();
	}
	
	@GET
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public RentACarObject rentACarObjectById(@QueryParam("id") int r) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		return rentacarobjectDAO.findById(r);
		
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
	
	@POST
	@Path("/addVehicle")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addVehicleToRentACarObject(@QueryParam("id") int id, Vehicle vehicle) {
	    RentACarObjectDAO rentACarObjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
	    rentACarObjectDAO.addVehicleToRentACarObject(id, vehicle);   
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RentACarObject update(RentACarObject r) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		rentacarobjectDAO.updateRentACarObject(r);
		return r;
	}
	
	@GET
	@Path("/sortedRentACarObjects")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RentACarObject> sortedRentACarObjects() {
		RentACarObjectDAO rentACarObjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		return rentACarObjectDAO.sortRentACarObjects();
	}
	
	@GET
	@Path("/getByManagerId")
	@Produces(MediaType.APPLICATION_JSON)
	public RentACarObject getByManagerId(@QueryParam("id") int r) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		return rentacarobjectDAO.findById(r);	
	}
	
	@GET
	@Path("/allVehiclesInRentACarObjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Vehicle> allVehiclesInRentACarObjects(@QueryParam("id") int id) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		return rentacarobjectDAO.findVehiclesByRentACarObjectId(id);
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(@QueryParam("id") int id) {
		RentACarObjectDAO rentacarobjectDAO = (RentACarObjectDAO) ctx.getAttribute("rentacarobjectDAO");
		rentacarobjectDAO.deleteRentACarObjectById(id);
	}
}
