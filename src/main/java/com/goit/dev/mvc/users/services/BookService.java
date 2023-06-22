package com.goit.dev.mvc.users.services;

import com.goit.dev.mvc.users.dto.BookDto;
import org.springframework.data.domain.Page;

public interface BookService {
    BookDto get(Long id);

    Page<BookDto> get(int size, int page);

    BookDto create(BookDto dto);

    BookDto update(Long id, BookDto dto);

    void delete(Long id);
}
