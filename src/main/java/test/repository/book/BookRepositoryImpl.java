package test.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import test.model.book.Book;
import test.model.exception.EntityNotFoundException;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final BookDao bookDao;
    private final BookMapper bookMapper;

    @Override
    public Book getOne(Long id) {
        try {
            BookEntity entity = bookDao.getOne(id);
            return bookMapper.toDto(entity);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(e);
        }
    }

    @Override
    public Book create(Book book) {
        BookEntity entity = new BookEntity();
        bookMapper.fillEntity(book, entity);
        long id = bookDao.save(entity);
        entity = bookDao.getOne(id);
        return bookMapper.toDto(entity);
    }

    @Override
    public Book update(Book book) {
        BookEntity entity = new BookEntity();
        bookMapper.fillEntity(book, entity);

        int updatedRowsCount = bookDao.update(entity);
        if (updatedRowsCount == 0) {
            throw new EntityNotFoundException("Book with id=%d not found");
        }

        entity = bookDao.getOne(book.getId());
        return bookMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        long deletedRowsCount = bookDao.delete(id);
        if (deletedRowsCount == 0) {
            throw new EntityNotFoundException("Book with id=%d not found");
        }
    }

}
