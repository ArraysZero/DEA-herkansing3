package nl.dani.han.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;
//import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.PlaylistService;

@Path("/playlists")
public class PlaylistResource {
	@Inject
	private PlaylistService playlistService;

	@Inject
	private LoginService authentication;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlaylists(@QueryParam("token") String token)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.getAllPlaylists()).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") Integer id)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.deletePlaylist(id)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlaylist(@QueryParam("token") String token, PlayListDTO playlist)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.addPlaylist(playlist)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") Integer id, PlayListDTO playlist)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.editPlaylist(playlist)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@GET
	@Path("/{id}/tracks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrackListPlaylistId(@QueryParam("token") String token, @PathParam("id") Integer id)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.getTrackList(id)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@POST
	@Path("/{id}/tracks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTrackPlaylistId(@QueryParam("token") String token, @PathParam("id") Integer id, TrackDTO track)
			throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.addTrack(id, track)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	@DELETE
	@Path("{playlistid}/tracks/{trackid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTrackPlaylistId(@QueryParam("token") String token,
			@PathParam("playlistid") Integer playlistId,
			@PathParam("trackid") Integer trackId) throws LoginException, DataAccessException {
		if (authentication.tokenExists(token)) {
			return Response.status(Response.Status.OK).entity(playlistService.deleteTrack(playlistId, trackId)).build();
		} else {
			throw new LoginException("token does not exist");
		}
	}

	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	public void setAuthentication(LoginService authentication) {
		this.authentication = authentication;
	}

}