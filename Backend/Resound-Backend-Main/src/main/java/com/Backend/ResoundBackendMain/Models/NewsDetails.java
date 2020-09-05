package com.Backend.ResoundBackendMain.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class NewsDetails {
    private String headline;
    private String url;
    private String source;
    private String time;
}
