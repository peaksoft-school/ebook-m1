INSERT INTO users(id, created, email, first_name, is_active, last_name, password, phone_number, basket_id, wish_list_id)
VALUES (2, '2020-07-14', 'vendor@gmail.com', 'Vendor', true, 'Vendor', 'vendor', +79777461379, null, null);

INSERT INTO roles(id, name)
VALUES (2, 'ROLE_VENDOR');

INSERT INTO users_roles(user_id, roles_id)
VALUES (2, 2);