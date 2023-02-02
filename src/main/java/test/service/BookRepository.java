package test.service;

import test.model.book.Book;

public interface BookRepository {

    Book getOne(Long id);

    Book create(Book book);

    Book update(Book book);

    void delete(Long id);

}
