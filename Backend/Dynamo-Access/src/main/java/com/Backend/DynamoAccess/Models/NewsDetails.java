package com.Backend.DynamoAccess.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@DynamoDBDocument
public class NewsDetails {

    @DynamoDBAttribute
    private String headline;
    @DynamoDBAttribute
    private String url;
    @DynamoDBAttribute
    private String source;
    @DynamoDBAttribute
    private String time;
}
