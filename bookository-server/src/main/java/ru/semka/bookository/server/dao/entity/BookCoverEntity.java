package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_cover")
@Data
public class BookCoverEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "size")
    private Long size;

    @Column(name = "format")
    private String format;

    @Column(name = "data")
    private byte[] data;
}
