package com.Backend.NewsAggregator.Controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NewsAggregatorController {

    //here is what swagger annotations would look like
//    @ApiOperation(value = "Find Contacts by id",
//            notes = "Provide an id to look up specific contact from the address book",
//            response = Contact.class) //What it does, response is a Contact module
//    public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true) @PathVariable String id) {
//        return contacts.get(id); //API PARAM TO SPECIFY NECESSARY CONDITIONS
//    }
}
