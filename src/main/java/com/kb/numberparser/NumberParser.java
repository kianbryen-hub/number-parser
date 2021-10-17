package com.kb.numberparser;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Number Parser</h1>
 * The Number Parser is a utility class which takes a dialled phone number and transforms it into an international
 * formatted phone number. The Number Parser is constructed through the use of two maps, the first maps country names
 * to country codes and the second maps country names to national trunk prefixes. For example:
 *
 * <table>
 *   sv<tr>
 *     <th> Country </th> <th> Country Code </th> <th> National Prefix </th>
 *   </tr>
 *   <tr>
 *     <td> UK </td> <td> +44 </td> <td> 0 </td>
 *   </tr>
 *   <tr>
 *     <td> FR </td> <td> +33 </td> <td> 0 </td>
 *   </tr>
 *   <tr>
 *     <td> US </td> <td> +1  </td> <td> 1 </td>
 *   </tr>
 * </table>
 *
 * @author Kian Bryen
 * @version 0.0.1
 */
public class NumberParser {

    /**
     * Map of country code to national trunk prefix
     */
    private final Map<String, String> countryCodeToNationalTrunkPrefix = new HashMap<>();

    /**
     * Creates a Number Parser from two maps of country codes and national trunk prefixes.
     *
     * @param countryCodes          Map of country name to country code
     * @param nationalTrunkPrefixes Map of country name to national trunk prefix
     */
    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> nationalTrunkPrefixes) {
        for (Map.Entry<String, Integer> entry : countryCodes.entrySet()) {
            String countryName = entry.getKey();
            String formattedCountryCode = "+" + entry.getValue();
            String nationalTrunkPrefix = nationalTrunkPrefixes.get(countryName);
            countryCodeToNationalTrunkPrefix.put(formattedCountryCode, nationalTrunkPrefix);
        }
    }

    /**
     * This method is used to parse a dialled number and return the international formatted version of it, dependent on
     * the country of the origin user number. If origin user number is from an unrecognised country then this will
     * return null.
     *
     * @param dialledNumber This is the number being dialled
     * @param userNumber    This is the number of the user who is dialling
     * @return String This returns the international formatted version of the dialled number or null
     */
    public String parse(String dialledNumber, String userNumber) {
        // If the number starts with a + then this is already in international format and does not need transformation
        if (dialledNumber.charAt(0) == '+') {
            return dialledNumber;
        }

        // Else this is an internal call
        int countryCodeIndex = 2;
        do {
            // Lookup national prefix from country code
            String countryCode = userNumber.substring(0, countryCodeIndex);
            String nationTrunkPrefix = countryCodeToNationalTrunkPrefix.get(countryCode);
            if (nationTrunkPrefix != null) {
                // Concatenate country code and dialled number without the national trunk prefix
                return countryCode + dialledNumber.substring(nationTrunkPrefix.length());
            }
            // If not found increase country code index
            countryCodeIndex++;
        } while (countryCodeIndex < 5);

        // If the dialled number is from an unrecognised country code or invalid then return null
        return null;
    }

}