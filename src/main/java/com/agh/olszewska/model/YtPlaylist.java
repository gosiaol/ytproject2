package com.agh.olszewska.model;

import com.google.api.services.youtube.model.PlaylistItem;

import java.util.List;

/**
 * Class represents objects of created playlists
 *
 * @author Olszew
 */
public class YtPlaylist {

  private   String title;

   private String description;

    private String playlistId;

    private List<PlaylistItem> playlistItems;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlaylistItem> getPlaylistItems() {
        return playlistItems;
    }

    public void setPlaylistItems(List<PlaylistItem> playlistItems) {
        this.playlistItems = playlistItems;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
