package ru.semka.bookository.server.dao;

import ru.semka.bookository.server.dao.entity.AuthorEntity;
import ru.semka.bookository.server.rest.dto.author.AuthorRequestDto;

import java.util.Collection;

public interface AuthorDao {
    Collection<AuthorEntity> findOrCreate(Collection<AuthorRequestDto> requestDtos);
}
