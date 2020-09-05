package com.Backend.ResoundBackendMain.Controllers;


import com.Backend.ResoundBackendMain.Interfaces.DynamoInterface;
import com.Backend.ResoundBackendMain.Models.NewsDetailsDynamo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/news-crawler")
public class NewsCrawlerController {

    @Autowired
    DynamoInterface dynamoInterface;

    @GetMapping("/{topic}")
    public NewsDetailsDynamo retrieveCachedTopic(@PathVariable("topic") String topic) {
        return dynamoInterface.retrieveNewsDetails(topic);
    }

}
