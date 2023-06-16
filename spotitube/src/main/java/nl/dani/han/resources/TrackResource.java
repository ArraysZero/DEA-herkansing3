package nl.dani.han.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import nl.dani.han.exceptions.TrackException;
import nl.dani.han.services.TrackService;

@Path("/tracks")
public class TrackResource {

	@Inject
	TrackService trackService;

	@GET
	public Response getAvailableTracks() {
		System.out.println(trackService);
		try {
			return Response.status(Response.Status.OK).entity(trackService.getAvailableTracks(0)).build();
		} catch (TrackException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	public TrackService getTrackService() {
		return trackService;
	}
}