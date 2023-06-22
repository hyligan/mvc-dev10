package com.goit.dev.mvc.users.utils;

import com.goit.dev.mvc.users.dto.BookDto;
import com.goit.dev.mvc.users.entities.BookEntity;
import org.springframework.beans.BeanUtils;

public class Utils {
    private Utils() {
    }

    public static BookDto mappingBookDto(BookEntity entity){
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(entity.getAuthor());
        bookDto.setName(entity.getName());
        bookDto.setYear(entity.getYear());
        return bookDto;
    }

    public static BookEntity mappingBookEntity(BookDto dto){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setName(dto.getName());
        bookEntity.setYear(dto.getYear());
        return bookEntity;
    }
}
