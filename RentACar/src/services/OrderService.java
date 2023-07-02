package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.OrderDAO;
import entities.Order;

@Path("/order")
public class OrderService {
    @Context
    ServletContext ctx;

    public OrderService() {
    }

    @PostConstruct
    public void init() {
        if (ctx.getAttribute("orderDAO") == null) {
            //String contextPath = ctx.getRealPath("");
            ctx.setAttribute("orderDAO", new OrderDAO());
        }
    }

    @GET
    @Path("/allOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> allOrders() {
        OrderDAO orderDao = (OrderDAO) ctx.getAttribute("orderDAO");
        return orderDao.findAll();
    }

    @GET
    @Path("/ordersByUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> ordersByUserId(@QueryParam("userId") int userId) {
        OrderDAO orderDao = (OrderDAO) ctx.getAttribute("orderDAO");
        return orderDao.findByUserId(userId);
    }

    @GET
    @Path("/ordersByObjectId")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> ordersByObjectId(@QueryParam("objectId") int objectId) {
        OrderDAO orderDao = (OrderDAO) ctx.getAttribute("orderDAO");
        return orderDao.findByObjectID(objectId);
    }
}
