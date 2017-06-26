package com.agh.olszewska.controller;


import com.agh.olszewska.model.InitModel;
import com.agh.olszewska.model.StartData;
import com.agh.olszewska.model.YtPlaylist;
import com.agh.olszewska.service.YtChannelService;
import com.agh.olszewska.service.YtPlaylistItemService;
import com.agh.olszewska.service.YtPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * RestController for request about channel
 *
 * @author Olszew
 */
@RestController
public class ChannelController {

    @Autowired
    private YtChannelService ytChannelService;

    @Autowired
    private YtPlaylistService ytPlaylistService;

    @Autowired
    private YtPlaylistItemService ytPlaylistItemService;

    /**
     * Return info about channel.
     * @throws IOException
     * @param initModel -user access Token
     * @return information about channel existing
     */
    @PostMapping(value = "/start", produces = "application/json")
    public final StartData start(@RequestBody final InitModel initModel) throws IOException {
        StartData startData = new StartData();
        startData.setChannelExisting(ytChannelService.checkChanelExisting(initModel.getAccessToken()));

        return startData;


    }

    /**
     * Return playlists created by application.
     * @throws IOException
     * @param initModel -user access Token
     * @return created playlists
     */
    @PostMapping(value = "/start2", produces = "application/json")
    public final List<YtPlaylist> start2(@RequestBody final InitModel initModel) throws IOException {


        return ytPlaylistItemService.getPlaylistItems(initModel.getAccessToken(), ytPlaylistService.getCreatedPlaylists(initModel.getAccessToken()));


    }


}
