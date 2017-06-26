package com.agh.olszewska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Application for created playlist with selected videos.
 *
 * @author Olszew
 */
@SpringBootApplication
public class YTprojectApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YTprojectApplication.class, args);
    }


    /**
     * Application for created playlist with selected videos.
     *
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YTprojectApplication.class);
    }
}
