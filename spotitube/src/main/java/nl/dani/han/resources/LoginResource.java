package nl.dani.han.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.services.LoginService;

@Path("/login")
public class LoginResource {

	@Inject
	private LoginService loginService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDTO user) throws LoginException, DataAccessException {
		var response = loginService.login(user);
		return Response.status(Response.Status.OK).entity(response).build();
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}