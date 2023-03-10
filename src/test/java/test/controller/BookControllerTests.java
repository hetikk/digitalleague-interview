package test.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import test.AbstractTest;
import test.model.book.Book;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class BookControllerTests extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/books";

    private static Integer bookId;

    @Test
    public void _1_create() throws Exception {
        Book book = newBook(null);

        String body = mapper.writeValueAsString(book);
        String response = mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.price", is(book.getPrice())))
                .andExpect(jsonPath("$.publisher", is(book.getPublisher())))
                .andReturn().getResponse().getContentAsString();

        bookId = JsonPath.parse(response).read("$.id", Integer.class);
    }

    @Test
    public void _2_getOne() throws Exception {
        mockMvc.perform(get(URL + "/" + bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookId)));
    }

    @Test
    public void _3_update() throws Exception {
        Book book = new Book();
        book.setPrice(123);

        String body = mapper.writeValueAsString(book);
        mockMvc.perform(patch(URL + "/" + bookId)
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(book.getPrice())));
    }

    @Test
    public void _4_delete() throws Exception {
        mockMvc.perform(delete(URL + "/" + bookId)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
