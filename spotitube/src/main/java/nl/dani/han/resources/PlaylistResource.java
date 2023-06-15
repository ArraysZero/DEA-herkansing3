package nl.dani.han.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.services.PlaylistService;

@Path("/playlists")
public class PlaylistResource {
	private PlaylistService playlistService = new PlaylistService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPlaylists() {
		try {
			var playlists = playlistService.getAllPlaylists();
			return Response.status(Response.Status.OK).entity(playlists).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deletePlaylist(@PathParam("id") Integer id) { // TODO functie het laten doen
		try {
			return Response.status(Response.Status.OK).entity(playlistService.deletePlaylist(id)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPlaylist(PlayListDTO playlist) {
		try {
			return Response.status(Response.Status.OK).entity(playlistService.addPlaylist(playlist)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editPlaylist(@PathParam("id") Integer id, PlayListDTO playlist) {
		try {
			return Response.status(Response.Status.OK).entity(playlistService.editPlaylist(id, playlist)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}/tracks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrackListPlaylistId(@PathParam("id") Integer id) {
		try {
			return Response.status(Response.Status.OK).entity(playlistService.getTrackList(id)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/{id}/tracks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTrackPlaylistId(@PathParam("id") Integer id, TrackDTO track) {
		try {
			return Response.status(Response.Status.OK).entity(playlistService.addTrack(id, track)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}/tracks/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTrackPlaylistId(@PathParam("id") Integer playlistId, @PathParam("id") Integer trackId) {
		try {
			return Response.status(Response.Status.OK).entity(playlistService.deleteTrack(playlistId, trackId)).build();
		} catch (PlaylistException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}