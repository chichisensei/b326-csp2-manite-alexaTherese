package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;

import java.util.List;

public interface PlaylistRepo {
    // CRUD
    boolean createPlaylist(Playlist playlist);
    List<Playlist> getAllPlaylist();
    List<Playlist> searchPlaylist(String keyword);
    List<Playlist> getPlaylistByUserId(User user);
    boolean updatePlaylist(Playlist playlist);
    boolean archivePlaylist(int id);
    List<Playlist> getAllArchivedPlaylist();
    boolean restoreArchivedPlaylist(int id);
    boolean deletePlaylist(int id);

}
