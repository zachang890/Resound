package com.Backend.NewsCrawler.Interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "news-crawler", url = "https://04mi7h7oo3.execute-api.us-west-2.amazonaws.com/alpha1/")
public interface AWSLambdaInterface {

    @GetMapping("/news-aggregator-bs4")
    public String newsCrawl(@RequestParam String topic);
}
