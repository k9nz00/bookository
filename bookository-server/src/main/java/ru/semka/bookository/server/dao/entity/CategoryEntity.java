package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "bookository", name = "category")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
