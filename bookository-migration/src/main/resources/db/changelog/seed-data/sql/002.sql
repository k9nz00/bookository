INSERT INTO bookository.book_cover (id, size, format, data)
VALUES (1,
        (PG_STAT_FILE('/opt/covers/cover-1.jpg')).size,
        'JPG'::bookository.cover_format,
        PG_READ_BINARY_FILE('/opt/covers/cover-1.jpg')::bytea),

       (2,
        (PG_STAT_FILE('/opt/covers/cover-2.jpg')).size,
        'JPG'::bookository.cover_format,
        PG_READ_BINARY_FILE('/opt/covers/cover-2.jpg')::bytea),

       (3,
        (PG_STAT_FILE('/opt/covers/cover-3.jpg')).size,
        'JPG'::bookository.cover_format,
        PG_READ_BINARY_FILE('/opt/covers/cover-3.jpg')::bytea),

       (4,
        (PG_STAT_FILE('/opt/covers/cover-4.jpg')).size,
        'JPG'::bookository.cover_format,
        PG_READ_BINARY_FILE('/opt/covers/cover-4.jpg')::bytea),

       (5,
        (PG_STAT_FILE('/opt/covers/cover-5.jpg')).size,
        'JPG'::bookository.cover_format,
        PG_READ_BINARY_FILE('/opt/covers/cover-5.jpg')::bytea)
;

