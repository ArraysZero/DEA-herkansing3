package nl.dani.han.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.services.LoginService;

@Path("/login")
public class LoginResource {

	private LoginService loginService = new LoginService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDTO user) {
		try {
			var response = loginService.login(user);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (LoginException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getCause()).build();

		}
	}
}