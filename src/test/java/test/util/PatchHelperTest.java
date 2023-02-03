package test.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractTest;
import test.model.book.Book;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PatchHelperTest extends AbstractTest {

    @Autowired
    private PatchHelper patchHelper;

    @Test
    public void _1_updates_null() {
        Book updates = null;
        Book currentState = newBook(1L);
        Book patched = patchHelper.apply(updates, currentState, Book.class);

        assertThat(patched.getId(), is(currentState.getId()));
        assertThat(patched.getTitle(), is(currentState.getTitle()));
        assertThat(patched.getAuthor(), is(currentState.getAuthor()));
        assertThat(patched.getPrice(), is(currentState.getPrice()));
        assertThat(patched.getPublisher(), is(currentState.getPublisher()));
    }


    @Test
    public void _2_updates_empty() {
        Book updates = new Book();
        Book currentState = newBook(1L);
        Book patched = patchHelper.apply(updates, currentState, Book.class);

        assertThat(patched.getId(), is(currentState.getId()));
        assertThat(patched.getTitle(), is(currentState.getTitle()));
        assertThat(patched.getAuthor(), is(currentState.getAuthor()));
        assertThat(patched.getPrice(), is(currentState.getPrice()));
        assertThat(patched.getPublisher(), is(currentState.getPublisher()));
    }

    @Test
    public void _3_updates_notEmpty() {
        Book updates = new Book();
        updates.setId(1L);
        updates.setTitle("new title");
        updates.setAuthor("new author");
        updates.setPrice(123);
        updates.setPublisher("new publisher");
        updates.setPublishedAt(LocalDateTime.now());
        updates.setTitle("new title");
        Book currentState = newBook(1L);
        Book patched = patchHelper.apply(updates, currentState, Book.class);

        assertThat(patched.getId(), is(updates.getId()));
        assertThat(patched.getTitle(), is(updates.getTitle()));
        assertThat(patched.getAuthor(), is(updates.getAuthor()));
        assertThat(patched.getPrice(), is(updates.getPrice()));
        assertThat(patched.getPublisher(), is(updates.getPublisher()));
    }

}
