package nl.dani.han.resources;

import javax.validation.constraints.Past;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import nl.dani.han.dtos.UserDTO;

@Path("/user")
public class UserResource {

	@POST
	public Response login(UserDTO user) {
		// call service for login

		// call service to generate token

		return Response.status(Response.Status.OK).build();
	}
}