package com.agh.olszewska.controller;


import com.agh.olszewska.model.AnswerData;
import com.agh.olszewska.model.SearchData;
import com.agh.olszewska.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController for get search requests.
 *
 * @author Olszew
 */
@RestController
public class SearchController {

    @Autowired
   private MainService mainService;


    /**
     * Search videos by params.
     * @param searchData
     * @return answet data for user
     */
    @PostMapping(value = "/api3", produces = "application/json", consumes = "application/json")
    public final AnswerData api(@RequestBody final SearchData searchData) {


        return mainService.api(searchData);
    }


}
