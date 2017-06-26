package com.agh.olszewska.service;


import com.agh.olszewska.model.AnswerData;
import com.agh.olszewska.model.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Class serves as service for others services.
 *
 * @author Olszew
 */
@Service
public class MainService {

    @Autowired
    private YtSearchService ytSearchService;
    @Autowired
    private YtVideoService ytVideoService;

    @Autowired
    private YtPlaylistService ytPlaylistService;


    /**
     * Class represents answer for user.
     * @param searchData
     * @return answer for user
     */
    public AnswerData api(final SearchData searchData) {
        AnswerData answer = new AnswerData();


        try {
            List searchList = ytSearchService.search(searchData);

            List searchListFiltred = ytVideoService.videos(searchList);
            ytPlaylistService.createPlaylist(searchListFiltred, searchData);
        } catch (IOException e) {
            e.printStackTrace();
            answer.setMessage("Exception");
        }


        return answer;
    }
}
