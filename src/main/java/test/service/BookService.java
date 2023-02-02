package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.model.book.Book;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book getOne(Long id) {
        return bookRepository.getOne(id);
    }

    public Book create(Book book) {
        book.setId(null);
        return bookRepository.create(book);
    }

    public Book update(Long id, Book newState) {
        Book currentState = bookRepository.getOne(id);
        copyEditableFields(newState, currentState);
        return bookRepository.update(currentState);
    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }

    private void copyEditableFields(Book newState, Book currentState) {
        currentState.setTitle(newState.getTitle());
        currentState.setAuthor(newState.getAuthor());
        currentState.setPrice(newState.getPrice());
        currentState.setPublisher(newState.getPublisher());
        currentState.setPublishedAt(newState.getPublishedAt());
    }

}
