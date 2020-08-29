package com.Backend.NewsAggregator.Controllers;

import com.Backend.NewsAggregator.Services.NewsAggregatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-aggregator")
@Slf4j
public class NewsAggregatorController {

    @Autowired
    NewsAggregatorService newsAggregatorService;

    @GetMapping("/topic/{topic}") //convert to hbs file
    public String newsByTopic(@PathVariable("topic") String topic) {
        return newsAggregatorService.attemptCrawl(topic);
    }
    //here is what swagger annotations would look like
//    @ApiOperation(value = "Find Contacts by id",
//            notes = "Provide an id to look up specific contact from the address book",
//            response = Contact.class) //What it does, response is a Contact module
//    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true) @PathVariable String id) {
//        return contacts.get(id); //API PARAM TO SPECIFY NECESSARY CONDITIONS
//    }
}
