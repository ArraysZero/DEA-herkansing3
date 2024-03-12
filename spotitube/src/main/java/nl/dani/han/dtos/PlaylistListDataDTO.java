package nl.dani.han.dtos;

import java.util.List;

public class PlaylistListDataDTO {

    private List<PlaylistDataDTO> playlists;

    public PlaylistListDataDTO(List<PlaylistDataDTO> playlists) {
        this.playlists = playlists;
    }

    public PlaylistListDataDTO() {
    }

    public List<PlaylistDataDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDataDTO> playlists) {
        this.playlists = playlists;
    }
}
