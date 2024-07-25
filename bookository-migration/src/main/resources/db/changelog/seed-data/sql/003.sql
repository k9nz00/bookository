INSERT INTO bookository.book_content (book_id, name, size, format, content)
VALUES (1,
        'book-1-1',
        (PG_STAT_FILE('/opt/books/book_1/book_1_1.fb2')).size,
        'FB2'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_1/book_1_1.fb2')::bytea),

       (1,
        'book-1-2',
        (PG_STAT_FILE('/opt/books/book_1/book_1_2.txt')).size,
        'TXT'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_1/book_1_2.txt')::bytea),

       (1,
        'book-1-3',
        (PG_STAT_FILE('/opt/books/book_1/book_1_3.epub')).size,
        'EPUB'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_1/book_1_3.epub')::bytea)
;

INSERT INTO bookository.book_content (book_id, name, size, format, content)
VALUES (2,
        'book-2-1',
        (PG_STAT_FILE('/opt/books/book_2/book_2_1.fb2')).size,
        'FB2'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_2/book_2_1.fb2')::bytea),

       (2,
        'book-2-2',
        (PG_STAT_FILE('/opt/books/book_2/book_2_2.epub')).size,
        'EPUB'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_2/book_2_2.epub')::bytea),

       (2,
        'book-2-3',
        (PG_STAT_FILE('/opt/books/book_2/book_2_3.pdf')).size,
        'PDF'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_2/book_2_3.pdf')::bytea)
;

INSERT INTO bookository.book_content (book_id, name, size, format, content)
VALUES (3,
        'book-3-1',
        (PG_STAT_FILE('/opt/books/book_3/book_3_1.epub')).size,
        'EPUB'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_3/book_3_1.epub')::bytea),

       (3,
        'book-3-2',
        (PG_STAT_FILE('/opt/books/book_3/book_3_2.pdf')).size,
        'PDF'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_3/book_3_2.pdf')::bytea),

       (3,
        'book-3-3',
        (PG_STAT_FILE('/opt/books/book_3/book_3_3.txt')).size,
        'TXT'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_3/book_3_3.txt')::bytea)
;

INSERT INTO bookository.book_content (book_id, name, size, format, content)
VALUES (4,
        'book-4-1',
        (PG_STAT_FILE('/opt/books/book_4/book_4_1.epub')).size,
        'EPUB'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_4/book_4_1.epub')::bytea),

       (4,
        'book-4-2',
        (PG_STAT_FILE('/opt/books/book_4/book_4_2.fb2')).size,
        'FB2'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_4/book_4_2.fb2')::bytea)
;

INSERT INTO bookository.book_content (book_id, name, size, format, content)
VALUES (5,
        'book-5-1',
        (PG_STAT_FILE('/opt/books/book_5/book_5_1.epub')).size,
        'EPUB'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_5/book_5_1.epub')::bytea),

       (5,
        'book-5-2',
        (PG_STAT_FILE('/opt/books/book_5/book_5_2.fb2')).size,
        'FB2'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_5/book_5_2.fb2')::bytea),

       (5,
        'book-5-3',
        (PG_STAT_FILE('/opt/books/book_5/book_5_3.pdf')).size,
        'PDF'::bookository.book_format,
        PG_READ_BINARY_FILE('/opt/books/book_5/book_5_3.pdf')::bytea)
;

