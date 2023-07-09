package services;

import java.util.Collection;
import java.util.List;

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

import dao.CommentDAO;
import dao.UserDAO;
import entities.Comment;


@Path("/comment")
public class CommentService {
	@Context
	ServletContext ctx;
	
	public CommentService() {	
	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("commentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("commentDAO", new CommentDAO(contextPath));
		}
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> allRentACarObjectss() {
		CommentDAO commentDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		commentDAO.loadComments();
		return commentDAO.findAll();
	}
	
	@GET
	@Path("/getById")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment rentacarobjectView(@QueryParam("id") int r) {
		CommentDAO commentDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		return commentDAO.findById(r);	
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment addRentACarObject(Comment r) {
		CommentDAO commentDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		commentDAO.addComment(r);
		return r;
	}
}
