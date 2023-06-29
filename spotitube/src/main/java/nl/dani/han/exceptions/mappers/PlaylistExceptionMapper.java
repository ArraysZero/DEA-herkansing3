package nl.dani.han.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import nl.dani.han.exceptions.PlaylistException;

@Provider
public class PlaylistExceptionMapper implements ExceptionMapper<PlaylistException> {

	@Override
	public Response toResponse(PlaylistException arg0) {
		return Response.status(Response.Status.FORBIDDEN).entity(arg0.getMessage()).build();
	}

}