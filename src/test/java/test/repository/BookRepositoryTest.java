package test.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractTest;
import test.model.book.Book;
import test.model.exception.EntityNotFoundException;
import test.repository.book.BookRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookRepositoryTest extends AbstractTest {

    @Autowired
    private BookRepository bookRepository;

    private static Book globalBook;
    private static long globalBookId;
    private static final long NOT_EXISTS_ENTITY_ID = -1L;

    @Test
    void _1_create() {
        Book newBook = newBook(null);

        Book createdBook = assertDoesNotThrow(() -> bookRepository.create(newBook));
        globalBook = createdBook;
        globalBookId = createdBook.getId();

        assertThat(createdBook.getId(), greaterThanOrEqualTo(1L));
        assertThat(createdBook.getTitle(), is(newBook.getTitle()));
        assertThat(createdBook.getAuthor(), is(newBook.getAuthor()));
        assertThat(createdBook.getPrice(), is(newBook.getPrice()));
        assertThat(createdBook.getPublisher(), is(newBook.getPublisher()));
    }

    @Test
    void _2_1_getOne_existsEntity() {
        Book book = assertDoesNotThrow(() -> bookRepository.getOne(globalBookId));
        assertThat(book.getId(), is(globalBookId));
        assertThat(book.getTitle(), is(globalBook.getTitle()));
        assertThat(book.getAuthor(), is(globalBook.getAuthor()));
        assertThat(book.getPrice(), is(globalBook.getPrice()));
        assertThat(book.getPublisher(), is(globalBook.getPublisher()));
    }

    @Test
    void _2_2_getOne_notExistsEntity() {
        assertThrows(EntityNotFoundException.class, () -> bookRepository.getOne(NOT_EXISTS_ENTITY_ID));
    }

    @Test
    void _3_1_update_existsEntity() {
        Book bookForUpdate = assertDoesNotThrow(() -> bookRepository.getOne(globalBookId));
        bookForUpdate.setTitle("new title");

        Book updatedBook = assertDoesNotThrow(() -> bookRepository.update(bookForUpdate));

        assertThat(updatedBook.getId(), is(globalBookId));
        assertThat(updatedBook.getTitle(), is(bookForUpdate.getTitle()));
        assertThat(updatedBook.getAuthor(), is(bookForUpdate.getAuthor()));
        assertThat(updatedBook.getPrice(), is(bookForUpdate.getPrice()));
        assertThat(updatedBook.getPublisher(), is(bookForUpdate.getPublisher()));
    }

    @Test
    void _3_2_update_notExistsEntity() {
        Book book = new Book();
        book.setId(NOT_EXISTS_ENTITY_ID);
        assertThrows(EntityNotFoundException.class, () -> bookRepository.update(book));
    }

    @Test
    void _4_1_update_existsEntity() {
        assertDoesNotThrow(() -> bookRepository.delete(globalBookId));
        assertThrows(EntityNotFoundException.class, () -> bookRepository.delete(globalBookId));
    }

    @Test
    void _4_2_update_notExistsEntity() {
        assertThrows(EntityNotFoundException.class, () -> bookRepository.delete(NOT_EXISTS_ENTITY_ID));
    }

}
