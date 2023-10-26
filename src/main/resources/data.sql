-- AUTHORS --
insert into author (id, name)
values (1, 'Author A');

insert into author (id, name)
values (2, 'Author B');

insert into author (id, name)
values (3, 'Author C');


-- BOOKS --
insert into book (id, isbn, title, genre, published, page_count, created_at)
values (1, 'isbn-01', 'Book 01', 'FANTASY', '2023-10-01T00:00:00+02:00', 101, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (2, 'isbn-02', 'Book 02', 'FANTASY', '2023-10-02T00:00:00+02:00', 102, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (3, 'isbn-03', 'Book 03', 'FANTASY', '2023-10-03T00:00:00+02:00', 103, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (4, 'isbn-04', 'Book 04', 'SCIENCE_FICTION', '2023-10-04T00:00:00+02:00', 104, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (5, 'isbn-05', 'Book 05', 'SCIENCE_FICTION', '2023-10-05T00:00:00+02:00', 105, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (6, 'isbn-06', 'Book 06', 'SCIENCE_FICTION', '2023-10-06T00:00:00+02:00', 106, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (7, 'isbn-07', 'Book 07', 'DYSTOPIAN', '2023-10-07T00:00:00+02:00', 107, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (8, 'isbn-08', 'Book 08', 'DYSTOPIAN', '2023-10-08T00:00:00+02:00', 108, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (9, 'isbn-09', 'Book 09', 'DYSTOPIAN', '2023-10-09T00:00:00+02:00', 109, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (10, 'isbn-10', 'Book 10', 'ADVENTURE', '2023-10-10T00:00:00+02:00', 110, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (11, 'isbn-11', 'Book 11', 'ADVENTURE', '2023-10-11T00:00:00+02:00', 111, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (12, 'isbn-12', 'Book 12', 'ADVENTURE', '2023-10-12T00:00:00+02:00', 112, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (13, 'isbn-13', 'Book 13', 'HORROR', '2023-10-13T00:00:00+02:00', 113, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (14, 'isbn-14', 'Book 14', 'HORROR', '2023-10-14T00:00:00+02:00', 114, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (15, 'isbn-15', 'Book 15', 'HORROR', '2023-10-15T00:00:00+02:00', 115, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (16, 'isbn-16', 'Book 16', 'THRILLER', '2023-10-16T00:00:00+02:00', 116, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (17, 'isbn-17', 'Book 17', 'THRILLER', '2023-10-17T00:00:00+02:00', 117, now());

insert into book (id, isbn, title, genre, published, page_count, created_at)
values (18, 'isbn-18', 'Book 18', 'THRILLER', '2023-10-18T00:00:00+02:00', 118, now());


-- AUTHORS <-> BOOKS
insert into author_books (authors_id, books_id) values (1, 1);
insert into author_books (authors_id, books_id) values (1, 4);
insert into author_books (authors_id, books_id) values (1, 7);
insert into author_books (authors_id, books_id) values (1, 10);
insert into author_books (authors_id, books_id) values (1, 13);
insert into author_books (authors_id, books_id) values (1, 16);

insert into author_books (authors_id, books_id) values (2, 2);
insert into author_books (authors_id, books_id) values (2, 5);
insert into author_books (authors_id, books_id) values (2, 8);
insert into author_books (authors_id, books_id) values (2, 11);
insert into author_books (authors_id, books_id) values (2, 14);
insert into author_books (authors_id, books_id) values (2, 17);

insert into author_books (authors_id, books_id) values (3, 3);
insert into author_books (authors_id, books_id) values (3, 6);
insert into author_books (authors_id, books_id) values (3, 9);
insert into author_books (authors_id, books_id) values (3, 12);
insert into author_books (authors_id, books_id) values (3, 15);
insert into author_books (authors_id, books_id) values (3, 18);

