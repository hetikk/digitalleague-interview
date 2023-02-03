package test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractTest;
import test.model.book.Book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookService bookService;

    private static Book globalBook;
    private static long globalBookId;

    @Test
    void _1_create() {
        Book newBook = newBook(null);

        Book createdBook = assertDoesNotThrow(() -> bookService.create(newBook));
        globalBook = createdBook;
        globalBookId = createdBook.getId();

        assertThat(createdBook.getId(), greaterThanOrEqualTo(1L));
    }

    @Test
    void _2_update_idUpdating_idShouldBotChange() {
        long newId = 0L;
        globalBook.setId(newId);

        Book updatedBook = assertDoesNotThrow(() -> bookService.update(globalBookId, globalBook));

        assertThat(updatedBook.getId(), not(is(newId)));
        assertThat(updatedBook.getId(), is(globalBookId));
    }

}
