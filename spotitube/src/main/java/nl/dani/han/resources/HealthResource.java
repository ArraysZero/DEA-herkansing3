package nl.dani.han.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

	@GET
	public Response healthCheck() {
		return Response.status(Response.Status.OK).entity("up and running").build();
	}

}