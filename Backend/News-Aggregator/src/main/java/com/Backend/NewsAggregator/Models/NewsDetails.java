package com.Backend.NewsAggregator.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class NewsDetails {
    private String headline;
    private String url;
    private String source;
    private String time;

    public NewsDetails() {
        headline = "";
        url = "";
        source = "";
        time = "";
    }
}
