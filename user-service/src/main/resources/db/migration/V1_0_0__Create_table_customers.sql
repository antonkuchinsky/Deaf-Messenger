create table if not exists _user
(
    id          uuid,
    username    text,
    bio         text,
    is_active   boolean,
    last_active timestamptz,
    primary key (id),
    unique (username)
);

create table if not exists contact
(
    id               uuid,
    user_id          uuid,
    contact_id       uuid,
    is_blocked       boolean,
    contact_category text,
    contact_name     text,
    primary key (id),
    constraint user_id FOREIGN KEY (user_id) REFERENCES _user (id),
    constraint contact_id FOREIGN KEY (contact_id) REFERENCES _user (id) on DELETE cascade

);
