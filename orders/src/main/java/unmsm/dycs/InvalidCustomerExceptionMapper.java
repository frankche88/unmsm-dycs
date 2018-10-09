package unmsm.dycs;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import unmsm.dycs.orders.application.InvalidCustomerException;

import java.util.HashMap;
import java.util.Map;

@Provider
public class InvalidCustomerExceptionMapper implements ExceptionMapper<InvalidCustomerException> {
  @Override
  public Response toResponse(InvalidCustomerException exception) {
    Map<String, Object> data = new HashMap<>();
    data.put("message", exception.getMessage());
    data.put("id", exception.getCustomerId());
    return Response.status(422).entity(data).type(MediaType.APPLICATION_JSON_TYPE).build();
  }
}
