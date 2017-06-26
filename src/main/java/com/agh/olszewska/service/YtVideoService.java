package com.agh.olszewska.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoStatistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class serves as a service for videos on YouTube.
 *
 * @author Olszew
 */
@Service
public class YtVideoService {


    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static YouTube youtube;
    @Value("${youtube.apikey}")
   private String apiKey;

    /**
     * Get IDs of videos.
     */

    static private List<String> getVideosId(final List<SearchResult> resultList) {

        List<String> listOfVideoIds = new ArrayList<String>();
        for (SearchResult searchResult : resultList) {
            String videoId = searchResult.getId().getVideoId();
            listOfVideoIds.add(videoId);

        }
        return listOfVideoIds;

    }

    /**
     * Checking rank of Videos.
     */

    static public List<String> checkRankOfVideo(VideoListResponse videoListResponse) {

        List<String> rankedVideosRequest = new ArrayList<String>();
        for (Video video : videoListResponse.getItems()) {
            VideoStatistics videoStatistics = video.getStatistics();

            int rateRatio = 0;
            if (videoStatistics != null && videoStatistics.getLikeCount() != null && videoStatistics.getDislikeCount() != null) {
                rateRatio = videoStatistics.getLikeCount().compareTo(videoStatistics.getDislikeCount().multiply(new BigInteger("4")));
                System.out.println(rateRatio);
            }
            if ((rateRatio > 0)) {
                rankedVideosRequest.add(video.getId());

            }
            if (rankedVideosRequest.size() == 20) {
                return rankedVideosRequest;
            }
        }


        return rankedVideosRequest;
    }

    static private String stringParser(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    /**
     * Get filtred videos.
     */

    public List<String> videos(List<SearchResult> resultList) throws IOException {
        List<String> listOfVideoIds = getVideosId(resultList);


        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtubeManager").build();
        YouTube.Videos.List listVideosRequest = youtube.videos().list("id,statistics");

        listVideosRequest.setId(stringParser(listOfVideoIds.toString()));

        listVideosRequest.setKey(apiKey);
        listVideosRequest.setFields("items(id,statistics/likeCount,statistics/dislikeCount,statistics/viewCount)");
        VideoListResponse listResponse = listVideosRequest.execute();

        List<String> list = checkRankOfVideo(listResponse);

        return list;


    }
}


