package com.Backend.ResoundBackendMain.Controllers;


import com.Backend.ResoundBackendMain.Interfaces.NewsInterface;
import com.Backend.ResoundBackendMain.Models.NewsDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/news-crawler")
public class NewsCrawlerController {

    @Autowired
    NewsInterface newsInterface;

    @GetMapping("/{topic}")
    public List<NewsDetails> newsCrawl(@PathVariable("topic") String topic) { //Load into dynamo as a cache service
        return newsInterface.crawlNews(topic);
    }

}
