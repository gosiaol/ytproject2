package com.agh.olszewska;


import com.agh.olszewska.controller.ChannelController;
import com.agh.olszewska.controller.HomeController;
import com.agh.olszewska.controller.SearchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YTprojectApplicationTests {

	@Autowired
	private ChannelController channelController;

	@Autowired
	HomeController homeController;

	@Autowired
	SearchController searchController;

	@Test
	public void contextLoads() {
		assertThat(channelController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(searchController).isNotNull();

	}



}
