package com.joysistvi.RecordingApp.service;

import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;
import com.joysistvi.RecordingApp.repo.PlaylistRepo;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepo playlistRepo;

    public PlaylistServiceImpl(PlaylistRepo playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {
        if (playlist.getName() == null || playlist.getName().trim().isEmpty()) {
            System.out.println("Playlist name is required!");
            return false;
        }
        return true;
    }

    @Override
    public List<Playlist> getAllPlaylist() {
        if (playlistRepo.getAllPlaylist() == null || playlistRepo.getAllPlaylist().isEmpty()) {
            System.out.println("No playlist. Add some playlists right now!");
            return List.of();
        }
        return playlistRepo.getAllPlaylist();
    }

    @Override
    public List<Playlist> searchPlaylist(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Keyword is required!");
            return List.of();
        }
        return playlistRepo.searchPlaylist(keyword);

    }

    @Override
    public List<Playlist> getPlaylistByUserId(User user) {
        if (playlistRepo.getPlaylistByUserId(user) == null || playlistRepo.getPlaylistByUserId(user).isEmpty()) {
            System.out.println("No playlist. Add some playlists right now!");
            return List.of();
        }
        return playlistRepo.getPlaylistByUserId(user);

    }

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        if (playlist.getName() == null || playlist.getName().trim().isEmpty()) {
            System.out.println("Playlist name is required!");
            return false;
        }
        return true;
    }

    @Override
    public boolean archivePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Invalid input!");
            return false;
        }
        return true;
    }

    @Override
    public List<Playlist> getAllArchivedPlaylist() {
        if (playlistRepo.getAllArchivedPlaylist() == null) {
            System.out.println("No archived playlist.");
            return List.of();
        }
        return playlistRepo.getAllArchivedPlaylist();

    }

    @Override
    public boolean restoreArchivedPlaylist(int id) {
        if (id <= 0) {
            System.out.println("Invalid input!");
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Invalid input!");
            return false;
        }
        return true;
    }
}
