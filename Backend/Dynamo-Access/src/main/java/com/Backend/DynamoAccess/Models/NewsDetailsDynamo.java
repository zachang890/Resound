package com.Backend.DynamoAccess.Models;

import com.Backend.DynamoAccess.Utils.Utils;
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
@DynamoDBTable(tableName = Utils.RESOUND_APP)
public class NewsDetailsDynamo implements Serializable {

    @DynamoDBHashKey (attributeName = Utils.TOPIC)
    private String topic;

    @DynamoDBAttribute
    private List<NewsDetails> newsDetails;
}
