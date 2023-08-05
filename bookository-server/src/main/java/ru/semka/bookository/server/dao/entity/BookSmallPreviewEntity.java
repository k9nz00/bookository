package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book_small_preview")
@Data
public class BookSmallPreviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_small_preview_id")
    @SequenceGenerator(name = "seq_book_small_preview_id", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "size")
    private Long size;
    @Column(name = "preview")
    private byte[] preview;
}
