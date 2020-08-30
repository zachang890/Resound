package com.Backend.NewsAggregator.Controllers;

import com.Backend.NewsAggregator.Models.NewsDetails;
import com.Backend.NewsAggregator.Services.NewsAggregatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news-aggregator")
@Slf4j
public class NewsAggregatorController {

    @Autowired
    NewsAggregatorService newsAggregatorService;

    @GetMapping("/{topic}") //convert to hbs file
    @ApiOperation(value = "Retrieve most recent headlines", notes = "Provide topic to retrieve headlines", response = List.class)
    public List<NewsDetails> newsByTopic(@ApiParam(value = "News topic", required = true) @PathVariable("topic") String topic) {
        return newsAggregatorService.attemptCrawl(topic);
    }
}
