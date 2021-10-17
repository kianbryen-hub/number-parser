package com.kb.numberparser.rest.config;

import com.kb.numberparser.NumberParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * <h1>WebConfiguration</h1>
 * Configure the Number Parser to be used by the REST API by loading the country codes and national trunk prefixes
 * from a CSV stored in the main resources directory.
 *
 * @author Kian Bryen
 * @version 0.0.1
 */
@Configuration
public class WebConfiguration {

    @Bean
    public NumberParser numberParser() throws CsvValidationException, IOException {
        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> nationalTrunkPrefixes = new HashMap<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/countryCodes.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                String name = values[1];
                Integer code = Integer.valueOf(values[1]);
                String prefix = values[2];
                countryCodes.put(name, code);
                nationalTrunkPrefixes.put(name, prefix);
            }
        }
        return new NumberParser(countryCodes, nationalTrunkPrefixes);
    }

}
