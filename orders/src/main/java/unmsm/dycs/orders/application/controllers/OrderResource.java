package unmsm.dycs.orders.application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import unmsm.dycs.application.security.client.ApplicationUser;
import unmsm.dycs.commons.application.dto.ErrorDto;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.application.assembler.OrderAssembler;
import unmsm.dycs.orders.application.dto.OrderHeaderOutputDto;
import unmsm.dycs.orders.application.dto.OrderInputDto;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
        @ApiKeyAuthDefinition(key = "Authorization", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER, name = "Authorization") }
//        ,
//        oAuth2Definitions = {
//                @OAuth2Definition(flow = OAuth2Definition.Flow.PASSWORD, key = "OAuth2", authorizationUrl= "/aouth")
//        }
))

@RolesAllowed({ "admin", "member" })
@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/api/orders")
public class OrderResource {
    
    public static Logger log = LoggerFactory.getLogger(OrderResource.class);

    private final OrderService orderService;

    private OrderAssembler orderAssembler;

    @Inject
    public OrderResource(OrderService orderService, OrderAssembler assembler) {

        this.orderAssembler = assembler;
        this.orderService = orderService;
    }

    @POST
    @UnitOfWork
    @ApiOperation(value = "Add order", response = OrderOutputDto.class, notes = "Agregate new Order", authorizations = {
            @Authorization(value = "Authorization") })
    public Response create(@Valid OrderInputDto orderDto, @ApiParam(hidden = true) @Auth ApplicationUser user) {

        try {
            Order order = orderAssembler.toEntity(orderDto);

            order.setBuyerid(user.getId());

            order = orderService.create(order);

            OrderOutputDto dto = orderAssembler.toDto(order);

            return Response.ok().entity(dto).status(HttpStatus.CREATED_201).build();

        } catch (IllegalArgumentException e) {
            
            log.error(e.getMessage(), e);

            ErrorDto error = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY_422, e.getMessage());

            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(error).build();

        } catch (Exception e) {
            
            log.error(e.getMessage(), e);

            return Response.serverError().build();

        }
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "Remove order", response = String.class, notes = "Remove Order by id order", authorizations = {
            @Authorization(value = "Authorization") })
    public Response delete(@ApiParam(hidden = true) @Auth ApplicationUser user,
            @ApiParam(value = "id order", required = true) @PathParam("id") Long id) {

        try {
            orderService.delete(id);

            Map<String, Object> responseOK = new HashMap<>();

            responseOK.put("message", "Correct delete order");

            return Response.ok().entity(responseOK).build();

        } catch (IllegalArgumentException e) {
            
            log.error(e.getMessage(), e);

            ErrorDto error = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY_422, e.getMessage());

            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(error).build();

        } catch (Exception e) {
            
            log.error(e.getMessage(), e);

            return Response.serverError().build();

        }
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "show order", response = OrderOutputDto.class, notes = "Show Order by id order", authorizations = {
            @Authorization(value = "Authorization") })
    public Response ordersList(@ApiParam(hidden = true) @Auth ApplicationUser user,
            @ApiParam(value = "id order", required = true) @PathParam("id") Long id) {

        try {
            Order order = orderService.orderById(id);

            OrderOutputDto dto = orderAssembler.toDto(order);

            return Response.ok().entity(dto).build();

        } catch (IllegalArgumentException e) {
            
            log.error(e.getMessage(), e);

            ErrorDto error = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY_422, e.getMessage());

            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(error).build();

        } catch (Exception e) {
            
            log.error(e.getMessage(), e);

            return Response.serverError().build();

        }
    }

    @GET
    @UnitOfWork
    @ApiOperation(value = "List orders", notes = "List all orders", responseContainer = "List", response = OrderHeaderOutputDto.class, authorizations = {
            @Authorization(value = "Authorization") })
    public Response ordersList(@ApiParam(hidden = true) @Auth ApplicationUser user) {

        try {

            List<OrderHeaderOutputDto> dtos = orderAssembler.toHeaderDto(orderService.findAllByBuyerid(user.getId()));

            return Response.ok().entity(dtos).build();

        } catch (IllegalArgumentException e) {
            
            log.error(e.getMessage(), e);

            ErrorDto error = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY_422, e.getMessage());

            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(error).build();

        } catch (Exception e) {
            
            log.error(e.getMessage(), e);

            return Response.serverError().build();

        }

    }
}
