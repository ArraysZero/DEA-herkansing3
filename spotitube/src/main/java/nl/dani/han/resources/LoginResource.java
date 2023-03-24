package nl.dani.han.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.daos.DataAccessException;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.ServiceException;
import nl.dani.han.requests.LoginRequest;

@Path("/login")
public class LoginResource {

	@Inject
	private LoginService loginService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest request) {
		// if (loginService == null) {
		// loginService = new LoginService();
		// }

		// return Response.status(Response.Status.OK).build();
		UserDTO user = new UserDTO(request.user, request.password);
		String token;
		try {
			token = loginService.login(user.getUsername(), user.getPassword());

			return Response.status(Response.Status.OK).entity(token).build();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("oeps").build();
		}
	}
}