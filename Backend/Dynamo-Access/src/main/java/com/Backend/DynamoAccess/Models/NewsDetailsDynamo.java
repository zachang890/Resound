package com.Backend.DynamoAccess.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Resound-app")
public class NewsDetailsDynamo implements Serializable {

    @DynamoDBHashKey (attributeName = "topic")
    private String topic;

    @DynamoDBAttribute
    private List<NewsDetails> newsDetails;
}
