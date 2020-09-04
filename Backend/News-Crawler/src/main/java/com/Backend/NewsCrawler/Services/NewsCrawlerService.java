package com.Backend.NewsCrawler.Services;

import com.Backend.NewsCrawler.Models.NewsDetails;
import com.Backend.NewsCrawler.Repositories.NewsCrawlerRepository;
import com.Backend.NewsCrawler.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Slf4j
@Service
public class NewsCrawlerService {

    @Autowired
    NewsCrawlerRepository newsCrawlerRepository;

    public List<NewsDetails> attemptCrawl(String topic) {
        try {
            URL url = new URL(Utils.PREFIX_SEARCH + Utils.SUFFIX_SEARCH);
            URLConnection urlConnection = url.openConnection();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return newsCrawlerRepository.newsCrawl(topic);
    }
}
