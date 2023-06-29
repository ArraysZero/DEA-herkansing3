package nl.dani.han.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import nl.dani.han.exceptions.DataAccessException;

@Provider
public class DataAccessExceptionMapper implements ExceptionMapper<DataAccessException> {

	@Override
	public Response toResponse(DataAccessException arg0) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Data Access exception caused by: " + arg0.getMessage()).build();
	}

}