package ru.semka.bookository.server.dao.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ru.semka.bookository.server.common.enums.BookFormat;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;

public class BookFormatType implements UserType<BookFormat> {
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<BookFormat> returnedClass() {
        return BookFormat.class;
    }

    @Override
    public boolean equals(BookFormat bookFormat, BookFormat j1) {
        return Objects.equals(bookFormat, j1);
    }

    @Override
    public int hashCode(BookFormat bookFormat) {
        return bookFormat.hashCode();
    }

    @Override
    public BookFormat nullSafeGet(ResultSet resultSet,
                                  int i,
                                  SharedSessionContractImplementor sharedSessionContractImplementor,
                                  Object o) throws SQLException {
        String bookFormat = resultSet.getString(i);
        return bookFormat != null ? BookFormat.valueOf(bookFormat) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            BookFormat bookFormat,
                            int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

        if (bookFormat == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            preparedStatement.setObject(i, bookFormat, Types.OTHER);
        }
    }

    @Override
    public BookFormat deepCopy(BookFormat bookFormat) {
        return Optional.ofNullable(bookFormat)
                .map(BookFormat::name)
                .map(BookFormat::valueOf)
                .orElse(null);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(BookFormat language) {
        return language;
    }

    @Override
    public BookFormat assemble(Serializable serializable, Object o) {
        return null;
    }
}
