package com.agh.olszewska.service;

import com.agh.olszewska.model.YtPlaylist;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Class serves as a service for get videos from YouTube.
 *
 * @author Olszew
 */
@Service
public class YtPlaylistItemService {

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static YouTube youtube;
    private final int maxResults=20;

    /**
     * Get videos of playlists created by application.
     * @param accessToken
     * @param playlists
     * @return playlists
     */
    public List<YtPlaylist> getPlaylistItems(final String accessToken, final List<Playlist> playlists) throws IOException {


        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);


        // This object is used to make YouTube Data API requests.
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("youtubemanager")
                .build();

        List<YtPlaylist> playlistItemList = new ArrayList<YtPlaylist>();

        for (Playlist playlist : playlists) {
            YtPlaylist ytPlaylist = new YtPlaylist();
            ytPlaylist.setTitle(playlist.getSnippet().getTitle());
            ytPlaylist.setPlaylistId("https://www.youtube.com/playlist?list=" + playlist.getId());
            ytPlaylist.setDescription(playlist.getSnippet().getDescription());
            YouTube.PlaylistItems.List playlistItemRequest =
                    youtube.playlistItems().list("id,contentDetails,snippet");
            playlistItemRequest.setPlaylistId(playlist.getId());
            playlistItemRequest.setMaxResults((long) maxResults);
            playlistItemRequest.setFields("items/id, items/snippet/publishedAt, items/snippet/title");
            PlaylistItemListResponse playlistItemListResponse = playlistItemRequest.execute();
            ytPlaylist.setPlaylistItems(playlistItemListResponse.getItems());
            playlistItemList.add(ytPlaylist);
        }


        return playlistItemList;

    }


}
