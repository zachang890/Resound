package com.Backend.ResoundBackendMain.Controllers;

import com.Backend.ResoundBackendMain.Interfaces.DynamoInterface;
import com.Backend.ResoundBackendMain.Models.ConvertToJSON;
import com.Backend.ResoundBackendMain.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/suggestions")
public class SuggestionsController {

    @Autowired
    DynamoInterface dynamoInterface;

    @GetMapping("/today-topics-list")
    public ConvertToJSON retrieveTopics() {
        Random rand = new Random();
        List<String> preProcess = dynamoInterface.retrieveTopicsList();
        List<String> postProcess = new ArrayList<>();
        for (int i = 0; i < Utils.RAND_THRESHOLD; i++) {
            int nextRand = rand.nextInt(preProcess.size());
            postProcess.add(preProcess.get(nextRand));
            preProcess.remove(nextRand);
        }
        return new ConvertToJSON(postProcess);
    }

    @PutMapping("/daily-topic-change")
    public void dailyTopicChange() {

    }
}
