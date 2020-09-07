package com.Backend.ResoundBackendMain.Interfaces;

import com.Backend.ResoundBackendMain.Models.NewsDetailsDynamo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "dynamo-access", url = "http://dynamo-access.eba-24mqhk9g.us-west-2.elasticbeanstalk.com")
public interface DynamoInterface {

    @GetMapping("/dynamo/details/{topic}")
    public NewsDetailsDynamo retrieveNewsDetails(@PathVariable("topic") String topic);

    @GetMapping("/dynamo/suggestions/today-topics")
    public List<String> retrieveTopicsList();
}
