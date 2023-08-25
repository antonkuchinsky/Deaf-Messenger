create table if not exists _user
(
    id       uuid,
    username text,
    is_active boolean,
    last_active timestamptz,
    primary key (id),
    unique (username)
);

create table if not exists contact
(
    id uuid,
    user_id    uuid,
    contact_id uuid,
    is_blocked boolean,
    contact_category text,
    primary key (id),
    constraint user_id FOREIGN KEY (user_id) REFERENCES _user (id) on DELETE cascade,
    constraint contact_id FOREIGN KEY (contact_id) REFERENCES _user (id)

);

INSERT INTO _user (id, username)
VALUES
    ('007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', 'User 1'),
    ('efc4bdca-c291-4457-be28-1febf3a20311', 'User 2'),
    ('160eda73-8faf-4df5-9e0f-6dce9b9c8834', 'User 3'),
    ('9ac00353-707c-4a76-85a1-e046ac7364a4', 'User 4'),
    ('e07a14a3-1d06-4808-9cfa-e359a2d73ece', 'User 5'),
    ('361c1ef5-0235-42f3-abe0-c9508bf1c91d', 'User 6'),
    ('a28533f7-824b-475c-aa11-ea6f415893aa', 'User 7'),
    ('9b0eab66-eb37-4572-bf1c-256927d9c84b', 'User 8'),
    ('00fe52e4-046f-4083-b89f-16bdd3a00e66', 'User 9'),
    ('424ccb46-b87a-4586-a476-cb7fba70a3b3', 'User 10'),
    ('27aad666-106a-4b84-8b32-dc5609458bad', 'User 11');

INSERT INTO contact (id, user_id, contact_id, is_blocked, contact_category)
VALUES
    ('7374d8c9-33a7-4f6f-9eaa-3bce4b4aa92f','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', false, 'NONE'),
    ('44d08433-f8fd-4e35-85b3-18765e15504e','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', 'efc4bdca-c291-4457-be28-1febf3a20311', false, 'NONE'),
    ('f496a091-861b-42de-8677-6832cfe75cb8','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '160eda73-8faf-4df5-9e0f-6dce9b9c8834', false, 'NONE'),
    ('a940c9ef-6c45-4872-86db-3680e244801d','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '9ac00353-707c-4a76-85a1-e046ac7364a4', false, 'NONE'),
    ('7eed88e4-c80e-4bc8-877b-eb4950c273fc','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', 'e07a14a3-1d06-4808-9cfa-e359a2d73ece', false, 'NONE'),
    ('b1843fb4-ff3c-4b72-8a35-ba223f9d184e','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '361c1ef5-0235-42f3-abe0-c9508bf1c91d', false, 'NONE'),
    ('0cd28dcf-1a9a-446a-afc4-1c7c6beee390','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', 'a28533f7-824b-475c-aa11-ea6f415893aa', false, 'NONE'),
    ('866e2585-ab22-4135-8282-412a0654ff1f','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '9b0eab66-eb37-4572-bf1c-256927d9c84b', false, 'NONE'),
    ('3de77048-08b8-476e-a8c8-672335d893b9','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '00fe52e4-046f-4083-b89f-16bdd3a00e66', false, 'NONE'),
    ('230172ea-8de8-4283-9ed3-4d72a4b4ead2','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '424ccb46-b87a-4586-a476-cb7fba70a3b3', false, 'NONE'),
    ('8a03d702-833f-4705-af24-53e633ca4e50','007d1ca2-5f94-40d9-a0e1-5d5f8d022aba', '27aad666-106a-4b84-8b32-dc5609458bad', false, 'NONE');