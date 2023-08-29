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

INSERT INTO _user (id, username, bio, is_active, last_active)
VALUES ('007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', 'User 1', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00'),
       ('efc4bdca-c291-4457-be28-1febf3a20311', 'User 2', 'я гей', false, '2023-08-25 11:05:19.378000 +00:00'),
       ('160eda73-8faf-4df5-9e0f-6dce9b9c8834', 'User 3', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00'),
       ('9ac00353-707c-4a76-85a1-e046ac7364a4', 'User 4', 'я гей', false, '2023-08-25 11:05:19.378000 +00:00'),
       ('e07a14a3-1d06-4808-9cfa-e359a2d73ece', 'User 5', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00'),
       ('361c1ef5-0235-42f3-abe0-c9508bf1c91d', 'User 6', 'я гей', false, '2023-08-25 11:05:19.378000 +00:00'),
       ('a28533f7-824b-475c-aa11-ea6f415893aa', 'User 7', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00'),
       ('9b0eab66-eb37-4572-bf1c-256927d9c84b', 'User 8', 'я гей', false, '2023-08-25 11:05:19.378000 +00:00'),
       ('00fe52e4-046f-4083-b89f-16bdd3a00e66', 'User 9', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00'),
       ('424ccb46-b87a-4586-a476-cb7fba70a3b3', 'User 10', 'я гей', false, '2023-08-25 11:05:19.378000 +00:00'),
       ('27aad666-106a-4b84-8b32-dc5609458bad', 'User 11', 'я гей', true, '2023-08-25 11:05:19.378000 +00:00');

INSERT INTO contact (id, user_id, contact_id, is_blocked, contact_category, contact_name)
VALUES ('7374d8c9-33a7-4f6f-9eaa-3bce4b4aa92f', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '27aad666-106a-4b84-8b32-dc5609458bad', false, 'FRIEND', 'User 1'),
       ('44d08433-f8fd-4e35-85b3-18765e15504e', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        'efc4bdca-c291-4457-be28-1febf3a20311', false, 'COLLEAGUE', 'User 2'),
       ('f496a091-861b-42de-8677-6832cfe75cb8', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '160eda73-8faf-4df5-9e0f-6dce9b9c8834', false, 'FAMILY', 'User 3'),
       ('a940c9ef-6c45-4872-86db-3680e244801d', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '9ac00353-707c-4a76-85a1-e046ac7364a4', false, 'NONE', 'User 4'),
       ('7eed88e4-c80e-4bc8-877b-eb4950c273fc', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        'e07a14a3-1d06-4808-9cfa-e359a2d73ece', false, 'FRIEND', 'User 5'),
       ('b1843fb4-ff3c-4b72-8a35-ba223f9d184e', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '361c1ef5-0235-42f3-abe0-c9508bf1c91d', false, 'COLLEAGUE', 'User 6'),
       ('0cd28dcf-1a9a-446a-afc4-1c7c6beee390', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        'a28533f7-824b-475c-aa11-ea6f415893aa', false, 'FAMILY', 'User 7'),
       ('866e2585-ab22-4135-8282-412a0654ff1f', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '9b0eab66-eb37-4572-bf1c-256927d9c84b', false, 'NONE', 'User 8'),
       ('3de77048-08b8-476e-a8c8-672335d893b9', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '00fe52e4-046f-4083-b89f-16bdd3a00e66', false, 'FRIEND', 'User 9'),
       ('230172ea-8de8-4283-9ed3-4d72a4b4ead2', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba',
        '424ccb46-b87a-4586-a476-cb7fba70a3b3', false, 'COLLEAGUE', 'User 10');