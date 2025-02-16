package com.example.Sem_2.repository;

import com.example.Sem_2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
