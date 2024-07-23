package com.accenture.jive.books.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReplacerTest {

    @DisplayName("😱")
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        Assertions.assertTrue(candidate.endsWith("r"));
    }

    @Test
    public void testReplace() {
        String probe = "gg";
        String expect = "GG";
        Replacer replacer = new Replacer();

        String actual = replacer.execute(probe);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testReplaceNull() {
        String expect = "null String entered";
        Replacer replacer = new Replacer();

        String actual = replacer.execute(null);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void testReplaceEmpty() {
        String expect = "empty String entered";
        Replacer replacer = new Replacer();

        String actual = replacer.execute("");
        Assertions.assertEquals(expect, actual);
    }

}
