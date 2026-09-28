package com.joysistvi.RecordingApp.controller;

import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;
import com.joysistvi.RecordingApp.service.PlaylistService;

import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public boolean handleCreatePlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    public List<Playlist> createGetAllPlaylist() {
        return playlistService.getAllPlaylist();
    }

    public List<Playlist> handleSearchPlaylist(String keyword) {
        return playlistService.searchPlaylist(keyword);
    }

    public List<Playlist> handleGetPlaylistByUserId(User user) {
        return playlistService.getPlaylistByUserId(user);
    }

    public boolean handleUpdatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    public boolean handleArchivePlaylist(int id) {
        return playlistService.archivePlaylist(id);
    }

    public List<Playlist> handleCetAllArchivedPlaylist() {
        return playlistService.getAllArchivedPlaylist();
    }

    public boolean handleRestoreArchivedPlaylist(int id) {
        return playlistService.restoreArchivedPlaylist(id);
    }

    public boolean handleDeletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }
}
