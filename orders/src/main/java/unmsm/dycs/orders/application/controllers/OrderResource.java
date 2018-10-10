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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.application.assembler.OrderAssembler;
import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

@RolesAllowed("ADMIN")
@Path("/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "v1/orders")
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
    public Order create(@Valid OrderDto orderDto) {
    	
    	Order order = orderAssembler.toEntity(orderDto);
    	
    	ObjectMapper mapper = new ObjectMapper();  
    	
    	try {
            String json = mapper.writeValueAsString(order);
            
            System.out.println("OrderResource.create(): " + json);
            
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
        return orderService.create(order);
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
    public List<OrderDto> ordersList(@QueryParam("id") Long id) {
    	
    	List<Order> orders = orderService.ordersByBuyer(id);
    	
        return orderAssembler.toDto(orders);
    }

    @GET
    @Path("/buyer")
    @UnitOfWork
    public List<Order> ordersBuyerList(@QueryParam("id") Long id) {
    	
        return orderService.ordersByBuyer(id);
    }
}
