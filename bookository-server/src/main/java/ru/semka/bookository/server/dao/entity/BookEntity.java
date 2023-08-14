package ru.semka.bookository.server.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.dao.type.LanguageType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(schema = "bookository", name = "book")
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_id")
    @SequenceGenerator(schema = "bookository", name = "seq_book_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "annotation")
    private String annotation;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Type(value = LanguageType.class)
    @Column(name = "language")
    private Language language;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<CategoryEntity> categories = new ArrayList<>();
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "book_id")
    private BookSmallPreviewEntity smallPreview;
}
