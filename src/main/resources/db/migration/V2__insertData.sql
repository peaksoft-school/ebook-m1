INSERT INTO users(id, created, email, first_name, is_active, last_name, password, phone_number, basket_id, wish_list_id)
VALUES (1, '2020-07-14', 'admin@gmail.com', 'Admin', true, 'Admin', 'admin', +7977461377, null, null);

INSERT INTO roles(id, name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO users_roles(user_id, roles_id)
VALUES (1, 1);