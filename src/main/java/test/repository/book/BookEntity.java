package test.repository.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookEntity implements BaseEntity {

    private Long id;

    private String title;

    private String author;

    private int price;

    private String publisher;

    private LocalDateTime publishedAt;

}
