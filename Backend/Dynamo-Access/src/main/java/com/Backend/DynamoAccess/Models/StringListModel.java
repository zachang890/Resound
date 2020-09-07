package com.Backend.DynamoAccess.Models;

import com.Backend.DynamoAccess.Utils.Utils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = Utils.SUGGESTIONS_COLLECTION)
public class StringListModel {

    @DynamoDBHashKey(attributeName = Utils.SUGGESTION_CATEGORY)
    private String suggestionListTitle;

    @DynamoDBAttribute(attributeName = Utils.ENTRIES)
    private List<String> suggestions;
}
