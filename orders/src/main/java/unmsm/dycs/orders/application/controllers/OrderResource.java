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
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.OAuth2Definition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.application.assembler.OrderAssembler;
import unmsm.dycs.orders.application.dto.OrderHeaderOutputDto;
import unmsm.dycs.orders.application.dto.OrderInputDto;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "bearer", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER, name = "bearer") 
                }
//        ,
//        oAuth2Definitions = {
//                @OAuth2Definition(flow = OAuth2Definition.Flow.PASSWORD, key = "OAuth2", authorizationUrl= "/aouth")
//        }
        )
)

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
    @ApiOperation(value = "Add order", notes = "Agregate new Order", authorizations = {
            @Authorization(value = "bearer") })
    public Order create(@Valid OrderInputDto orderDto) {

        Order order = orderAssembler.toEntity(orderDto);

        return orderService.create(order);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "Remove order", notes = "Remove Order by id order", authorizations = {
            @Authorization(value = "bearer") })
    public void delete(@PathParam("id") Long id) {
        orderService.delete(id);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "show order", notes = "Show Order by id order", authorizations = {
            @Authorization(value = "bearer") })
    public OrderOutputDto ordersList(@QueryParam("id") Long id) {

        Order order = orderService.orderById(id);

        return orderAssembler.toDto(order);
    }

    @GET
    @UnitOfWork
    @ApiOperation(value = "List orders", notes = "List all orders", authorizations = {
            @Authorization(value = "bearer") })
    public List<OrderHeaderOutputDto> ordersList() {

        return orderAssembler.toHeaderDto(orderService.findAll());

    }
}
