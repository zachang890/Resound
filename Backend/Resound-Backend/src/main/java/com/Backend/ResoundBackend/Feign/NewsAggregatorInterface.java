package com.Backend.ResoundBackend.Feign;

import com.Backend.ResoundBackend.Models.NewsDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "news-aggregator", url = "http://localhost:8081/") //Name matches the one in eureka, url matches port of service
public interface NewsAggregatorInterface {

    @GetMapping("/news-aggregator/{topic}") //Mapping matches mapping in the service
    public List<NewsDetails> retrieveNewsByTopic(@PathVariable("topic") String topic);
}
