package com.Backend.ResoundBackendMain.Controllers;

import com.Backend.ResoundBackendMain.Interfaces.DynamoInterface;
import com.Backend.ResoundBackendMain.Models.ConvertToJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/suggestions")
public class SuggestionsController {

    @Autowired
    DynamoInterface dynamoInterface;

    @GetMapping("/today-topics-list")
    public ConvertToJSON retrieveTopics() {
        List<String> postProcess = dynamoInterface.retrieveTopicsList();
        return new ConvertToJSON(postProcess);
    }
}
