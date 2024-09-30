package ru.semka.bookository.server.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.semka.bookository.server.dao.AuthorDao;
import ru.semka.bookository.server.dao.entity.AuthorEntity;
import ru.semka.bookository.server.rest.dto.author.AuthorRequestDto;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String GET_OR_CREATE_AUTHORS_QUERY =
            "WITH ins AS (" +
                    "    INSERT INTO bookository.author (first_name, sur_name, last_name) " +
                    "        VALUES %s " +
                    "        ON CONFLICT ON CONSTRAINT author_qk " +
                    "            DO NOTHING " +
                    "        RETURNING * ) " +
                    "SELECT * FROM ins " +
                    "UNION " +
                    "SELECT * " +
                    "FROM bookository.author " +
                    "WHERE first_name IN (:firstNames) " +
                    "  AND sur_name IN (:surNames) " +
                    "  AND last_name IN (:lastNames) " +
                    "ORDER BY id";

    @Override
    public Collection<AuthorEntity> findOrCreate(Collection<AuthorRequestDto> requestDtos) {
        if (requestDtos == null) {
            return Collections.emptyList();
        }
        Set<String> firstNames = new HashSet<>();
        Set<String> surNames = new HashSet<>();
        Set<String> lastNames = new HashSet<>();
        String values = requestDtos.stream()
                .map(dto -> {
                    firstNames.add(dto.getFirstName());
                    surNames.add(dto.getSurName());
                    lastNames.add(dto.getLastName());
                    return "('%s','%s','%s')".formatted(
                            dto.getFirstName(),
                            dto.getSurName(),
                            dto.getLastName()
                    );
                })
                .collect(Collectors.joining(","));

        return jdbcTemplate.query(
                GET_OR_CREATE_AUTHORS_QUERY.formatted(values),
                Map.of(
                        "firstNames", firstNames,
                        "surNames", surNames,
                        "lastNames", lastNames
                ),
                (rs, rowNum) -> new AuthorEntity(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("sur_name"),
                        rs.getString("last_name")
                ));
    }
}