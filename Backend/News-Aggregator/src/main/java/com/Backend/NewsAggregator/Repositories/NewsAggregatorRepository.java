package com.Backend.NewsAggregator.Repositories;

import com.Backend.NewsAggregator.Models.NewsDetails;
import com.Backend.NewsAggregator.Utils.Utils;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class NewsAggregatorRepository {

    public List<NewsDetails> crawlNews(String topic) {
        if (topic.length() > 0) {

//            String headline;
//            try {
//                String[] command = {Utils.PYTHON_COMMAND, Utils.PATH_TO_SCRIPT, topic};
//                Process process = Runtime.getRuntime().exec(command);
//                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//                List<NewsDetails> toReturn = new ArrayList<>();
//
//                while ((headline = input.readLine()) != null)
//                {
//                    if (headline.equals(Utils.EMPTY_STRING) || headline.equals(Utils.NEW_LINE))
//                        continue;
//                    NewsDetails curr = new NewsDetails(headline, input.readLine(), input.readLine(), input.readLine());
//                    toReturn.add(curr);
//                }
//                return toReturn;
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
        }
        return new ArrayList<>();
    }
}
