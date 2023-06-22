package com.goit.dev.mvc.users.controllers;

import com.goit.dev.mvc.users.dto.BookDto;
import com.goit.dev.mvc.users.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/book")
public class BookRestController {
    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public @ResponseBody BookDto get(@PathVariable("id") Long id){
        return bookService.get(id);
    }
    @GetMapping("/")
    public Page<BookDto> getAll(@RequestParam(name = "size", defaultValue = "100") int size,
                             @RequestParam(name = "page", defaultValue = "0") int page){

        return bookService.get(size,page);
    }
    @PostMapping("/")
    public BookDto create(@RequestBody BookDto dto){
        return bookService.create(dto);
    }
    @PutMapping("/{id}")
    public BookDto update(@PathVariable("id") Long id,@Valid @RequestBody BookDto dto){
        return bookService.update(id,dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        bookService.delete(id);
    }
}
