package com.Backend.ResoundBackend.Controllers;

import com.Backend.ResoundBackend.Feign.NewsAggregatorInterface;
import com.Backend.ResoundBackend.Models.NewsDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/news-aggregator")
public class NewsAggregatorController {

    @Autowired
    NewsAggregatorInterface newsAggregatorInterface;

    @GetMapping("/{topic}")
    public List<NewsDetails> newsByTopic(@PathVariable("topic") String topic) {
        return newsAggregatorInterface.retrieveNewsByTopic(topic);
    }
}
