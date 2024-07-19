package ru.semka.bookository.server.dao.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ru.semka.bookository.server.common.enums.ImageFormat;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;

public class ImageFormatType implements UserType<ImageFormat> {
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<ImageFormat> returnedClass() {
        return ImageFormat.class;
    }

    @Override
    public boolean equals(ImageFormat imageFormat, ImageFormat j1) {
        return Objects.equals(imageFormat, j1);
    }

    @Override
    public int hashCode(ImageFormat imageFormat) {
        return imageFormat.hashCode();
    }

    @Override
    public ImageFormat nullSafeGet(ResultSet resultSet,
                                   int i,
                                   SharedSessionContractImplementor sharedSessionContractImplementor,
                                   Object o) throws SQLException {
        String imageFormat = resultSet.getString(i);
        return imageFormat != null ? ImageFormat.valueOf(imageFormat) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            ImageFormat imageFormat,
                            int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

        if (imageFormat == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            preparedStatement.setObject(i, imageFormat, Types.OTHER);
        }
    }

    @Override
    public ImageFormat deepCopy(ImageFormat imageFormat) {
        return Optional.ofNullable(imageFormat)
                .map(ImageFormat::name)
                .map(ImageFormat::valueOf)
                .orElse(null);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(ImageFormat imageFormat) {
        return imageFormat;
    }

    @Override
    public ImageFormat assemble(Serializable serializable, Object o) {
        return null;
    }
}
