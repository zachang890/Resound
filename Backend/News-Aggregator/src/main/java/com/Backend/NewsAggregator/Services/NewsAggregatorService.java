package com.Backend.NewsAggregator.Services;

import com.Backend.NewsAggregator.Repositories.NewsAggregatorRepository;
import com.Backend.NewsAggregator.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
@Service
public class NewsAggregatorService {

    @Autowired
    NewsAggregatorRepository newsAggregatorRepository;

    public String attemptCrawl(String topic) {
        try {
            URL url = new URL(Utils.PREFIX_SEARCH + Utils.SUFFIX_SEARCH);
            URLConnection urlConnection = url.openConnection();
        } catch (IOException e) {
            log.error(e.getMessage());
            return Utils.CONNECTION_FAILED;
        }
        return newsAggregatorRepository.crawlNews(topic);
    }
}
