package com.goit.dev.mvc.users.repos;

import com.goit.dev.mvc.users.dto.BookDto;
import com.goit.dev.mvc.users.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepo extends JpaRepository<BookEntity,Long> {
    @Query("select new com.goit.dev.mvc.users.dto.BookDto(t.name,t.author,t.year) from BookEntity t")
    Page<BookDto> findAllBookDto(Pageable pageable);
}
