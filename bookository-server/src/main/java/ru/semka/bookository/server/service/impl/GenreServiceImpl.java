package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.GenreDao;
import ru.semka.bookository.server.rest.dto.bookgenre.CreateBookGenreRequestDto;
import ru.semka.bookository.server.rest.dto.bookgenre.GenreUiDto;
import ru.semka.bookository.server.service.GenreService;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public void save(CreateBookGenreRequestDto dto) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Collection<GenreUiDto> getAll() {
        return List.of();
    }


    @Override
    public GenreUiDto getById(int id) {
        return genreDao.findById(id)
                .map(genreEntity -> new GenreUiDto(genreEntity.getId(), genreEntity.getName()))
                .orElseThrow(() -> new ResourceNotFoundException("жанр не найден"));
    }
}
