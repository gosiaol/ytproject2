package com.agh.olszewska;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.agh.olszewska.controller.SearchController;
import com.agh.olszewska.model.AnswerData;
import com.agh.olszewska.model.SearchData;
import com.agh.olszewska.service.MainService;
import com.google.api.client.json.Json;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainService mainService;



    @Mock
    SearchData searchData;



    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
     //  given(mainService.api(searchData)).willReturn(new AnswerData());
       // this.mockMvc.perform(post("/api3",new SearchData())).andDo(print()).andExpect(status().isOk());

    }


}