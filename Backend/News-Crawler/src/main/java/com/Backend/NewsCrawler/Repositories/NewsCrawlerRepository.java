package com.Backend.NewsCrawler.Repositories;

import com.Backend.NewsCrawler.Interfaces.AWSLambdaInterface;
import com.Backend.NewsCrawler.Models.NewsDetails;
import com.Backend.NewsCrawler.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class NewsCrawlerRepository {

    @Autowired
    AWSLambdaInterface awsLambdaInterface;

    public List<NewsDetails> newsCrawl(String topic) {
        String news = awsLambdaInterface.newsCrawl(topic);
        news = news.replace("\"", "");

        //Process the news into objects
        if (news.length() > 0) {
            int startFrom = 0;
            int indexOf = 0;
            int flag = 0;
            List<NewsDetails> newsObjects = new ArrayList<>();
            while (flag < Utils.NEWS_DETAILS_FIELDS) {
                NewsDetails current = new NewsDetails();

                indexOf = news.indexOf("\\n", startFrom);
                current.setHeadline(news.substring(startFrom, indexOf));
                startFrom = indexOf + 2;

                indexOf = news.indexOf("\\n", startFrom);
                current.setUrl(news.substring(startFrom, indexOf));
                startFrom = indexOf + 2;

                indexOf = news.indexOf("\\n", startFrom);
                current.setSource(news.substring(startFrom, indexOf));
                startFrom = indexOf + 2;

                indexOf = news.indexOf("\\n", startFrom);
                current.setTime(news.substring(startFrom, indexOf));
                startFrom = indexOf + 2;

                flag += 1;
                newsObjects.add(current);
            }
            return newsObjects;
        }
        return new ArrayList<>();
    }
}
