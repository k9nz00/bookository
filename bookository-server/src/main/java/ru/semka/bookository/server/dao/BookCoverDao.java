package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.entity.BookCoverEntity;

@Repository
public interface BookCoverDao extends JpaRepository<BookCoverEntity, Integer> {

}
