package nl.dani.han.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

	@GET
	public Response getAllPlaylists() {
		return Response.status(Response.Status.OK).build();
	}
}