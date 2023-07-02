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

import dao.VehicleDAO;
import entities.Vehicle;

@Path("/vehicle")
public class VehicleService {
	
	@Context
	ServletContext ctx;
	
	public VehicleService() {
		
	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("vehicleDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("vehicleDAO", new VehicleDAO(contextPath));
		}
	}
	
	@GET
	@Path("/allVehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Vehicle> allVehicless() {
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		vehicleDAO.loadVehicles();
		return vehicleDAO.findAll();
	}
	
	@POST
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Vehicle getVehicleById(Vehicle r) {
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		return vehicleDAO.findById(r.getId());
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Vehicle addVehicle(Vehicle r) {
		VehicleDAO vehicleDAO = (VehicleDAO) ctx.getAttribute("vehicleDAO");
		vehicleDAO.addVehicle(r);
		return r;
	}
}
