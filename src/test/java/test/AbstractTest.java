package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.model.book.Book;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AbstractTest {

    @Autowired
    protected ObjectMapper mapper;

    protected static Book newBook(Long id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPrice(100);
        book.setPublisher("publisher");
        book.setPublishedAt(LocalDateTime.now());
        return book;
    }

}
