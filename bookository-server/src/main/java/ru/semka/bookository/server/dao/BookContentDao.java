package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.entity.BookContentEntity;

import java.util.Optional;

@Repository
public interface BookContentDao extends JpaRepository<BookContentEntity, Integer> {

    Optional<BookContentEntity> findByIdAndAndBookId(Integer id, Integer bookId);
}
