package com.example.Sem_2.service;

import com.example.Sem_2.entities.Book;
import com.example.Sem_2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Сохранение новой книги
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    // Обновление существующей книги
    public void updateBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (optionalBook.isPresent()) {
            Book oldBook = optionalBook.get();
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            oldBook.setIsbn(book.getIsbn());
            oldBook.setPublicationYear(book.getPublicationYear());
            bookRepository.save(oldBook);
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
