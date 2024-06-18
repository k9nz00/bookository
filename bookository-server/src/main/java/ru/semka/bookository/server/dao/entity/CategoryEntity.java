package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
