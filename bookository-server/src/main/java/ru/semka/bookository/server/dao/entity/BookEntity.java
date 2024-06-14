package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = false)
public class BookEntity extends AbstractBookEntity {
}
