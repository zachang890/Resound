package com.Backend.DynamoAccess.Controllers;

import com.Backend.DynamoAccess.DBLayer.NewsDetailsRepository;
import com.Backend.DynamoAccess.Models.NewsDetailsDynamo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/dynamo")
public class NewsController {

    @Autowired
    private NewsDetailsRepository newsDetailsRepository;

    @PostMapping("/save-details")
    public NewsDetailsDynamo saveNewsDetails(@RequestBody NewsDetailsDynamo newsDetailsDynamo) {
        return newsDetailsRepository.addNewsDetails(newsDetailsDynamo);
    }

    @GetMapping("/details/{topic}")
    public NewsDetailsDynamo findNewsDetails(@PathVariable("topic") String topic) {
        return newsDetailsRepository.findNewsDetails(topic);
    }

    @DeleteMapping("/remove")
    public String deleteNewsDetails(@RequestBody NewsDetailsDynamo newsDetailsDynamo) {
        return newsDetailsRepository.deleteNewsDetails(newsDetailsDynamo);
    }

    @PutMapping("/revisit-details")
    public String updateNewsDetails(@RequestBody NewsDetailsDynamo newsDetailsDynamo) {
        return newsDetailsRepository.updateNewsDetails(newsDetailsDynamo);
    }
}
