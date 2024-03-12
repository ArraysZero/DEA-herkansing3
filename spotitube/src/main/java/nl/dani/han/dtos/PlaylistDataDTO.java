package nl.dani.han.dtos;

public class PlaylistDataDTO {

    private int id;
    private String name;
    private String owner;
    private TrackListDTO tracks;

    public PlaylistDataDTO(int id, String name, String owner, TrackListDTO tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public PlaylistDataDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public TrackListDTO getTracks() {
        return tracks;
    }

    public void setTracks(TrackListDTO tracks) {
        this.tracks = tracks;
    }
}
