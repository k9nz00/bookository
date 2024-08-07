package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.type.BookFormatType;

@Entity
@Table(name = "book_content")
@Data
public class BookContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_content_id")
    @SequenceGenerator(name = "seq_book_content_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "format")
    @Type(value = BookFormatType.class)
    private BookFormat bookFormat;

    @Column(name = "content")
    private byte[] content;
}
