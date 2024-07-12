package ru.semka.bookository.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.entity.BookCoverEntity;

import java.io.IOException;

public interface BookCoverDao extends JpaRepository<BookCoverEntity, Integer> {
}
