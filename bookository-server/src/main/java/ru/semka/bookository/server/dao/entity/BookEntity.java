package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "bookository", name = "book")
@Data
public class BookEntity extends AbstractBookEntity {
}
