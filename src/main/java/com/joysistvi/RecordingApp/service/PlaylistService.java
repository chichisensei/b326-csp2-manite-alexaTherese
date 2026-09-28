package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;

import java.util.List;

public interface PlaylistService {

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
