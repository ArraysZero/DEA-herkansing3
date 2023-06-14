package nl.dani.han.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.dtos.PlayListListDTO;

@Path("/playlists")
public class PlaylistResource {
	private PlaylistDAO playlistDAO = new PlaylistDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlaylists() {
		try {
			var playlists = playlistDAO.getAllPlaylists();
			return Response.status(Response.Status.OK).entity(playlists).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}