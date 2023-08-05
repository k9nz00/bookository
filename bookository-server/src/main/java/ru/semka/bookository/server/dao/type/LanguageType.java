package ru.semka.bookository.server.dao.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ru.semka.bookository.server.common.enums.Language;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class LanguageType implements UserType<Language> {
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<Language> returnedClass() {
        return Language.class;
    }

    @Override
    public boolean equals(Language language, Language j1) {
        return Objects.equals(language, j1);
    }

    @Override
    public int hashCode(Language language) {
        return language.hashCode();
    }

    @Override
    public Language nullSafeGet(ResultSet resultSet,
                                int i,
                                SharedSessionContractImplementor sharedSessionContractImplementor,
                                Object o) throws SQLException {
        String language = resultSet.getString(i);
        return language != null ? Language.valueOf(language) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            Language language,
                            int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

        if (language == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            preparedStatement.setObject(i, language, Types.OTHER);
        }
    }

    @Override
    public Language deepCopy(Language language) {
        String name = language.name();
        return Language.valueOf(name);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Language language) {
        return language;
    }

    @Override
    public Language assemble(Serializable serializable, Object o) {
        return null;
    }
}
