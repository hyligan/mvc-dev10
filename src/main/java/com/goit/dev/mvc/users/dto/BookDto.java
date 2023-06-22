package com.goit.dev.mvc.users.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookDto {

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;
    @NotBlank
    @NotNull
    @NotEmpty
    private String author;
    @NotBlank
    @NotNull
    @NotEmpty
    private Integer year;

    public BookDto() {
    }

    public BookDto(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
