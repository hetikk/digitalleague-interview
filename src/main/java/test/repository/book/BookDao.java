package test.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookEntity getOne(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = " + id, (rs, rowNum) -> {
            BookEntity entity = new BookEntity();
            entity.setId(rs.getLong(1));
            entity.setTitle(rs.getString(2));
            entity.setAuthor(rs.getString(3));
            entity.setPrice(rs.getInt(4));
            entity.setPublisher(rs.getString(5));
            Timestamp timestamp = rs.getTimestamp(6);
            entity.setPublishedAt(timestamp.toLocalDateTime());
            return entity;
        });
    }

    public long save(BookEntity entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = String.format(
                "INSERT INTO books(title, author, price, publisher, published_at) VALUES('%s', '%s', %d, '%s', '%s')",
                entity.getTitle(), entity.getAuthor(), entity.getPrice(), entity.getPublisher(), entity.getPublishedAt()
        );

        jdbcTemplate.update(connection -> connection.prepareStatement(sql, new String[] {"id"}), keyHolder);

        return keyHolder.getKey().longValue();
    }

    public int update(BookEntity entity) {
        String sql = String.format(
                """
                UPDATE books
                   SET title        = '%s',
                       author       = '%s',
                       price        = %d,
                	   publisher    = '%s',
                	   published_at = '%s'
                WHERE id = %d;
                """,
                entity.getTitle(), entity.getAuthor(), entity.getPrice(),
                entity.getPublisher(), entity.getPublishedAt(), entity.getId()
        );

        return jdbcTemplate.update(sql);
    }

    public long delete(long id) {
        String sql = "DELETE FROM books WHERE id = " + id;
        return jdbcTemplate.update(sql);
    }

}
