package nl.dani.han.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.exceptions.TrackException;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.TrackService;

@Path("/tracks")
public class TrackResource {

	@Inject
	private TrackService trackService;

	@Inject
	private LoginService authentication;

	@GET
	public Response getAvailableTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlist)
			throws LoginException, TrackException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(trackService.getAvailableTracks(playlist)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	public void setAuthentication(LoginService authentication) {
		this.authentication = authentication;
	}

}