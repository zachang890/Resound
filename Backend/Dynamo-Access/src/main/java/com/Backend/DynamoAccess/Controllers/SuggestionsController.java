package com.Backend.DynamoAccess.Controllers;

import com.Backend.DynamoAccess.DBLayer.SuggestionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/dynamo")
public class SuggestionsController {

    @Autowired
    SuggestionsRepository suggestionsRepository;

    @GetMapping("/suggestions/topics-list")
    public List<String> retrieveTopics() {
        return suggestionsRepository.retrieveTopics();
    }
}
