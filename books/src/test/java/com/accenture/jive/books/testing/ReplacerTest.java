package com.accenture.jive.books.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReplacerTest {

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
