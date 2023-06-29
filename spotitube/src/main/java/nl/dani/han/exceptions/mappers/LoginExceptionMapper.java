package nl.dani.han.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import nl.dani.han.exceptions.LoginException;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<LoginException> {

	@Override
	public Response toResponse(LoginException arg0) {
		return Response.status(Response.Status.UNAUTHORIZED).entity(arg0.getMessage()).build();
	}

}