package com.kb.numberparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class NumberParserTest {

    private static final NumberParser NUMBER_PARSER = new NumberParser(
            Map.of("UK", 44, "FR", 33, "US", 1),
            Map.of("UK", "0", "FR", "0", "US", "1")
    );

    @Test
    public void testParseUKtoUK() {
        Assert.assertEquals("+447277822334",
                NUMBER_PARSER.parse("07277822334", "+447866866886"));
    }

    @Test
    public void testParseUStoUS() {
        Assert.assertEquals("+1312233244",
                NUMBER_PARSER.parse("1312233244", "+1212233200"));
    }

    @Test
    public void testParseUKtoUS() {
        Assert.assertEquals("+1312233244",
                NUMBER_PARSER.parse("+1312233244", "+447866866886"));
    }

    @Test
    public void testParseUnknownCountry() {
        Assert.assertNull(NUMBER_PARSER.parse("1312233244", "+467866866886"));
    }

    @Test
    public void testParseNullDialledNumber() {
        Assert.assertNull(NUMBER_PARSER.parse(null, "+467866866886"));
    }

    @Test
    public void testParseNullUserNumber() {
        Assert.assertNull(NUMBER_PARSER.parse("1312233244", null));
    }

    @Test
    public void testParseNull() {
        Assert.assertNull(NUMBER_PARSER.parse(null, null));
    }
}
