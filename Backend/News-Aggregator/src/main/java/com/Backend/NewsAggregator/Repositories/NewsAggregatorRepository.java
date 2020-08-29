package com.Backend.NewsAggregator.Repositories;

import com.Backend.NewsAggregator.Utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Repository
@Slf4j
public class NewsAggregatorRepository {

    public String crawlNews(String topic) {
        if (topic.length() > 0) {
            String headlines = "";
            try {
                String[] command = {"python3", "src/main/java/com/Backend/NewsAggregator/PythonScripts/rake-headlines-bs4.py", topic};
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String toReturn = "";

                while ((headlines = input.readLine()) != null)
                {
                    toReturn = toReturn + headlines + "\n";
                }
                return toReturn;
            } catch (IOException e) {
                log.error(e.getMessage());
                return Utils.READ_LINE_FAILED;
            }
        }
        return Utils.SHORT_INPUT;
    }
}
