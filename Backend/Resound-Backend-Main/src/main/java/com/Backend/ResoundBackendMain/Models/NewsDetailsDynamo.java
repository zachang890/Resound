package com.Backend.ResoundBackendMain.Models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class NewsDetailsDynamo {
    private String topic;
    private List<NewsDetails> newsDetails;
}
