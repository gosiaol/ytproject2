package com.agh.olszewska.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class serves as a service for channel on YouTube.
 *
 * @author Olszew
 */

@Service
public class YtChannelService {

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static YouTube youtube;
    @Value("${youtube.apikey}")
   private String apiKey;

    /**
     * Check existing of channel for user.
     * @throws Exception
     * @param accessToken
     * @return channelExisting
     */
    public boolean checkChanelExisting(final String accessToken) throws IOException {

        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);


        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("youtubemanager")
                .build();

        ChannelListResponse channelListResponse = youtube.channels().list("snippet, status, contentDetails").setKey(apiKey).setMine(true).execute();

        Channel channel = channelListResponse.getItems().get(0);

        return channel.getStatus().getIsLinked();

    }


}
