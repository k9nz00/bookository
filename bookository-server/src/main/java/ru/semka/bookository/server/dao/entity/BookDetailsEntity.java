package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(schema = "bookository", name = "book")
@Data
public class BookDetailsEntity extends AbstractBookEntity {
    @OneToOne
    @JoinColumn(name = "id")
    private BigBookPreviewEntity bigPreview;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Collection<BookContentInfoEntity> bookContentsInfo;
}
