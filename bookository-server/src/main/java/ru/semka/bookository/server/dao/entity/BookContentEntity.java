package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_content")
public class BookContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_content_id")
    @SequenceGenerator(name = "seq_book_content_id", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "size")
    private Long size;
    @Column(name = "content")
    private byte[] content;
}
