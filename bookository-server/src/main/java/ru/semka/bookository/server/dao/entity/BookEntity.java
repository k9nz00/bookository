package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "bookository", name = "book")
@Data
public class BookEntity extends AbstractBookEntity {
    @OneToOne
    @JoinColumn(name = "id")
    private SmallBookPreviewEntity smallPreview;
}
