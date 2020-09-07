package com.Backend.DynamoAccess.Controllers;

import com.Backend.DynamoAccess.DBLayer.NewsDetailsRepository;
import com.Backend.DynamoAccess.Models.NewsDetailsDynamo;
import com.Backend.DynamoAccess.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dynamo")
public class NewsController {

    @Autowired
    NewsDetailsRepository newsDetailsRepository;

    @PostMapping("/save-details/{topic}") //Structure of get
    public NewsDetailsDynamo saveNewsDetails(@PathVariable(Utils.TOPIC) String topic) {
        return newsDetailsRepository.addNewsDetails(topic);
    }

    @GetMapping("/details/{topic}")
    public NewsDetailsDynamo findNewsDetails(@PathVariable(Utils.TOPIC) String topic) {
        return newsDetailsRepository.findNewsDetails(topic);
    }

    @DeleteMapping("/remove")
    public String deleteNewsDetails(@RequestBody NewsDetailsDynamo newsDetailsDynamo) {
        return newsDetailsRepository.deleteNewsDetails(newsDetailsDynamo);
    }

    @PutMapping("/revisit-details/{topic}")
    public String updateNewsDetails(@PathVariable(Utils.TOPIC) String topic) {
        return newsDetailsRepository.updateNewsDetails(topic);
    }
}
