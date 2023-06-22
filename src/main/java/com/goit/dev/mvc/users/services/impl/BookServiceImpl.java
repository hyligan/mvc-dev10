package com.goit.dev.mvc.users.services.impl;

import com.goit.dev.mvc.users.dto.BookDto;
import com.goit.dev.mvc.users.entities.BookEntity;
import com.goit.dev.mvc.users.repos.BookRepo;
import com.goit.dev.mvc.users.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.goit.dev.mvc.users.utils.Utils.mappingBookDto;
import static com.goit.dev.mvc.users.utils.Utils.mappingBookEntity;

@Service
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookDto get(Long id) {
        return mappingBookDto(bookRepo.findById(id).get());
    }

    @Override
    public Page<BookDto> get(int size, int page) {
        return bookRepo.findAllBookDto(PageRequest.of(page, size));

    }

    @Override
    public BookDto create(BookDto dto) {
        return mappingBookDto(bookRepo.save(mappingBookEntity(dto)));

    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        BookEntity bookEntity = bookRepo.findById(id).get();
        BeanUtils.copyProperties(dto,bookEntity);
        return mappingBookDto(bookRepo.save(bookEntity));
    }

    @Override
    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
}
