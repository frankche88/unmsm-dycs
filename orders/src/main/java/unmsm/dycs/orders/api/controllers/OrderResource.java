package unmsm.dycs.orders.api.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.dropwizard.hibernate.UnitOfWork;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.domain.entity.Order;

@RolesAllowed("ADMIN")
@Path("/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderService orderService;

    @Inject
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    @UnitOfWork
    public Order create(@Valid Order customer) {
        return orderService.create(customer);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Long id) {
        orderService.delete(id);
    }

    @GET
    @Path("/_customer")
    @UnitOfWork
    public List<Order> ordersList(@QueryParam("id") Long id) {
        return orderService.ordersByBuyer(id);
    }
}
