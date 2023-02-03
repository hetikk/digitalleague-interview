package test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.model.book.Book;
import test.repository.book.BookRepository;
import test.util.PatchHelper;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final PatchHelper patchHelper;

    public Book getOne(Long id) {
        return bookRepository.getOne(id);
    }

    public Book create(Book book) {
        book.setId(null);
        return bookRepository.create(book);
    }

    public Book update(Long id, Book bookUpdates) {
        Book currentState = bookRepository.getOne(id);
        Book newState = patchHelper.apply(bookUpdates, currentState, Book.class);
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
