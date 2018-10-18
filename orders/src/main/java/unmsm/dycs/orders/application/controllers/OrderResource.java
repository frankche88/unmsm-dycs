package unmsm.dycs.orders.application.controllers;

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
import io.swagger.annotations.Api;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.application.assembler.OrderAssembler;
import unmsm.dycs.orders.application.dto.OrderHeaderOutputDto;
import unmsm.dycs.orders.application.dto.OrderInputDto;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;

@RolesAllowed("ADMIN")
@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/api/orders")
public class OrderResource {

    private final OrderService orderService;
    
    private OrderAssembler orderAssembler;

    @Inject
    public OrderResource(OrderService orderService, OrderAssembler assembler) {

        this.orderAssembler = assembler;
        this.orderService = orderService;
    }

    @POST
    @UnitOfWork
    public Order create(@Valid OrderInputDto orderDto) {
    	
    	Order order = orderAssembler.toEntity(orderDto);
    	
        return orderService.create(order);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Long id) {
        orderService.delete(id);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public OrderOutputDto ordersList(@QueryParam("id") Long id) {
    	
    	Order order = orderService.orderById(id);
    	
        return orderAssembler.toDto(order);
    }

    @GET
    @UnitOfWork
    public List<OrderHeaderOutputDto> ordersList() {
    	
    	return orderAssembler.toHeaderDto(orderService.findAll());
        		
    }
}
