insert into users(id,created,email,first_name,is_active,last_name,password,phone_number,basket_id,wish_list_id)
values(4,'2020-07-15','client@gmail.com','Client',true,null,'client',null ,null,null );
insert into roles(id, name) VALUES (3,'ROLE_CLIENT');
insert into users_roles(user_id, roles_id) VALUES (4,3);