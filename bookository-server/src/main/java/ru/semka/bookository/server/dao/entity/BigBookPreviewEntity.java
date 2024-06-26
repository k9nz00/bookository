package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "big_book_preview")
@Data
public class BigBookPreviewEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    private Long size;

    @Column(name = "preview")
    private byte[] preview;
}
