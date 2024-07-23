package com.accenture.jive.books.testing;

public class Replacer {

    public String execute(String original) {

        if (original == null) {
            return "null String entered";
        }

        if (original.isEmpty()) {
            return "empty String entered";
        }

        return original.replace("gg", "GG");
    }

}
