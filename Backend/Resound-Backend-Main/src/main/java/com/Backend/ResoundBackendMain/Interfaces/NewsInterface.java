package com.Backend.ResoundBackendMain.Interfaces;

import com.Backend.ResoundBackendMain.Models.NewsDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "news-crawler-service", url = "http://news-crawler-service.eba-24mqhk9g.us-west-2.elasticbeanstalk.com")
public interface NewsInterface {

    @GetMapping("/news-aggregator/{topic}")
    public List<NewsDetails> crawlNews(@PathVariable String topic);
}
