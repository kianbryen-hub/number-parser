package com.kb.numberparser.controller;

import com.kb.numberparser.NumberParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Number Parser REST Controller</h1>
 * Exposes the Number Parser through a REST API, users can request international formatted numbers be performing a GET
 * request on the /parse resource, passing the dialled number and user number as mandatory parameters.
 *
 * @author Kian Bryen
 * @version 0.0.1
 */
@RestController
public class NumberParserRestController {

    @Autowired
    private NumberParser numberParser;

    @GetMapping("/parse")
    public String parse(@RequestParam String dialledNumber, @RequestParam String userNumber) {
        return numberParser.parse(dialledNumber, userNumber);
    }

}