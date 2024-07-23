package com.accenture.jive.books.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReplacerTest {

    @Test
    public void testReplace() {
        String probe = "";
        String expect = "";
        Replacer replacer = new Replacer();

        String actual = replacer.execute(probe);
        Assertions.assertEquals(expect, actual);

    }

}
