create sequence book_seq start 1 increment 1;
create sequence role_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;

create table baskets
(
    id           int8 generated by default as identity,
    basket_price float8,
    created_date date,
    quantity     int4,
    status       varchar(2048),
    book_id      int8,
    user_id      int8,
    primary key (id)
);
create table books
(
    id               int8   not null,
    about_the_book   varchar(1500),
    amount_of_books  int4   not null,
    author           varchar(2048),
    bestseller       boolean,
    book_fragment    varchar(9500),
    book_language    varchar(2048),
    comments         varchar(2048),
    discount         int4   not null,
    genre_enum       varchar(2048),
    image            varchar(2048),
    page_volume      int4   not null,
    price            float8 not null,
    publishing_house varchar(2048),
    status           varchar(2048),
    title            varchar(2048),
    type_of_book     varchar(2048),
    year_of_issue    int4,
    promocode_id     int8,
    user_id          int8,
    primary key (id)
);
create table history
(
    id           int8 generated by default as identity,
    created_date date,
    basket_id    int8,
    user_id      int8,
    wishlist_id  int8,
    primary key (id)
);
create table promocodes
(
    id              int8 generated by default as identity,
    amount_of_promo int4 not null,
    finishing_day   date,
    promo_name      varchar(2048),
    starting_day    date,
    user_id         int8,
    primary key (id)
);
create table roles
(
    id   int8 not null,
    name varchar(2048),
    primary key (id)
);
create table users
(
    id           int8    not null,
    created      timestamp,
    email        varchar(2048) not null,
    first_name   varchar(2048),
    is_active    boolean not null,
    last_name    varchar(2048),
    password     varchar(2048) not null,
    phone_number varchar(2048),
    basket_id    int8,
    wish_list_id int8,
    primary key (id)
);
create table users_histories
(
    user_id    int8 not null,
    history_id int8 not null
);
create table users_roles
(
    user_id  int8 not null,
    roles_id int8 not null
);
create table wishlists
(
    id           int8 generated by default as identity,
    created_date date,
    book_id      int8,
    user_id      int8,
    primary key (id)
);
alter table if exists users add constraint user_email_fk unique (email);
alter table if exists users add constraint user_password_fk unique (password);
alter table if exists users_histories add constraint users_histories_fk unique (history_id);
alter table if exists baskets add constraint basket_book_fk foreign key (book_id) references books;
alter table if exists baskets add constraint basket_user_fk foreign key (user_id) references users;
alter table if exists books add constraint book_promocode_fk foreign key (promocode_id) references promocodes on delete cascade;
alter table if exists books add constraint book_user_fk foreign key (user_id) references users;
alter table if exists history add constraint history_basket_fk foreign key (basket_id) references baskets;
alter table if exists history add constraint history_user_fk foreign key (user_id) references users;
alter table if exists history add constraint history_wishlist_fk foreign key (wishlist_id) references wishlists;
alter table if exists promocodes add constraint promocode_user_fk foreign key (user_id) references users;
alter table if exists users add constraint user_basket_fk foreign key (basket_id) references baskets;
alter table if exists users add constraint user_wishlist_fk foreign key (wish_list_id) references wishlists;
alter table if exists users_histories add constraint users_histiories_history_fk foreign key (history_id) references history;
alter table if exists users_histories add constraint users_histories_user_fk foreign key (user_id) references users;
alter table if exists users_roles add constraint users_roles_role_fk foreign key (roles_id) references roles;
alter table if exists users_roles add constraint users_roles_user_fk foreign key (user_id) references users;
alter table if exists wishlists add constraint wishlists_book_fk foreign key (book_id) references books;
alter table if exists wishlists add constraint wishlists_user_fk foreign key (user_id) references users;