package test.repository.book;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import test.model.book.Book;
import test.repository.BaseMapper;

@Component
public class BookMapper extends BaseMapper<Book, BookEntity> {

    @Override
    public Book toDto(BookEntity entity) {
        Book book = new Book();
        BeanUtils.copyProperties(entity, book);
        return book;
    }

    @Override
    public void fillEntity(Book dto, BookEntity entity) {
        super.fillEntity(dto, entity);
    }


}
