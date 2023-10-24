# mvc1AP
mvc ap


create table if not exists person
(
    id       integer generated by default as identity
        primary key,
    fullname varchar
        unique,
    birthday varchar
);

alter table person
    owner to postgres;

create table if not exists book
(
    id        integer generated by default as identity
        primary key,
    person_id integer
        references person
            on update cascade on delete set null,
    title     varchar,
    author    varchar,
    year      integer
);

alter table book
    owner to postgres;


insert into person values(1, 'Алексеев Иван Сергеевич', '14.12.1996');
insert into person values(2, 'Сизифов Труд Николаевич', '21.01.1964');
insert into person values(3, 'Роналду Криштиану Дусилвович', '12.05.2001');
insert into person values(4, 'Коганович Юрий Мартынович', '01.01.1970');
insert into book values(1, 1, 'Ночь в Лиссабоне', 'Ремарк', 1961);
insert into book values(2, 2, 'Преступление и наказание', 'Достоевский', 1756);
insert into book values(3, 3, 'Сказка о царе Суслтане', 'Пушкин', 1684);
insert into book values(14, NULL, 'asd', 'asd', 66);
