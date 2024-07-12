package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = false)
public class BookDetailsEntity extends AbstractBookEntity {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Collection<BookContentEntity> bookContentsInfo;
}
