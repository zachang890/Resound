package com.Backend.NewsCrawler.Controllers;

import com.Backend.NewsCrawler.Models.NewsDetails;
import com.Backend.NewsCrawler.Services.NewsCrawlerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-aggregator")
@Slf4j
public class NewsCrawlerController {
    @Autowired
    NewsCrawlerService newsCrawlerService;

    @GetMapping("/{topic}") //convert to hbs file
    @ApiOperation(value = "Retrieve most recent headlines", notes = "Provide topic to retrieve headlines", response = List.class)
    public List<NewsDetails> newsByTopic(@ApiParam(value = "News topic", required = true) @PathVariable("topic") String topic) {
        return newsCrawlerService.attemptCrawl(topic);
    }
}
