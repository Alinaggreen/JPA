package com.accenture.jive.books.controller.dto;

import com.accenture.jive.books.persistence.entity.Genre;

import java.util.List;
import java.util.Set;

public class BookDto {

    private String guid;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String genre;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}