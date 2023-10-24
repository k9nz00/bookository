package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "bookository", name = "small_book_preview")
@Data
public class SmallBookPreviewEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    private Long size;

    @Column(name = "preview")
    private byte[] preview;
}
