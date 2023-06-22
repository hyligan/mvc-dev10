package com.goit.dev.mvc.users.dto;

import org.springframework.data.domain.Page;

public class LibDto {


    private Page<BookDto> books;
    private Page<BookDto> bookses;

    public LibDto(Page<BookDto> books, Page<BookDto> bookses) {
        this.books = books;
        this.bookses = bookses;
    }

    public Page<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Page<BookDto> books) {
        this.books = books;
    }

    public Page<BookDto> getBookses() {
        return bookses;
    }

    public void setBookses(Page<BookDto> bookses) {
        this.bookses = bookses;
    }
}
