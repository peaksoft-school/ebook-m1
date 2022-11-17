INSERT INTO users(id, created, email, first_name, is_active, last_name, password, phone_number, basket_id, wish_list_id)
VALUES (3, '2020-07-15', 'client@gmail.com', 'Client', true, 'Client', 'client', null, null, null);

INSERT INTO roles(id, name)
VALUES (3, 'ROLE_CLIENT');

INSERT INTO users_roles(user_id, roles_id)
VALUES (3, 3);