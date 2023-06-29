package nl.dani.han.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import nl.dani.han.exceptions.TrackException;

@Provider
public class TrackExceptionMapper implements ExceptionMapper<TrackException> {

	@Override
	public Response toResponse(TrackException arg0) {
		return Response.status(Response.Status.FORBIDDEN).entity(arg0.getMessage()).build();
	}

}